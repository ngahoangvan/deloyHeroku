package org.zerhusen.Entities.PauEntities;



import com.fasterxml.jackson.annotation.JsonFilter;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
@JsonFilter("filter.Category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private int idCategory;

    @Column(name = "category")
    private String category;

    @Column(name = "create_at")
    private String createAt;

    @Column(name = "update_at")
    private String updateAt;

    @Column (name="links")
    private String links;
    @Column(name = "id_group_category")
    private int idGroupCategory;


    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "category_id_category",referencedColumnName ="id_category" )
    private Set<Trainer> listTrainer=new HashSet<>();


    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "category_id_category",referencedColumnName ="id_category" )
    private Set<Course> listCourse=new HashSet<>();


    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "category_id_category",referencedColumnName ="id_category" )
    private Set<News> ListNews= new HashSet<>();

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "category_id_category", referencedColumnName = "id_category")
    private Set<Events> ListEvent = new HashSet<>();


    public Category() {
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public int getIdGroupCategory() {
        return idGroupCategory;
    }

    public void setIdGroupCategory(int idGroupCategory) {
        this.idGroupCategory = idGroupCategory;
    }

    public Set<Events> getListEvent() {
        return ListEvent;
    }

    public void setListEvent(Set<Events> listEvent) {
        ListEvent = listEvent;
    }

    public Set<News> getListNews() {
        return ListNews;
    }

    public void setListNews(Set<News> listNews) {
        ListNews = listNews;
    }

    public Set<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(Set<Course> listCourse) {
        this.listCourse = listCourse;
    }

    public Set<Trainer> getListTrainer() {
        return listTrainer;
    }

    public void setListTrainer(Set<Trainer> listTrainer) {
        this.listTrainer = listTrainer;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
