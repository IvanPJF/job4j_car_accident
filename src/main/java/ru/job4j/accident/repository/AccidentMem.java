package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentMem {

    private Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        fill();
    }

    private void fill() {
        Accident a1 = new Accident(1, "Accident #1", "Text #1", "Street #1");
        Accident a2 = new Accident(2, "Accident #2", "Text #2", "Street #2");
        Accident a3 = new Accident(3, "Accident #3", "Text #3", "Street #3");
        accidents.put(a1.getId(), a1);
        accidents.put(a2.getId(), a2);
        accidents.put(a3.getId(), a3);
    }

    public Map<Integer, Accident> getAccidents() {
        return accidents;
    }
}
