package ru.zhigalov;

/**
 * Created by Zhigalov on 04.07.19
 */

public class Main {
    public static void main(String[] args){
        Executer executer = new Executer();
        executer.logger.info("Run");
        // Начальная проверка на соответствие ключей и вывод информации по ключам
        if (args.length != 3){
            PrintHelp.printHelp();
            executer.logger.warning("Wrong keys, exit");
            System.exit(1);
        }
        executer.logger.info("Program run with keys: " + args[0] + " " + args[1] + " " + args[2]);
        executer.setInKey(args[0].toLowerCase());
        executer.setAgrKey(args[1].toLowerCase().split(":"));
        executer.setOutKey(args[2].toLowerCase());

        // Проверяем правильность ключей
        executer.checkKeys();
        executer.logger.info("Keys have been verified");
        // Загружаем данные в программу
        executer.loadData();
        executer.logger.info("Data loaded");
        // Обрабатываем данные
        executer.agrData();
        executer.logger.info("Data processed");
        // Выводим данные
        executer.outData();
        executer.logger.info("The data provided the program is completed");
    }
}
