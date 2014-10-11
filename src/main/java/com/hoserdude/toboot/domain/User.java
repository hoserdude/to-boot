package com.hoserdude.toboot.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

/**
 * This is our copy of the user information, stored in Postgres (or wherever).
 * Note that we named it different than the classname because postgres is picky
 * about using user for a table name.
 */
@Entity
@Table(name = "tb_user", uniqueConstraints = {@UniqueConstraint(name = "UK_User_userId", columnNames = "userId")})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    /**
     * See unique constraint in Table definition
     */
    @Column
    private String userId;

    @Column
    private String fullName;

    @Column
    private String email;

    public User() {}

    public User(String userId, String fullName, String email) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Everything down here is to be cool with Spring Security Interface we are implementing.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    /**
     * Since we use OpenId, we never store this.
     * @return
     */
    @Override
    public String getPassword() {
        return "";
    }

    /**
     * We're using the unique ID from OpenID
     * @return
     */
    @Override
    public String getUsername() {
        return this.userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
