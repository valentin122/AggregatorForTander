package ru.zhigalov.file;

import ru.zhigalov.Executer;
import ru.zhigalov.TestRow;
import ru.zhigalov.csv.CsvReadWrite;
import ru.zhigalov.input.ConsoleInput;
import ru.zhigalov.xml.AllXmlOperations;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Zhigalov on 04.07.19
 */
public class FileHelper {
    private Logger logger = Logger.getLogger(Executer.class.getName());

    private static String fileSuffix;
    private static String fileName = "result.csv";
    // Читаем данные из файла
    public HashSet<TestRow> getDataFromFile(){
        HashSet<TestRow> temp = new HashSet<>();
        fileName = askFileName();
        fileSuffix = fileName.substring(fileName.length()-4).toLowerCase();
        switch (fileSuffix){
            case ".csv":
                // Читаем CSV
                temp = CsvReadWrite.readDataFromCSV(fileName);
                break;
            case ".xml":
                // Читаем XML
                temp = AllXmlOperations.readDataFromXML(fileName);
                break;
            default:
                System.out.println("The source file format is incorrect. Run the program with the correct parameters!");
                logger.warning("The program does not work with this data format :(");
                System.exit(2);
        }
        return temp;
    }

    // Запрашиваем имя файла
    private String askFileName(){
        String file = new String();
        System.out.println("Enter a valid path and file name:");
        try {
            while (true){
                file = ConsoleInput.consoleInput();
                if (file.toLowerCase().equals("exit")) System.exit(2);
                if (file.length()>4) break;
                System.out.println("The file path is too short. Try again or type exit to exit the program");
            }
        }catch (IOException ioe){
            logger.warning("Error reading file name");
            ioe.printStackTrace();
        }
        return file;
    }

    // Пишем данные в файл
    public void writeFile(Map<String, String> finalData, String column1, String column2, String agr){
        fileSuffix = fileName.substring(fileName.length()-4).toLowerCase();
        switch (fileSuffix){
            case ".csv":
                // Пишем в CSV
                CsvReadWrite.writeToCSV(finalData,column1,column2,agr,fileName);
                break;
            case ".xml":
                // Пишем в XML
                AllXmlOperations.writeToXML(finalData,column1,column2,agr,fileName);
                break;
            default:
                System.out.println("The output file format is incorrect. Run the program with the correct parameters!");
                System.exit(2);
        }
    }
}
