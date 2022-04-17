package org.melita.service;

import org.melita.config.QueueConfig;
import org.melita.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PlaceOrderService {

    public static final Logger log = LoggerFactory.getLogger(PlaceOrderService.class);
    private QueueConfig queueConfig;

    public PlaceOrderService(QueueConfig queueConfig) {
        this.queueConfig = queueConfig;
    }

    public void pushEventToQueue(Order order) {
        this.queueConfig.order().send(message(order));
    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}
