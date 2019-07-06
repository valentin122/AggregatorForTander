package ru.zhigalov;

public class PrintHelp {
    // Вызывается в случае проблем с ключами
    public static void printHelp() {
        System.out.println("Для работы программы нужно верно указать ключи запуска:");
        System.out.println("Формат ключа запуска: [1] [2] [3]");
        System.out.println();
        System.out.println("[1] - ключ метода ввода:");
        System.out.println("    f - данные из файла (принимаем csv и xml)");
        System.out.println("    s - данные из консоли");
        System.out.println();
        System.out.println("[2] - ключ обработки данных:");
        System.out.println("    поле_группировки:вид_агрегации:агрегируемое_поле");
        System.out.println("    виды агрегации:[ sum | avg | count | min | max ]");
        System.out.println();
        System.out.println("[3] - ключ метода вывода:");
        System.out.println("    f - вывод результата в файл");
        System.out.println("    s - вывод результата в консоль");
        System.out.println("    db - вывод результата в БД");
    }
}
