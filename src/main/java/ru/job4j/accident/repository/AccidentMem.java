package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger index = new AtomicInteger(1);
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();

    public AccidentMem() {
        fillAccidentTypes();
    }

    private void fillAccidentTypes() {
        AccidentType aTwoCars = AccidentType.of(1, "Две машины");
        AccidentType aCarAndMan = AccidentType.of(2, "Машина и человек");
        AccidentType aCarAndBicycle = AccidentType.of(3, "Машина и велосипед");
        accidentTypes.put(aTwoCars.getId(), aTwoCars);
        accidentTypes.put(aCarAndMan.getId(), aCarAndMan);
        accidentTypes.put(aCarAndBicycle.getId(), aCarAndBicycle);
    }

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }

    public void create(Accident accident) {
        accident.setId(index.getAndIncrement());
        accidents.put(accident.getId(), accident);
    }

    public void edit(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }

    public Accident findById(Accident accident) {
        return accidents.get(accident.getId());
    }

    public Collection<AccidentType> allAccidentTypes() {
        return accidentTypes.values();
    }

    public AccidentType findAccidentTypeById(AccidentType type) {
        return accidentTypes.get(type.getId());
    }
}
