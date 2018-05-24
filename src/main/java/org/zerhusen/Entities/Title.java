package org.zerhusen.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "title")
public class Title implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_title")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTitle;

    @Column(name = "title")
    private String title;

    @Column(name = "details")
    private String details;

    public Title() {
    }

    public int getIdTitle() {
        return idTitle;
    }

    public void setIdTitle(int idTitle) {
        this.idTitle = idTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
