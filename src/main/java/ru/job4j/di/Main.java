package ru.job4j.di;

public class Main {

    public static void main(String[] args) {
        Context context = new Context();
        context.reg(Store.class);
        context.reg(ConsoleInput.class);
        context.reg(StartUI.class);
        StartUI startUI = context.get(StartUI.class);
        startUI.add("First name");
        startUI.add("Second name");
        startUI.add();
        startUI.print();
    }
}
