package org.zerhusen.Entities.PauEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "course_has_trainer")
public class TrainerCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "trainer_idtrainer")
    private int idTrainer;

    @Id
    @Column(name = "course_idcourse")
    private int idCourse;

    public TrainerCourse() {
    }

    public int getIdTrainer() {
        return idTrainer;
    }

    public void setIdTrainer(int idTrainer) {
        this.idTrainer = idTrainer;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
