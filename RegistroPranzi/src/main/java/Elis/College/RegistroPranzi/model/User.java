package Elis.College.RegistroPranzi.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "user")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    private String name;

    private String surname;

    private String number;

    private String email;

    private String password;

    private Integer lable_number;

    @ManyToOne
    private Authority id_authority;

}
