package com.digitall.api.model.citizen;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PERMIS")
public class Permis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private Date delivrance;
    private String place;
    private int a;
    private int ap;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int citizen;
}
