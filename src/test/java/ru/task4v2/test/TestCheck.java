package ru.task4v2.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.task4v2.model.ParseString;
import ru.task4v2.service.CheckFIOImpl;
import ru.task4v2.service.CheckTypeAppImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestCheck {
    @Test
    @DisplayName("test FIO")
    public void TestFIO() {
        ParseString parseString = new ParseString("login1", "иванов иван иванович", new Timestamp((new Date()).getTime()), "others");
        List<ParseString> orig = new ArrayList<>();
        List<ParseString> res;
        orig.add(0, parseString);
        res = (new CheckFIOImpl()).check(orig);
//        System.out.println("TestFIO: " + res);
        Assertions.assertEquals(res.get(0).getFio(), "Иванов Иван Иванович");
    }

    @Test
    @DisplayName("test typeApp")
    public void TestApp() {
        ParseString parseString = new ParseString("login1", "иванов иван иванович", new Timestamp((new Date()).getTime()), "others");
        List<ParseString> orig = new ArrayList<>();
        List<ParseString> res;
        orig.add(0, parseString);
        res = (new CheckTypeAppImpl()).check(orig);
//        System.out.println("TestApp: " + res);
        Assertions.assertEquals(res.get(0).getTypeApp(), "others: OTHERS");
    }
}
