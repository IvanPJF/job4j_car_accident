package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

//@Repository
public class AccidentMem {

    private final Map<Integer, Accident> accidents = new HashMap<>();
    private final AtomicInteger index = new AtomicInteger(1);
    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>();
    private final Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        fillAccidentTypes();
        fillRules();
    }

    private void fillAccidentTypes() {
        AccidentType aTwoCars = AccidentType.of(1, "Две машины");
        AccidentType aCarAndMan = AccidentType.of(2, "Машина и человек");
        AccidentType aCarAndBicycle = AccidentType.of(3, "Машина и велосипед");
        accidentTypes.put(aTwoCars.getId(), aTwoCars);
        accidentTypes.put(aCarAndMan.getId(), aCarAndMan);
        accidentTypes.put(aCarAndBicycle.getId(), aCarAndBicycle);
    }

    private void fillRules() {
        Rule rOne = Rule.of(1, "Статья.1");
        Rule rTwo = Rule.of(2, "Статья.2");
        Rule rThree = Rule.of(3, "Статья.3");
        rules.put(rOne.getId(), rOne);
        rules.put(rTwo.getId(), rTwo);
        rules.put(rThree.getId(), rThree);
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

    public Collection<Rule> allRules() {
        return rules.values();
    }

    public Set<Rule> findRulesByIds(String[] sIds) {
        Set<Rule> result = new HashSet<>();
        for (String sId : sIds) {
            result.add(rules.get(Integer.parseInt(sId)));
        }
        return result;
    }
}
