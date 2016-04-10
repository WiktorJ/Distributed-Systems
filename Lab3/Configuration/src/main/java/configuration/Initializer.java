package configuration;


import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wiktor on 09/04/16.
 */
@Component
public class Initializer {

    private final Logger logger = Logger.getLogger(Initializer.class);

    @Autowired
    private ConnectionFactory cachingConnectionFactory;

    public List<OperationTemplateWrapper<ActiveMQTopic>> initializeTopics(List<String> operations) {
        return initialize(operations, s -> new ActiveMQTopic(Configuration.PROPERTIES_MAP.get(s).getTopicName()));
    }

    public List<OperationTemplateWrapper<ActiveMQQueue>> initializeQueues(List<String> operations) {
        return initialize(operations, s -> new ActiveMQQueue(Configuration.PROPERTIES_MAP.get(s).getQueueName()));
    }


    private  <T extends ActiveMQDestination> List<OperationTemplateWrapper<T>> initialize(List<String> operations, Function<String, T> dest) {
        return operations.stream()
                .<OperationTemplateWrapper<T>>flatMap
                        (e -> {
                            Configuration.OperationProperties operationProperties = Configuration.PROPERTIES_MAP.get(e);
                            if(operationProperties == null) {
                                logger.warn("Operation " + e + " is not supported!");
                                return Stream.empty();
                            } else {
                               return Stream.of(new OperationTemplateWrapper<>(e,
                                        dest.apply(e), cachingConnectionFactory));
                            }
                        })
                .collect(Collectors.toList());
    }
}
