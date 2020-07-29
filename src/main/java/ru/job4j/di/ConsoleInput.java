package ru.job4j.di;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Scope("prototype")
public class ConsoleInput {

    private static final String ASK = "Enter the new string element:";

    public String ask() {
        System.out.println(ASK);
        String value = null;
        do {
            value = new Scanner(System.in)
                    .nextLine()
                    .trim();
        } while (value.isEmpty());
        return value;
    }
}
