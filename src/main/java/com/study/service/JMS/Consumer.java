package com.study.service.JMS;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;


import static com.study.service.ServiceConstants.LOCALHOST;

public class Consumer {
    private static final Logger LOG = LoggerFactory.getLogger(Consumer.class);
    private static final ConnectionFactory factory = new ConnectionFactory();

    public static String getMessage(String queueName){
        factory.setHost(LOCALHOST);
        List<String> list = new ArrayList<>();

        try(Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();) {
            channel.queueDeclare(queueName, false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = (new String(delivery.getBody(), "UTF-8"));
                System.out.println(" [x] Received '" + message + "'");
                list.add(message);
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
        } catch (TimeoutException e) {
            LOG.error("Time out error", e);
        } catch (IOException e) {
            LOG.error("IOException", e);
        }

        return list.get(0);

    }

}
