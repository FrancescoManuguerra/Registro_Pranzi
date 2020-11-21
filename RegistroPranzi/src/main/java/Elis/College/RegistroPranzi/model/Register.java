package Elis.College.RegistroPranzi.model;


import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GeneratorType;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.*;
import java.sql.Date;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "register")
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private State breakfast;

    @ManyToOne
    private State lunch;

    @ManyToOne
    private State dinner;

    @Column(name = "date")
    private Date date;

}
