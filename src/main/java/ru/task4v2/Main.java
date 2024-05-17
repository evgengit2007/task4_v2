package ru.task4v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.task4v2.service.Conveyer;

@SpringBootApplication
public class Main implements ApplicationRunner {
    @Autowired
    Conveyer conveyer;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        conveyer.start();
    }
}
