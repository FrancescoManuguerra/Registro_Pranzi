package Elis.College.RegistroPranzi.model;

import javax.persistence.*;

@Entity(name = "Authority")
public class Authority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "authority_type")
    private String authority_type;
}
