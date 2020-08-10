package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;

@Repository
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;
    private final String sqlAllAccidents = "select a.id aId, a.name aName, a.address, a.text,"
            + " at.id atId, at.name atName, r.id rId, r.name rName"
            + " from accident a"
            + " left join accident_type at on a.accident_type_id = at.id"
            + " left join accident_rule ar on a.id = ar.accident_id"
            + " left join rules r on ar.rule_id = r.id";

    public AccidentJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<Accident> allAccidents() {
        return jdbc.query(sqlAllAccidents, extractData());
    }

    @Transactional
    public void create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement(
                    "insert into accident (name, text, address, accident_type_id)"
                            + " values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((Integer) keyHolder.getKeys().get("id"));
        insertRulesForAccident(accident);
    }

    @Transactional
    public void edit(Accident accident) {
        jdbc.update("update accident set name = ?, text = ?, address = ?, accident_type_id = ?"
                        + " where id = ?",
                accident.getName(), accident.getText(), accident.getAddress(),
                accident.getType().getId(), accident.getId());
        jdbc.update("delete from accident_rule where accident_id = ?", accident.getId());
        insertRulesForAccident(accident);
    }

    public Accident findById(Accident accident) {
        Collection<Accident> accidents = jdbc.query(sqlAllAccidents + " where a.id = ?",
                extractData(),
                accident.getId());
        Iterator<Accident> iterator = accidents.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    public Collection<AccidentType> allAccidentTypes() {
        return jdbc.query("select id, name from accident_type",
                (rs, rowNum) -> AccidentType.of(rs.getInt("id"), rs.getString("name")));
    }

    public AccidentType findAccidentTypeById(AccidentType accidentType) {
        return jdbc.queryForObject("select id, name from accident_type where id = ?",
                (rs, rowNum) -> AccidentType.of(rs.getInt("id"), rs.getString("name")),
                accidentType.getId());
    }

    public Collection<Rule> allRules() {
        return jdbc.query("select id, name from rules",
                (rs, rowNum) -> Rule.of(rs.getInt("id"), rs.getString("name")));
    }

    public Set<Rule> findRulesByIds(String[] sIds) {
        List<Rule> query = jdbc.query("select id, name from rules where id = any (?)",
                pss -> pss.setArray(1, pss.getConnection().createArrayOf("integer", sIds)),
                (rs, rowNum) -> Rule.of(
                        rs.getInt("id"),
                        rs.getString("name")
                ));
        return new HashSet<>(query);
    }

    private void insertRulesForAccident(Accident accident) {
        Set<Rule> rules = accident.getRules();
        jdbc.batchUpdate("insert into accident_rule (accident_id, rule_id) values (?, ?)",
                rules, rules.size(),
                (ps, argRule) -> {
                    ps.setInt(1, accident.getId());
                    ps.setInt(2, argRule.getId());
                });
    }

    private ResultSetExtractor<Collection<Accident>> extractData() {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        return rs -> {
            while (rs.next()) {
                int id = rs.getInt("aId");
                Accident accident = accidentMap.get(id);
                Rule rule = Rule.of(rs.getInt("rId"), rs.getString("rName"));
                if (Objects.isNull(accident)) {
                    String name = rs.getString("aName");
                    String address = rs.getString("address");
                    String text = rs.getString("text");
                    AccidentType accidentType = AccidentType.of(
                            rs.getInt("atId"),
                            rs.getString("atName"));
                    accident = new Accident(id, name, text, address);
                    accident.setType(accidentType);
                    accident.setRules(new HashSet<>());
                    accidentMap.put(accident.getId(), accident);
                }
                accident.getRules().add(rule);
            }
            return accidentMap.values();
        };
    }
}
