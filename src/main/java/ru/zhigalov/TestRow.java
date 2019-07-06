package ru.zhigalov;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhigalov on 04.07.19.
 * Объект одной строки
 */
public class TestRow {
    private Map<String, String> row;
    /**
    * в задании говорится о поле referer, но в образцах на обработку его нет.
    * добавить в конструктор 'String referer,' при необходимости.
     * поправить код в классах: ConsoleInput, TestRow
     */
    public TestRow(String start_page, String user, String ts, String depth, String duration, String transmit, String type) {
        row = new HashMap<>();
        row.put("start_page", start_page);
      //row.put("referer", referer);
        row.put("user", user);
        row.put("depth", depth);
        row.put("duration", duration);
        row.put("transmit", transmit);
        row.put("type", type);
        row.put("ts",ts);
    }

    //геттер
    public Map<String, String> getRow() {
        return row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestRow testRow = (TestRow) o;

        return row.equals(testRow.row);

    }

    @Override
    public int hashCode() {
        return row.hashCode();
    }

    @Override
    public String toString() {
        return "TestRow{" +
                "row=" + row +
                '}';
    }
}
