package ru.zhigalov.csv;

import ru.zhigalov.Executer;
import ru.zhigalov.TestRow;

import java.io.*;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Zhigalov on 04.07.19
 */

public class CsvReadWrite {
    private static Logger logger = Logger.getLogger(Executer.class.getName());
    public static HashSet<TestRow> readDataFromCSV(String fileName){
        HashSet<TestRow> temp = new HashSet<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            reader.readLine();
            while ((line = reader.readLine()) != null){
                String[] row = line.split(",");
                temp.add(new TestRow(row[0],row[1],row[2],row[3],row[4],row[5],row[6])); //,row[7]
            }
        }catch (FileNotFoundException fnfe){
            logger.warning("File not found...");
            fnfe.printStackTrace();
        }catch (IOException ioe){
            logger.warning("Error read file...");
            ioe.printStackTrace();
        }
        return temp;
    }

    public static void writeToCSV(Map<String, String> finalData, String column1, String column2, String agr, String fileName){
        String head = column1 + "," + agr + "_" + column2;
        String newFileName = new StringBuffer(fileName).insert(fileName.lastIndexOf("\\")+1,"new_").toString();
        try(PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(newFileName)))){
            pw.println(head);
            for (Map.Entry<String,String> entry:finalData.entrySet()
            ) {
                pw.println(entry.getKey() + "," + entry.getValue());
            }
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        System.out.println("Data written to file " + newFileName);
    }
}
