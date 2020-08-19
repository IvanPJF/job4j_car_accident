package ru.job4j.accident.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accident.service.AccidentService;

import java.util.Map;

@Controller
public class IndexControl {

    private AccidentService service;

    public IndexControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAllAttributes(Map.of(
                "accidents", service.allAccident(),
                "user", SecurityContextHolder.getContext().getAuthentication().getPrincipal()
        ));
        return "index";
    }
}
