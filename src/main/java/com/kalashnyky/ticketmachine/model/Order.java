package com.kalashnyky.ticketmachine.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private final BigDecimal totalPrice;
    private final List<Ticket> tickets;

    public Order(BigDecimal totalPrice, List<Ticket> tickets) {
        this.totalPrice = totalPrice;
        this.tickets = tickets;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
