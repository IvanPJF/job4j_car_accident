package ru.job4j.accident.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;

@Repository
@Transactional
public class AccidentHibernate {

    private final SessionFactory sf;
    private final String sqlAllAccidents = "select distinct a from Accident a"
            + " left join fetch a.type left join fetch a.rules";

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Collection<Accident> allAccidents() {
        Session session = sf.getCurrentSession();
        return session.createQuery(sqlAllAccidents, Accident.class).list();
    }

    public void create(Accident accident) {
        Session session = sf.getCurrentSession();
        session.save(accident);
    }

    public void edit(Accident accident) {
        Session session = sf.getCurrentSession();
        session.update(accident);
    }

    public Accident findById(Accident accident) {
        Session session = sf.getCurrentSession();
        return session.createQuery(sqlAllAccidents + " where a.id = :aId", Accident.class)
                .setParameter("aId", accident.getId()).uniqueResult();
    }

    public Collection<AccidentType> allAccidentTypes() {
        Session session = sf.getCurrentSession();
        return session.createQuery("select at from AccidentType at", AccidentType.class).list();
    }

    public AccidentType findAccidentTypeById(AccidentType accidentType) {
        Session session = sf.getCurrentSession();
        return session.get(AccidentType.class, accidentType.getId());
    }

    public Collection<Rule> allRules() {
        Session session = sf.getCurrentSession();
        return session.createQuery("select r from Rule r", Rule.class).list();
    }

    public Set<Rule> findRulesByIds(String[] sIds) {
        Session session = sf.getCurrentSession();
        List<Rule> rules = session
                .createQuery("select r from Rule r where r.id in (:ids)", Rule.class)
                .setParameterList("ids", strIdsToIntList(sIds)).list();
        return new HashSet<>(rules);
    }

    private List<Integer> strIdsToIntList(String[] ids) {
        List<Integer> listId = new ArrayList<>();
        for (String id : ids) {
            listId.add(Integer.parseInt(id));
        }
        return listId;
    }
}
