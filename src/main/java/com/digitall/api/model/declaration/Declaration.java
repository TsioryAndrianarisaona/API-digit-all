package com.digitall.api.model.declaration;

import com.digitall.api.model.ministry.Ministry;
import com.digitall.api.model.user.User;
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
@Table(name = "DECLARATION")
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int citizen;
    private String title;
    private String content;
    private float longitude;
    private float latitude;
    @Column(name = "digitall_user")
    private int digitallUser;
    private int ministry;
    @Column(name = "date_declaration")
    private Timestamp dateDeclaration;
    private int state;
    @Transient
    private String qrCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "digitall_user",insertable = false,updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ministry",insertable = false,updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ministry ministryObject;
}
