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

    @Incoming("orders")
    @Outgoing("orders-stats")
    public Multi<OrderStat> computeTopProducts(Multi<ManufactureOrder> orders) {
        return orders.group().by(order -> order.sku)
                .onItem().transformToMultiAndMerge(g -> g.onItem().scan(OrderStat::new, this::incrementOrderCount))
                .invoke(() -> LOGGER.info("Order received. Computed the top product stats"));
    }

    private OrderStat incrementOrderCount(OrderStat orderStat, ManufactureOrder manufactureOrder) {
        orderStat.sku = manufactureOrder.sku;
        orderStat.count = orderStat.count + 1;
        return orderStat;
    }
}
