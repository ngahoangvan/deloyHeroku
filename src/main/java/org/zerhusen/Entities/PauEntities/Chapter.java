package org.zerhusen.Entities.PauEntities;

import com.fasterxml.jackson.annotation.JsonFilter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chapter")
@JsonFilter("filter.Chapter")
public class Chapter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_chapter")
    private int idChapter;
    @Column(name = "chapter")
    private String chapter;
    @Column(name = "course_idcourse")
    private int idCourse;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chapter_idchapter",referencedColumnName ="id_chapter" )
    private Set<Lesson> listLesson=new HashSet<>();

    public Chapter() {
    }

    public Set<Lesson> getListLesson() {
        return listLesson;
    }

    public void setListLesson(Set<Lesson> listLesson) {
        this.listLesson = listLesson;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }
}
