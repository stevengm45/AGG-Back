package com.agg.certificados.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table (name = "roles")
public class Rol {

    @Id
    private Long rolId;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "rol")
    private Set<UserRol> usersRoles = new HashSet<>();

    public Long getRolId() {
        return rolId;
    }

    public void setRolId(Long rolId) {
        this.rolId = rolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserRol> getUsersRoles() {
        return usersRoles;
    }

    public void setUsersRoles(Set<UserRol> usersRoles) {
        this.usersRoles = usersRoles;
    }

    public Rol(){

    }
}
