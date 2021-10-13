package com.kalashnyky.ticketmachine.service;

import com.kalashnyky.ticketmachine.model.Order;
import com.kalashnyky.ticketmachine.model.Ticket;
import com.kalashnyky.ticketmachine.model.TicketType;
import com.kalashnyky.ticketmachine.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OrderService {

    private final PriceRepository priceRepository;

    public OrderService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    public Order makeOrder(Map<TicketType, Integer> userOrder) {
        List<Ticket> tickets = userOrder.entrySet().stream()
                .flatMap(e -> {
                    BigDecimal price = priceRepository.getPriceByTicketType(e.getKey());
                    return IntStream.range(0, e.getValue())
                            .mapToObj(i -> new Ticket(e.getKey(), price));
                }).collect(Collectors.toList());

        BigDecimal totalPrice = tickets.stream()
                .map(Ticket::getPrice)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        return new Order(totalPrice, tickets);
    }
}
