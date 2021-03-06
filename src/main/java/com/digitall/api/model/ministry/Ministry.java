package com.digitall.api.model.ministry;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "MINISTRY")
public class Ministry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int state;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "ministry")
    private List<Empowerment> empowermentList;
}
