package com.fbs.flightfareservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fbs.flightfareservice.config.MessagingConfig;
import com.fbs.flightfareservice.models.PaymentStatus;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(PaymentStatus paymentStatus) {
        System.out.println("Message recieved from queue : " + paymentStatus);
    }
}
