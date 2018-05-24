package org.zerhusen.Entities.PauEntities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "group_category")
public class GroupCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_group_category")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGroupCategory;

    @Column(name = "category")
    private String category;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @Column(name = "links")
    private String links;

    @OneToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "id_group_category",referencedColumnName ="id_group_category" )
    private Set<Category> listCategory=new HashSet<>();

    public GroupCategory() {
    }

    public int getIdGroupCategory() {
        return idGroupCategory;
    }

    public void setIdGroupCategory(int idGroupCategory) {
        this.idGroupCategory = idGroupCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getLinks() {
        return links;
    }

    public void setLinks(String links) {
        this.links = links;
    }

    public Set<Category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(Set<Category> listCategory) {
        this.listCategory = listCategory;
    }
}
