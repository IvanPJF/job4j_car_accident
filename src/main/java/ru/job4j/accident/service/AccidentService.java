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
}
