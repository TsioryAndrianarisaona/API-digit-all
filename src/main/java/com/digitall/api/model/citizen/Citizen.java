package com.digitall.api.model.citizen;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CITIZEN")
public class Citizen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "firstname")
    private String firstName;
    private String picture;
    private Date birthday;
    private int sex;
    @Column(name = "qrcode")
    private String qrCode;
    private String phone;
    private String fokontany;
    private String commune;
}
