package org.zerhusen.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "slogan")
public class Slogan implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_slogan")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSlogan;

    @Column(name = "name")
    private String name;

    public Slogan() {
    }

    public int getIdSlogan() {
        return idSlogan;
    }

    public void setIdSlogan(int idSlogan) {
        this.idSlogan = idSlogan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
