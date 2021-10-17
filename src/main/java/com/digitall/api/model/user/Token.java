package com.digitall.api.model.user;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TOKEN")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int digitall_user;
    private String token;
    private Timestamp validity;
    private int state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "digitall_user",insertable = false,updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
     private User user;
}
