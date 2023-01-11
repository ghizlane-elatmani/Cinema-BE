package org.sid.cinema.dao.entities;

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
    @Column(unique = true)
    private int paymentCode;
    private boolean isReserved;
    @ManyToOne
    private Place place;
    @ManyToOne
    private Projection projection;

}
