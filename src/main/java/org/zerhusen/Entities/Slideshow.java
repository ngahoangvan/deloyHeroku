package org.zerhusen.Entities;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "slideshow")
public class Slideshow implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_slideshow")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSlideshow;

    @Column(name = "image")
    private String image;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "text")
    private String text;

    public Slideshow() {
    }

    public Slideshow(int idSlideshow, String image, String title, String description, String text) {
        this.idSlideshow = idSlideshow;
        this.image = image;
        this.title = title;
        this.description = description;
        this.text = text;
    }

    public int getIdSlideshow() {
        return idSlideshow;
    }

    public void setIdSlideshow(int idSlideshow) {
        this.idSlideshow = idSlideshow;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
