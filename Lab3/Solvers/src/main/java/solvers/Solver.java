package solvers;


import configuration.Configuration;
import configuration.OperationTemplateWrapper;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.log4j.Logger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.List;
import java.util.Map;

/**
 * Created by wiktor on 09/04/16.
 */
public class Solver implements MessageListener {

    private final Logger logger = Logger.getLogger(Solver.class);
    private final String id;
    private final Map<String, OperationTemplateWrapper<ActiveMQTopic>> wrappers;
    private final ScriptEngine engine;

    public Solver(String id, Map<String, OperationTemplateWrapper<ActiveMQTopic>> wrappers) {
        this.id = id;
        this.wrappers = wrappers;
        ScriptEngineManager manager = new ScriptEngineManager();
        engine = manager.getEngineByName("js");
        System.out.println("Solver " + id + "  started");
    }


    @Override
    public void onMessage(Message message) {
        try {
            OperationTemplateWrapper<ActiveMQTopic> activeMQDestinationOperationTemplateWrapper =
                    wrappers.get(message.getStringProperty(Configuration.OPERATION_PROPERTY_NAME));
            if (activeMQDestinationOperationTemplateWrapper == null) {
                logger.error("This Solver do not support " + message.getStringProperty(Configuration.OPERATION_PROPERTY_NAME) + " operation " +
                        "but it is listening to queue that provide one. This situation should never occur and its signal of serious configuration issue. " +
                        "This message will perish.");
            } else {
                String result = engine.eval(((TextMessage) message).getText()).toString();
                activeMQDestinationOperationTemplateWrapper.getJmsTemplate()
                        .send(session -> session.createTextMessage(result));
            }
        } catch (JMSException | ScriptException e) {
            logger.warn("Exception in lister", e);
        }
    }
}
