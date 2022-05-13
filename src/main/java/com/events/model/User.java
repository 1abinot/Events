package com.events.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "events_users")
public class User {

    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    private String email;



    public User() {
    }

    public User(String username, String password, String name, String surname, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }
}
