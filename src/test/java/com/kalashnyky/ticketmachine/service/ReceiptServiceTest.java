package com.kalashnyky.ticketmachine.service;

import com.kalashnyky.ticketmachine.model.Order;
import com.kalashnyky.ticketmachine.model.Ticket;
import com.kalashnyky.ticketmachine.model.TicketType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ReceiptServiceTest {

    private static final String RECEIPT_TEXT =
                    "Receipt for Willy Wonka Movie Theatre\n\n" +
                    "ADULT Ticket:              Price: £20.00\n" +
                    "SENIOR Ticket:             Price:  £8.00\n" +
                    "SENIOR Ticket:             Price:  £8.00\n" +
                    "                           TOTAL: £36.00\n\n" +
                    "Thank You for your custom, enjoy the movie!";


    @InjectMocks
    private ReceiptService target;

    @Test
    public void getReceiptText() {
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(TicketType.ADULT, BigDecimal.valueOf(20)));
        tickets.add(new Ticket(TicketType.SENIOR, BigDecimal.valueOf(8)));
        tickets.add(new Ticket(TicketType.SENIOR, BigDecimal.valueOf(8)));
        String receiptText = target.getReceiptText(new Order(BigDecimal.valueOf(36), tickets));
        assertThat(receiptText).isEqualTo(RECEIPT_TEXT);
    }
}
