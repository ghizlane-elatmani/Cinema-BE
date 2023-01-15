package org.sid.cinema.web;

import jakarta.transaction.Transactional;
import lombok.Data;
import org.sid.cinema.dao.MovieRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.dao.entities.Movie;
import org.sid.cinema.dao.entities.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping(path = "/movieImage/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws IOException {
        Movie movie = movieRepository.findById(id).get();
        String photoName = movie.getPhoto();
        File file = new File(System.getProperty("user.home") + "/" + "photo" +"/" + movie.getPhoto());
        Path path = Paths.get(file.toURI());
        //Path p = Paths.get(System.getProperty("user:home"), "photo", movie.getPhoto());
        return Files.readAllBytes(path);
    }

    @PostMapping("/payTickets")
    @Transactional
    public List<Ticket> payTickets(@RequestBody TicketFrom ticketFrom){
        List<Ticket> ticketList = new ArrayList<>();
        ticketFrom.getTickets().forEach(idTicket -> {
            Ticket ticket = ticketRepository.findById(idTicket).get();
            ticket.setNameCustomer(ticketFrom.getNameCustomer());
            ticket.setReserved(true);
            ticket.setPaymentCode(ticketFrom.getPaymentCode());
            ticketRepository.save(ticket);
            ticketList.add(ticket);
        });
        return ticketList;
    }
}
@Data
class TicketFrom {
    private String nameCustomer;
    private int paymentCode;
    private List<Long> tickets = new ArrayList<>();
}