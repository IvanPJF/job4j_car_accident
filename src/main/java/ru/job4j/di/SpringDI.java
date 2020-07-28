package ru.job4j.di;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringDI {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ru.job4j.di");
        context.refresh();
        StartUI startUI = context.getBean(StartUI.class);
        startUI.add("First name");
        startUI.add("Second name");
        startUI.add();
        startUI.print();
    }
}
