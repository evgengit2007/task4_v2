package ru.task4v2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.task4v2.model.*;
import ru.task4v2.repo.DataWriter;

import java.util.List;

// запись в БД нормализованных значений
@Component
public class DataWriterImpl implements DataWriter {

    @Autowired
    UserRepo userRepo;

    @Autowired
    LoginRepo loginRepo;

    @Override
    public void doWrite(List<ParseString> parseStringList) {
        System.out.println("doWrite: " + parseStringList);
        User userId;
        List<User> lstUser;
        List<Login> lstLogin;
        for (ParseString str : parseStringList) {
            lstUser = userRepo.findByLogin(str.getLogin());
            if (lstUser.isEmpty()) {
                userId = userRepo.save(new User(str.getLogin(), str.getFio())); // создаем запись в tb_user
            } else {
                userId = lstUser.get(0);
            }
            lstLogin = loginRepo.findLoginsByUserId(userId.getId(), str.getAccessDate());
            if (lstLogin.isEmpty()) {
                loginRepo.save(new Login(userId, str.getAccessDate(), str.getTypeApp())); // создаем запись в tb_login
            }
        }
    }
}
