package br.com.wendelsegadilha.orderservice.publisher;

import br.com.wendelsegadilha.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.binding.routing.key}")
    private String orderRoutingKey;

    @Value("${rabbitmq.binding.email.routing.key}")
    private String emailRoutingKey;

    private final RabbitTemplate rabbitTemplate;

    public OrderProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order event sent to RabbitMQ => %s", orderEvent));
        rabbitTemplate.convertAndSend(exchange, orderRoutingKey, orderEvent);
        rabbitTemplate.convertAndSend(exchange, emailRoutingKey, orderEvent);
    }
}
