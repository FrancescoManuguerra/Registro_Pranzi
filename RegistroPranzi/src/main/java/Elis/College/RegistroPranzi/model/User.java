package Elis.College.RegistroPranzi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @JsonIgnore
    private String password;
    private Integer lable_number;

    @JsonIgnore
    @ManyToOne
    private Authority id_authority;

}
