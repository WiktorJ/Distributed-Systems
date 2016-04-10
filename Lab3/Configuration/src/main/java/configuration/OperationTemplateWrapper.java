package configuration;


import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;

/**
 * Created by wiktor on 09/04/16.
 */

public class OperationTemplateWrapper<T extends ActiveMQDestination>  {

    private final String operation;
    private final T destination;
    private final JmsTemplate jmsTemplate;

    public OperationTemplateWrapper(String operation, T destination, ConnectionFactory connectionFactory) {
        this.operation = operation;
        this.destination = destination;
        jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestination(destination);
    }

    public Constrains getConstrains() {
        return Configuration.PROPERTIES_MAP.get(operation).getConstrains();
    }

    public String getOperation() {
        return operation;
    }

    public T getDestination() {
        return destination;
    }

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
}
