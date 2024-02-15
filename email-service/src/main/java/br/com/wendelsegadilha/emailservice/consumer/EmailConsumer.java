package br.com.wendelsegadilha.emailservice.consumer;

import br.com.wendelsegadilha.emailservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {

    private final Logger LOGGER = LoggerFactory.getLogger(EmailConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.email.name}"})
    public void consumer(OrderEvent event) {
        LOGGER.info(String.format("Received message => %s", event));
    }

}
