package ru.zhigalov.dao;


import ru.zhigalov.Executer;
import ru.zhigalov.input.ConsoleInput;


import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by Zhigalov on 04.07.19
 */

public class DbHelper {
    private Logger logger = Logger.getLogger(Executer.class.getName());

    private String url;
    private String login;
    private String pw;

    // Записываем данные в БД
    public void writeDataToDb(Map<String, String> finalData, String column1, String column2, String agr){
        try (Connection connection = DriverManager.getConnection(url, login, pw)){
            logger.info("Connected to DB");
            Statement stat=connection.createStatement();
            stat.executeUpdate("CREATE TABLE F1_Result ("+column1+" VARCHAR(20), "+agr+"_"+column2+" VARCHAR(20))");
            logger.info("Create table");
            for (Map.Entry<String,String> entry:finalData.entrySet()
                    ) {
                stat.executeUpdate("INSERT INTO F1_Result VALUES ("+entry.getKey()+","+entry.getValue()+")");
            }
            logger.info("Data written to DB");
            System.out.println("Data written to DB");
        }catch (SQLException sqle){
            logger.warning("Error while working with DB");
            sqle.printStackTrace();
        }
    }

    // Считываем данные из файла и создаем по ним коннект к БД
    public Connection getProperties() throws SQLException
    {
        Properties props=new Properties();
        System.out.println("Please enter path to config file JDBC:");
        String fileNameProps = "";
        try {
            fileNameProps = ConsoleInput.consoleInput();
        }catch (IOException ioe){
            logger.warning("Error read file");
            ioe.printStackTrace();
        }
        try(FileInputStream in=new FileInputStream(fileNameProps)){
            props.load(in);
        }catch (IOException ioe){
            logger.warning("Error loading file Properties");
            ioe.printStackTrace();
        }
        String drivers = props.getProperty("jdbc.drivers");
        if(drivers !=null) System.setProperty("jdbc.drivers", drivers);
        url = props.getProperty("jdbc.url");
        login=props.getProperty("jdbc.username");
        pw=props.getProperty("jdbc.password");
        logger.info("Properties DB loaded");
        return DriverManager.getConnection(url, login, pw);
    }
}
