package ru.task4v2.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

public class ParseString {
    @Getter
    @Setter
    private String login;
    @Getter
    @Setter
    private String fio;
    @Getter
    @Setter
    private Timestamp accessDate;
    @Getter
    @Setter
    private String typeApp;

    public ParseString(String login, String fio, Timestamp accessDate, String typeApp) {
        this.login = login;
        this.fio = fio;
        this.accessDate = accessDate;
        this.typeApp = typeApp;
    }

    @Override
    public String toString() {
        return "ParseString{" +
                "login='" + login + '\'' +
                ", fio='" + fio + '\'' +
                ", accessDate=" + accessDate +
                ", typeApp='" + typeApp + '\'' +
                '}';
    }
}
