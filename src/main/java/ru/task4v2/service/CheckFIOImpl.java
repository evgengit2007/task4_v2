package ru.task4v2.service;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.task4v2.logging.LogTransformation;
import ru.task4v2.model.ParseString;
import ru.task4v2.repo.Checker;

import java.util.List;

@Component
@Order(1)
@LogTransformation(logName = "C:/temp/log/log_fio.log")
public class CheckFIOImpl implements Checker {
    // проверить ФИО что с заглавной буквы

    @Override
    public List<ParseString> check(List<ParseString> lst) {
        for (ParseString str : lst) {
            str.setFio(firstChartoUpper(str.getFio()));
        }
        return lst;
    }

    private String firstChartoUpper(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        StringBuilder result = new StringBuilder();
        boolean firstChar = true;
        for (char c : input.trim().toCharArray()) {
            c = firstChar ? Character.toUpperCase(c) : c;
            firstChar = c == ' ';
            result.append(c);
        }
        return result.toString();
    }
}
