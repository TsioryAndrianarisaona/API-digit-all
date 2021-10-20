package com.digitall.api.model.user;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "DIGITALL_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String name;
    @Column(name="firstname")
    private String firstName;
    private String role;
    private int ministry;

    @OneToMany
    private List<Token> tokenList;
}
