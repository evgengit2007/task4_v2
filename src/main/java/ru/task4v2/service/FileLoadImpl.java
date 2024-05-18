package ru.task4v2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.task4v2.repo.FileLoader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

// загрузка данных из файлов указанного в настройке каталога (spring.application.pathInput) в List
@Component
public class FileLoadImpl implements FileLoader {

    private String pathInput;

    public FileLoadImpl(@Value("${spring.application.pathInput}") String path) {
        this.pathInput = path;
//        System.out.println(path);
    }

    @Override
    public List<String> fileLoad() {
        List<String> res = new ArrayList<>();
        File file = new File(pathInput);
        File[] files = file.listFiles();
        if (files == null || files.length == 0) return null;
//        Scanner sc;
        for (File fl : files) {
            try {
                // <-- вариант 1
//                System.out.println(fl);
//                sc = new Scanner(fl);
//                while (sc.hasNextLine()) {
//                    res.add(res.size(), sc.nextLine());
//                }
                // --> вариант1
                // --< вариант 2
                List<String> lines = Files.readAllLines(Paths.get(fl.toString()), UTF_8);
                res.addAll(res.size(), lines);
                // --> вариант 2
            } catch (Exception e) {
                System.out.println("Ошибка при чтении файла " + e);
                continue;
            }
        }
//        System.out.println(res);
        return res;
    }
}
