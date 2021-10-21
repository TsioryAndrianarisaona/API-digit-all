package com.digitall.api.model.user;


import com.digitall.api.model.ministry.Ministry;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ministry",insertable = false,updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ministry ministryObject;
}
