package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Service
public class AccidentService {

    private AccidentMem repository;

    public AccidentService(AccidentMem repository) {
        this.repository = repository;
    }

    public Collection<Accident> allAccident() {
        return repository.getAccidents().values();
    }

    public void save(Accident accident) {
        if (Objects.nonNull(findById(accident))) {
            repository.edit(accident);
            return;
        }
        repository.create(accident);
    }

    public Accident findById(Accident accident) {
        return repository.findById(accident);
    }

    public Collection<AccidentType> allAccidentTypes() {
        return repository.allAccidentTypes();
    }

    public AccidentType findAccidentTypeById(AccidentType type) {
        return repository.findAccidentTypeById(type);
    }

    public Collection<Rule> allRules() {
        return repository.allRules();
    }

    public Set<Rule> findRulesByIds(String[] sIds) {
        return repository.findRulesByIds(sIds);
    }
}
