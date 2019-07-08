package ru.zhigalov;

import ru.zhigalov.dao.DbHelper;
import ru.zhigalov.file.FileHelper;
import ru.zhigalov.input.ConsoleInput;

import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Zhigalov on 04.07.19.
 */
public class Executer {

    protected Logger logger = Logger.getLogger(Executer.class.getName());
    // Ключи запуска
    private String inKey;
    private String[] agrKey;
    private String outKey;

    // Загруженные данные
    private HashSet<TestRow> data;

    // Итоговые данные
    private Map<String, String> finalData;

    // Геттеры и Сеттеры
    public Map<String, String> getFinalData() {
        return finalData;
    }

    public void setFinalData(Map<String, String> finalData) {
        this.finalData = finalData;
    }

    public HashSet<TestRow> getData() {
        return data;
    }

    public void setData(HashSet<TestRow> data) {
        this.data = data;
    }

    public String getInKey() {
        return inKey;
    }

    public void setInKey(String inKey) {
        this.inKey = inKey;
    }

    public String[] getAgrKey() {
        return agrKey;
    }

    public void setAgrKey(String[] agrKey) {
        this.agrKey = agrKey;
    }

    public String getOutKey() {
        return outKey;
    }

    public void setOutKey(String outKey) {
        this.outKey = outKey;
    }


    // Загрузка данных в программу
    protected void loadData() {
        switch (getInKey()) {
            case ("f"):
                // Из файла
                logger.info("Trying to accept data from a file");
                setData(new FileHelper().getDataFromFile());
                break;
            case ("s"):
                // Через консоль
                logger.info("Trying to accept data from a console");
                setData(new ConsoleInput().getDataFromConsole());
                break;
        }
    }

    // Обработка данных
    protected void agrData() {
        switch (getAgrKey()[1]) {
            case ("sum"):
                // По ключу SUM
                logger.info("Try to process the data on the key sum");
                setFinalData(new AgrHelper().getSum(getData(), getAgrKey()[0], getAgrKey()[2]));
                break;
            case ("avg"):
                // По ключу AVG
                logger.info("Try to process the data on the key avg");
                setFinalData(new AgrHelper().getAvg(getData(), getAgrKey()[0], getAgrKey()[2]));
                break;
            case ("count"):
                // По ключу COUNT
                logger.info("Try to process the data on the key count");
                setFinalData(new AgrHelper().getCount(getData(), getAgrKey()[0]));
                break;
            case ("min"):
                // По ключу MIN
                logger.info("Try to process the data on the key min");
                setFinalData(new AgrHelper().getMin(getData(), getAgrKey()[0], getAgrKey()[2]));
                break;
            case ("max"):
                // По ключу MAX
                logger.info("Try to process the data on the key max");
                setFinalData(new AgrHelper().getMax(getData(), getAgrKey()[0], getAgrKey()[2]));
                break;
        }
    }
    // Вывод данных
    protected void outData() {
        switch (getOutKey()) {
            case ("f"):
                // В файл
                logger.info("Trying to output data to a file");
                new FileHelper().writeFile(getFinalData(), getAgrKey()[0], getAgrKey()[2], getAgrKey()[1]);
                break;
            case ("s"):
                // На консоль
                logger.info("Trying to output data to a console");
                new ConsoleInput().printFinalData(getFinalData(), getAgrKey()[0], getAgrKey()[2], getAgrKey()[1]);
                break;
            case ("db"):
                // В БД
                logger.info("Trying to output data to a DB");
                new DbHelper().writeDataToDb(getFinalData(), getAgrKey()[0], getAgrKey()[2], getAgrKey()[1]);
                break;
        }
    }
    protected void checkKeys(){
        //Проверяем ключ ввода
        if (!(getInKey().equals("f") || getInKey().equals("s"))){
            System.out.println("0. Input method key error! Run the program with the correct parameters");
            System.exit(2);
        }

        // Проверяем ключ обработки данных
        if (!(getAgrKey()[0].equals("start_page") || getAgrKey()[0].equals("referer") || getAgrKey()[0].equals("user")
                || getAgrKey()[0].equals("depth") || getAgrKey()[0].equals("duration") || getAgrKey()[0].equals("transmit")
                || getAgrKey()[0].equals("type") || getAgrKey()[0].equals("ts"))){
            System.out.println("1. Error processing key: invalid grouping field! Run the program with the correct parameters");
            System.exit(2);
        }

        if (!(getAgrKey()[1].equals("sum") || getAgrKey()[1].equals("avg") || getAgrKey()[1].equals("count")
                || getAgrKey()[1].equals("min") || getAgrKey()[1].equals("max"))){
            System.out.println("2. Error processing key: invalid aggregation type! Run the program with the correct parameters");
            System.exit(2);
        }

        if (!(getAgrKey()[2].equals("start_page") || getAgrKey()[2].equals("referer") || getAgrKey()[2].equals("user")
                || getAgrKey()[2].equals("depth") || getAgrKey()[2].equals("duration") || getAgrKey()[2].equals("transmit")
                || getAgrKey()[2].equals("type") || getAgrKey()[2].equals("ts"))){
            System.out.println("3. Error in data processing key: wrong aggregated field! Run the program with the correct parameters");
            System.exit(2);
        }

        // Проверяем ключ вывода
        if (!(getOutKey().equals("f") || getOutKey().equals("s") || getOutKey().equals("db"))){
            System.out.println("4. Error in output method key! Run the program with the correct parameters");
            System.exit(2);
        }
    }
}