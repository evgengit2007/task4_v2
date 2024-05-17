package ru.task4v2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.sql.Timestamp;

@Entity
@Table(name = "tb_login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "loginIdSeq")
    @SequenceGenerator(name = "loginIdSeq", sequenceName = "login_id_seq", allocationSize = 1)
    @Getter
    @Setter
    private Long id;

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name = "user_id")
    @Getter
    @Setter
    private User userId;

    @Getter
    @Setter
    @Column(name = "access_date")
    private Timestamp accessdate;

    @Getter
    @Setter
    @Column
    private String application;

    public Login() {
    }

    public Login(User userId, Timestamp accessdate, String application) {
        this.userId = userId;
        this.accessdate = accessdate;
        this.application = application;
    }
}
