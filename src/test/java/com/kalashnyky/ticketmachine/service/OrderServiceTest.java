package com.kalashnyky.ticketmachine.service;

import com.kalashnyky.ticketmachine.model.Order;
import com.kalashnyky.ticketmachine.model.TicketType;
import com.kalashnyky.ticketmachine.repository.PriceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService target;

    @Spy
    private PriceRepository priceRepository;

    @Test
    public void makeOrder_TwoAdultTickets() {
        Order order = target.makeOrder(Collections.singletonMap(TicketType.ADULT, 2));
        assertThat(order.getTotalPrice().intValue()).isEqualTo(40);
    }

    @Test
    public void makeOrder_ThreeSeniorTickets() {
        Order order = target.makeOrder(Collections.singletonMap(TicketType.SENIOR, 3));
        assertThat(order.getTotalPrice().intValue()).isEqualTo(24);
    }

    @Test
    public void makeOrder_OneAdultTicketTwoSeniorTickets() {
        Map<TicketType, Integer> userOrder = new HashMap<>();
        userOrder.put(TicketType.ADULT, 1);
        userOrder.put(TicketType.SENIOR, 2);

        Order order = target.makeOrder(userOrder);
        assertThat(order.getTotalPrice().intValue()).isEqualTo(36);
    }

    @Test
    public void makeOrder_EmptyOrder() {
        Order order = target.makeOrder(new HashMap<>());
        assertThat(order.getTotalPrice().intValue()).isEqualTo(0);
    }
}
