package ru.task4v2.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface LoginRepo extends JpaRepository<Login, Long> {

    @Query(value = "SELECT * FROM tb_login where user_id=?1 and access_date=?2", nativeQuery = true)
    List<Login> findLoginsByUserId(long userId, Timestamp ts);
}
