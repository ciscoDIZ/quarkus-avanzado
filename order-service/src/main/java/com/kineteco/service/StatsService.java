package com.kineteco.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kineteco.model.ManufactureOrder;
import com.kineteco.model.OrderStat;
import io.smallrye.mutiny.Multi;
import io.vertx.core.impl.ConcurrentHashSet;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class StatsService {
    private static final Logger LOGGER = Logger.getLogger(StatsService.class);

    private final Map<String, OrderStat> stats = new HashMap<>();

    @Inject
    ObjectMapper mapper;

    @Incoming("orders")
    @Outgoing("orders-stats")
    public Multi<Collection<String>> computeTopProducts(Multi<ManufactureOrder> orders) {
        return orders.group().by(order -> order.sku)
                .onItem().transformToMultiAndMerge(g -> g.onItem().scan(OrderStat::new, this::incrementOrderCount))
                .onItem().transform(this::onNewStat)
                .invoke(() -> LOGGER.info("Order received. Computed the top product stats"));
    }

    private Collection<String> onNewStat(OrderStat stat) {
        if (stat.sku != null) {
            stats.put(stat.sku, stat);
        }

        return stats.values().stream()
                .sorted(Comparator.comparingInt(s-> -1 * s.count))
                .map(this::transformJson)
                .collect(Collectors.toUnmodifiableList());
    }

    private String transformJson(OrderStat stat) {
        try {
            return mapper.writeValueAsString(stat);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private OrderStat incrementOrderCount(OrderStat orderStat, ManufactureOrder manufactureOrder) {
        orderStat.sku = manufactureOrder.sku;
        orderStat.count = orderStat.count + 1;
        return orderStat;
    }
}