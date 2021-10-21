package com.digitall.api.model.declaration;

import com.digitall.api.model.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "BURGLARY")
public class Bulgary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date_bulgary")
    private Timestamp dateBurglary;
    private float longitude;
    private float latitude;
    private float place;
    private String description;
    @Column(name = "digitall_user")
    private int digitallUser;
}
