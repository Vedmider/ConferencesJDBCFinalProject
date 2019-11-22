package com.study.service.JMS;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static com.study.service.ServiceConstants.LOCALHOST;

public class Producer {
    private static final Logger LOG = LoggerFactory.getLogger(Producer.class);
    private static final ConnectionFactory factory = new ConnectionFactory();

    public static boolean sendMessage(String message, String queueName) {
        factory.setHost(LOCALHOST);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(queueName, false, false, false, null);
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
            LOG.info("Message: {} was sent", message);
            return true;
        } catch (TimeoutException e) {
            LOG.error("Time out exception while creating channel", e);
            return false;
        } catch (IOException e) {
            LOG.error("IOException while creating channel", e);
            return false;
        }
    }
}
