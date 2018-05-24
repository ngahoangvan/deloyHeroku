package org.zerhusen.model.security;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER_AUTHORITY")
public class UserAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;


    @Column(name = "authority_id")
    private long authorityId;

    public UserAuthority() {
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(long authorityId) {
        this.authorityId = authorityId;
    }
}
