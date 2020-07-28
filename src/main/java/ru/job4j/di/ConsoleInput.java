package ru.job4j.di;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
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
