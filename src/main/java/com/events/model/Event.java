package com.events.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private LocalDateTime dateCreated;
    private Integer nrParticipants;
    @ManyToOne
    private Category category;
    @Column(name = "image")
    @Lob
    private String imageBase64;


    public Event() {
    }

    public Event(String name, String location, LocalDateTime dateCreated, Integer nrParticipants, Category category) {
        this.name = name;
        this.location = location;
        this.dateCreated = dateCreated;
        this.nrParticipants = nrParticipants;
        this.category = category;
    }
}
