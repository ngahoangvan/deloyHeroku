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

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
