package org.zerhusen.Entities.PauEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "lesson")
public class Lesson implements Serializable{
    @Id
    @Column(name = "id_lesson")
    private int idLesson;

    @Column(name="name")
    private String name;

    @Column(name = "chapter_idchapter")
    private int idChapter;

    public Lesson() {
    }

    public int getIdLesson() {
        return idLesson;
    }

    public void setIdLesson(int idLesson) {
        this.idLesson = idLesson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }
}
