package com.digitall.api.model.citizen;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CARTVALIDATION")
public class CartValidation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int citizen;
    @Column(name = "date_validation")
    private Timestamp dateValidation;
    private int code;
    private int state;
}
