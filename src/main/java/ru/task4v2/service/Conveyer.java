package ru.task4v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task4v2.model.ParseString;
import ru.task4v2.repo.Checker;
import ru.task4v2.repo.DataWriter;
import ru.task4v2.repo.Transformer;

import java.util.List;

@Component
public class Conveyer {

    @Autowired
    Transformer ft;

    @Autowired
    DataWriter dw;

    @Autowired
    List<Checker> checker;


    public void start() {
        System.out.println("---------Start Spring Task4----------");
        List<ParseString> lst = ft.prepareList(); // проверяем корректность даты и что она не пустая
//        System.out.println(lst);
        for (Checker ch : checker) {
            // пробежимся по проверкам в соответствии с установкой сортировки @ORDER(значение)
            // Таких проверок у нас 2: на ФИО и тип приложения
            lst = ch.check(lst);
//            System.out.println("---1---" + lst);
        }
        // запишем в БД обработанные строки
        dw.doWrite(lst);
        System.out.println("---------End Spring Task4----------");
    }
}
