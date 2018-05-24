package org.zerhusen.Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DateTimeException;
import java.util.Date;

@Entity
@Table(name = "feedback")
public class Feedback implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_feedback")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFeedback;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "image")
    private String image;

    @Column(name = "message")
    private String message;

    public Feedback() {
    }

    public int getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(int idFeedback) {
        this.idFeedback = idFeedback;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
