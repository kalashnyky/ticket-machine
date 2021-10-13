package com.kalashnyky.ticketmachine.service;

import com.kalashnyky.ticketmachine.model.Order;
import com.kalashnyky.ticketmachine.model.TicketType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScreenService {

    private static int ADULT_TICKETS = 0;
    private static int SENIOR_TICKETS = 0;

    private final OrderService orderService;
    private final ReceiptService receiptService;

    public ScreenService(OrderService orderService, ReceiptService receiptService) {
        this.orderService = orderService;
        this.receiptService = receiptService;
    }

    public void handleChoice(String choice) {
        switch (choice) {
            case "1":
                System.out.println("Adult ticket is added");
                ADULT_TICKETS++;
                break;
            case "2":
                System.out.println("Adult ticket is added");
                SENIOR_TICKETS++;
                break;
            case "3":
                printReceipt();
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Wrong choice");
        }
    }

    private void printReceipt() {
        Map<TicketType, Integer> userOrder = new HashMap<>();
        if (ADULT_TICKETS > 0) {
            userOrder.put(TicketType.ADULT, ADULT_TICKETS);
        }
        if (SENIOR_TICKETS > 0) {
            userOrder.put(TicketType.SENIOR, SENIOR_TICKETS);
        }

        Order order = orderService.makeOrder(userOrder);
        String receiptText = receiptService.getReceiptText(order);
        System.out.println(receiptText);

        ADULT_TICKETS = 0;
        SENIOR_TICKETS = 0;
    }
}
