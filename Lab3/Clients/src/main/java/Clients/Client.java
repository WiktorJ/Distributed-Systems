package Clients;

import configuration.Initializer;
import configuration.OperationTemplateWrapper;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.List;

/**
 * Created by wiktor on 10/04/16.
 */
public class Client implements MessageListener {

    private final Logger logger = Logger.getLogger(Client.class);
    private final String id;

    public Client(String id) {
        this.id = id;
    }


    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Client " + id + " received message " +
                    ((TextMessage)message).getText() + " from topic: " + ((ActiveMQTopic)message.getJMSDestination()).getTopicName());
        } catch (JMSException e) {
            logger.warn("Exception in Client", e);
        }
    }
}
