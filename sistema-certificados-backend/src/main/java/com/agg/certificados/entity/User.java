package com.agg.certificados.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String full_name;
    private String email;
    private Date create_date;
    private boolean enabled = true;
    private Long number_id;
    @ManyToOne
    @JoinColumn(name = "type_document_id")
    public TypeDocument type_document_id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnore
    private Set<UserRol> userRoles = new HashSet<>();

    public User(){

    }

    public User(Long id, String username, String password, String full_name, String email, Date create_date, boolean enabled, Long number_id, TypeDocument type_document_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.full_name = full_name;
        this.email = email;
        this.create_date = create_date;
        this.enabled = enabled;
        this.number_id = number_id;
        this.type_document_id = type_document_id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> authorities = new HashSet<>();
        this.userRoles.forEach(userRol -> {
            authorities.add(new Authority(userRol.getRol().getRolName()));
        });
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Long getNumber_id() {
        return number_id;
    }

    public void setNumber_id(Long number_id) {
        this.number_id = number_id;
    }

    public TypeDocument getType_document_id() {
        return type_document_id;
    }

    public void setType_document_id(TypeDocument type_document_id) {
        this.type_document_id = type_document_id;
    }

    public Set<UserRol> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRol> userRoles) {
        this.userRoles = userRoles;
    }


}
