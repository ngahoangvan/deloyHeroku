package org.zerhusen.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "footer")
public class Footer implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_footer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFooter;

    @Column(name = "text")
    private String text;

    @Column(name = "icon")
    private String icon;

    public Footer() {
    }

    public Footer(int idFooter, String text, String icon) {
        this.idFooter = idFooter;
        this.text = text;
        this.icon = icon;
    }

    public int getIdFooter() {
        return idFooter;
    }

    public void setIdFooter(int idFooter) {
        this.idFooter = idFooter;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
