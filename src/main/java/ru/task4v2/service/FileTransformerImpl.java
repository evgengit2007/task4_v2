package ru.task4v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.task4v2.model.ParseString;
import ru.task4v2.repo.Loader;
import ru.task4v2.repo.Transformer;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// обработчик данных, полученных из файла
// возвращает массив строк с нормализированной датой и выброшены строки заголовки,
// строки с пустым или несоответствующим формату значением даты входа перемещены в файл с ошибками
@Component
public class FileTransformerImpl implements Transformer {

    private String pathErrOutput;

    @Autowired
    Loader fl;

    public FileTransformerImpl(@Value("${spring.application.pathOutput}") String path) {
        this.pathErrOutput = path;
    }

    @Override
    public List<ParseString> prepareList() {
        List<ParseString> res = new ArrayList<>(); // массив объектов распарсенных строк
        List<String> err = new ArrayList<>();
        Timestamp dt;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss[.SSS]");
        for (String str : fl.load()) {
            // проверить наличие даты, если нет, то запись помещается в лог файл с ошибкой
            // полученный результат кладем в res
//            System.out.println(str);
            if (str.isBlank()) continue;
            if (str.startsWith("Логин")) continue; // если 1 строка начинается на Логин, игнорируем
            String[] strField = str.split(";");
            try {
                LocalDateTime localDateTime = LocalDateTime.from(formatter.parse(strField[2]));
                dt = Timestamp.valueOf(localDateTime);
                res.add(res.size(), new ParseString(strField[0], strField[1], dt, strField[3]));
            } catch (Exception e) {
                // строки с ошибкой пишем в лог с ошибкой
                err.add(err.size(), str);
            }
        }
        if (!err.isEmpty()) {
            Path dirPath = Paths.get(pathErrOutput);
            try {
                Files.createDirectories(dirPath);
                FileWriter fw;
                try {
                    fw = new FileWriter(pathErrOutput + "\\error.log");
                    for (String s : err) {
                        fw.write(s + "\n");
                    }
                    fw.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.out.println("err = " + err);
        return res;
    }
}
