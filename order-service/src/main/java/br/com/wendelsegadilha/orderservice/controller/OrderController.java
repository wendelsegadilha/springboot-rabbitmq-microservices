package br.com.wendelsegadilha.orderservice.controller;

import br.com.wendelsegadilha.orderservice.dto.Order;
import br.com.wendelsegadilha.orderservice.dto.OrderEvent;
import br.com.wendelsegadilha.orderservice.publisher.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent event = new OrderEvent();
        event.setStatus("PENDING");
        event.setMessage("Order is in pending status");
        event.setOrder(order);
        orderProducer.sendMessage(event);

        return ResponseEntity.status(HttpStatus.CREATED).body("Order sent to the RabbitMQ...");
    }
}
