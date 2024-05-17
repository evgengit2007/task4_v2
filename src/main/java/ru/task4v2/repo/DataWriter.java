package ru.task4v2.repo;

import ru.task4v2.model.ParseString;

import java.util.List;

public interface DataWriter {
    void doWrite(List<ParseString> parseStringList);
}
