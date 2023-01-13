package org.sid.cinema.dao.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Ticket implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCustomer;
    private double price;
    @Column(unique = false, nullable = true)
    private Integer paymentCode;
    private boolean isReserved;
    @ManyToOne
    private Place place;
    @ManyToOne
    private Projection projection;

}
