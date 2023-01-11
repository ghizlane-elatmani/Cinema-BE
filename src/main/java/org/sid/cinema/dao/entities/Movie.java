package org.sid.cinema.dao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Movie implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String producer;
    private Date releaseDate;
    private double duration;
    private String photo;
    @OneToMany(mappedBy = "movie")
    private Collection<Projection> projections;
    @ManyToOne
    private Category category;
}
