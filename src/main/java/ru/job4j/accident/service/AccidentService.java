package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.*;

@Service
public class AccidentService {

    private final AccidentRepository repository;

    public AccidentService(AccidentRepository repository) {
        this.repository = repository;
    }

    public Collection<Accident> allAccident() {
        return repository.allAccidents();
    }

    public void save(Accident accident) {
        repository.save(accident);
    }

    public Accident findById(Accident accident) {
        return repository.findById(accident.getId()).orElse(null);
    }

    public Collection<AccidentType> allAccidentTypes() {
        return repository.allAccidentTypes();
    }

    public AccidentType findAccidentTypeById(AccidentType type) {
        return repository.findAccidentTypeById(type.getId());
    }

    public Collection<Rule> allRules() {
        return repository.allRules();
    }

    public Set<Rule> findRulesByIds(String[] sIds) {
        return repository.findRulesByIds(strIdsToIntList(sIds));
    }

    private List<Integer> strIdsToIntList(String[] ids) {
        List<Integer> listId = new ArrayList<>();
        for (String id : ids) {
            listId.add(Integer.parseInt(id));
        }
        return listId;
    }
}
