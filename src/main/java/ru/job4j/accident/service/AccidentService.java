package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private AccidentMem repository;

    public AccidentService(AccidentMem repository) {
        this.repository = repository;
    }

    public Collection<Accident> allAccident() {
        return repository.getAccidents().values();
    }

    public void create(Accident accident) {
        repository.create(accident);
    }

    public void edit(Accident accident) {
        repository.edit(accident);
    }

    public Accident findById(Accident accident) {
        return repository.findById(accident);
    }
}
