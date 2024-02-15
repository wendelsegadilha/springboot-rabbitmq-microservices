package br.com.wendelsegadilha.stockservice.consumer;

import br.com.wendelsegadilha.stockservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.order.name}"})
    public void consumer(OrderEvent event) {
        LOGGER.info(String.format("Received message => %s", event));
    }

}
