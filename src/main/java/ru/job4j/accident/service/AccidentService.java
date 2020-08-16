package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.RuleRepository;

import java.util.*;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentService(AccidentRepository accidentRepository,
                           AccidentTypeRepository accidentTypeRepository,
                           RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public Collection<Accident> allAccident() {
        List<Accident> accidents = new ArrayList<>();
        accidentRepository.findAll().forEach(accidents::add);
        return accidents;
    }

    public void save(Accident accident) {
        accidentRepository.save(accident);
    }

    public Accident findById(Accident accident) {
        return accidentRepository.findById(accident.getId()).orElse(null);
    }

    public Collection<AccidentType> allAccidentTypes() {
        List<AccidentType> types = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(types::add);
        return types;
    }

    public AccidentType findAccidentTypeById(AccidentType type) {
        return accidentTypeRepository.findById(type.getId()).orElse(null);
    }

    public Collection<Rule> allRules() {
        List<Rule> rules = new ArrayList<>();
        ruleRepository.findAll().forEach(rules::add);
        return rules;
    }

    public Set<Rule> findRulesByIds(String[] sIds) {
        Set<Rule> rules = new HashSet<>();
        ruleRepository.findAllById(() -> strIdsToIntList(sIds).iterator()).forEach(rules::add);
        return rules;
    }

    private List<Integer> strIdsToIntList(String[] ids) {
        List<Integer> listId = new ArrayList<>();
        for (String id : ids) {
            listId.add(Integer.parseInt(id));
        }
        return listId;
    }
}
