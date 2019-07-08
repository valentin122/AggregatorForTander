package ru.zhigalov;

public class PrintHelp {
    // Вызывается в случае проблем с ключами
    public static void printHelp() {
        System.out.println("To run the program, you need to correctly specify the startup keys:");
        System.out.println("Launch key format: [1] [2] [3]");
        System.out.println();
        System.out.println("[1] - input method key:");
        System.out.println("    f - data from a file (entered csv и xml)");
        System.out.println("    s - data from a console");
        System.out.println();
        System.out.println("[2] - data processing key:");
        System.out.println("    field_group:type_of_aggregation:aggregation_field");
        System.out.println("    types of aggregation:[ sum | avg | count | min | max ]");
        System.out.println();
        System.out.println("[3] - output method key:");
        System.out.println("    f - output result to file");
        System.out.println("    s - output result to console");
        System.out.println("    db - output result to DB");
    }
}
