package ru.task4v2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdSeq")
    @SequenceGenerator(name = "userIdSeq", sequenceName = "user_id_seq", allocationSize = 1)
    @Getter
    private Long id;

    @Getter
    @Setter
    @Column
    private String login;

    @Getter
    @Setter
    @Column
    private String fio;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Login> logins;

    public User() {
    }

    public User(String login, String fio) {
        this.login = login;
        this.fio = fio;
    }
}
