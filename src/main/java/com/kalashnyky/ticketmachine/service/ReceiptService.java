package com.kalashnyky.ticketmachine.service;

import com.kalashnyky.ticketmachine.model.Order;
import com.kalashnyky.ticketmachine.model.Ticket;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ReceiptService {
    private final String CURRENCY = "£";
    private final int LINE_SIZE = 40;

    public String getReceiptText(Order order) {
        StringBuilder content = new StringBuilder();
        addHeaderToContent(content);
        addPricesToContent(content, order.getTickets());
        addTotalPriceToContent(content, order.getTotalPrice());
        addFooterToContent(content);
        return content.toString();
    }

    private void addHeaderToContent(StringBuilder content) {
        content.append("Receipt for Willy Wonka Movie Theatre\n\n");
    }

    private void addPricesToContent(StringBuilder content, List<Ticket> tickets) {
        tickets.forEach(t -> {
            String ticketTypeText = t.getTicketType().name() + " Ticket:";
            String priceText = "Price:" + String.format("%7s", CURRENCY + t.getPrice().setScale(2, RoundingMode.HALF_UP));
            content.append(ticketTypeText);
            addEmptySpaces(content,LINE_SIZE - ticketTypeText.length() - priceText.length());
            content.append(priceText).append("\n");
        });
    }

    private void addTotalPriceToContent(StringBuilder content, BigDecimal total) {
        String totalText = "TOTAL: " + CURRENCY + total.setScale(2, RoundingMode.HALF_UP);
        addEmptySpaces(content, LINE_SIZE - totalText.length());
        content.append(totalText).append("\n\n");
    }

    private void addEmptySpaces(StringBuilder content, int amount) {
        IntStream.range(0, amount).forEach(i -> content.append(" "));
    }

    private void addFooterToContent(StringBuilder content) {
        content.append("Thank You for your custom, enjoy the movie!");
    }
}
