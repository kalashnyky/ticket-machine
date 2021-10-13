package com.kalashnyky.ticketmachine.model;

import java.math.BigDecimal;

public class Ticket {

    private final TicketType ticketType;
    private final BigDecimal price;

    public Ticket(TicketType ticketType, BigDecimal price) {
        this.ticketType = ticketType;
        this.price = price;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
