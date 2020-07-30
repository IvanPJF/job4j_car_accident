package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {

    private static final List<String> USERS = List.of("Sarah Connor", "John Connor", "T-800");

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", USERS);
        return "index";
    }
}
