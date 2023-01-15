package org.sid.cinema.dao.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "ticketProj", types = {org.sid.cinema.dao.entities.Ticket.class})
public interface TicketProject {

     Long getId();
     String getNameCustomer();
     double getPrice();
     Integer getPaymentCode();
     boolean getReserved();
     Place getPlace();

}
