package Elis.College.RegistroPranzi.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GeneratorType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.persistence.*;
import java.util.Date;

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
    private State lunch;

    @ManyToOne
    private State dinner;

    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd", style = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "guest_lunch_number")
    private Integer guestLunchNumber;

    @Column(name = "guest_dinner_number")
    private Integer guestDinnerNumber;

}
