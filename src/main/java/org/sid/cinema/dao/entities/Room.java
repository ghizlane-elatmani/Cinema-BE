package org.sid.cinema.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Room implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int placeNumber;
    @ManyToOne
    private Cinema cinema;
    @OneToMany(mappedBy = "room")
    private Collection<Place> places;
    @OneToMany(mappedBy = "room")
    private Collection<Projection> projections;
}
