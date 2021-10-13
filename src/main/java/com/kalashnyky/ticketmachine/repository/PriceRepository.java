package com.kalashnyky.ticketmachine.repository;

import com.kalashnyky.ticketmachine.model.TicketType;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PriceRepository {

    private final Map<TicketType, BigDecimal> harcodedMap = buildHarcodedMap();

    public BigDecimal getPriceByTicketType(TicketType ticketType) {
        return harcodedMap.get(ticketType);
    }

    private Map<TicketType, BigDecimal> buildHarcodedMap() {
        Map<TicketType, BigDecimal> prices = new HashMap<>();
        prices.put(TicketType.ADULT, BigDecimal.valueOf(20));
        prices.put(TicketType.SENIOR, BigDecimal.valueOf(8));
        return prices;
    }
}
