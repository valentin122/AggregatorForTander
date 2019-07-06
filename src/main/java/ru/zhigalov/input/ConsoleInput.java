package ru.zhigalov.input;

import ru.zhigalov.Executer;
import ru.zhigalov.TestRow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Zhigalov on 06.07.19
 */

public class ConsoleInput {
    private Logger logger = Logger.getLogger(Executer.class.getName());
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

        // Запрашиваем ввод данных через консоль
        public HashSet<TestRow> getDataFromConsole(){
            HashSet<TestRow> temp = new HashSet<>();
            System.out.println("Введите данные вручную в формате: start_page,user,ts,depth,duration,transmit,type"); //referer,
            System.out.println("Для окончания ввода введите exit");
                try {
                    while (true){
                        String str = consoleInput();
                        if (str.toLowerCase().equals("exit")) break;
                        String[] row = str.split(",");
                        if (row.length != 7){
                            //или if (row.length != 8){ если входящие данные будут с полем referer. см. TestRow
                            System.out.println("Неверный формат данных! Введите корректные данные или завершите ввод командой exit");
                            continue;
                        }
                        temp.add(new TestRow(row[0],row[1],row[2],row[3],row[4],row[5],row[6])); //,row[7]
                        System.out.println("Строка добавлена");
                    }
                }catch (IOException ioe){
                    logger.warning("Ошибка при вводе строки с данными");
                    ioe.printStackTrace();
                }
            return temp;
        }

        // Вывод итога на консоль
        public void printFinalData(Map<String, String> finalData, String column1, String column2, String agr){
            System.out.println(column1 + "\t" + agr + "_" + column2);
            for (Map.Entry<String,String> entry:finalData.entrySet()
                 ) {
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
        }

        public static String consoleInput() throws IOException {
            return READER.readLine();
        }
}
