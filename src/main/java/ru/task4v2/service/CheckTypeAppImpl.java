package ru.task4v2.service;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.task4v2.logging.LogTransformation;
import ru.task4v2.model.ParseString;
import ru.task4v2.model.TypeApp;
import ru.task4v2.repo.Checker;

import java.util.List;

@Component
@Order(2)
@LogTransformation
public class CheckTypeAppImpl implements Checker {
    // проверить тип приложения

    @Override
    public List<ParseString> check(List<ParseString> lst) {
        for (ParseString str : lst) {
            str.setTypeApp(checkTypeApp(str.getTypeApp()));
        }
        return lst;
    }

    private String checkTypeApp(String str) {
        str = str.toUpperCase();
        if (!(str.equals(TypeApp.WEB.toString()) || str.equals(TypeApp.MOBILE.toString()))) {
            str = "others: " + str;
        }
        return str;
    }
}
