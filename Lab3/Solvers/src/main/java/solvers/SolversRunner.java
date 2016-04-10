package solvers;

import com.google.common.collect.Lists;
import configuration.Configuration;
import configuration.Initializer;
import configuration.OperationTemplateWrapper;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by wiktor on 10/04/16.
 */
@Component
public class SolversRunner implements CommandLineRunner {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private Initializer initializer;

    @Resource
    private List<String> supportedOperations;

    @Override
    public void run(String... strings) throws Exception {
        Map<String, OperationTemplateWrapper<ActiveMQTopic>> wrapperMap = initializer.initializeTopics(supportedOperations).stream().
                collect(Collectors.toMap(OperationTemplateWrapper::getOperation, e -> e));
        for (int i = 0; i < Configuration.SOLVERS_NUMBER; i++) {
            Solver solver = new Solver(String.valueOf(i + 1), wrapperMap);
            initializer.initializeQueues(Lists.newArrayList(supportedOperations)).stream().forEach(e -> {
                DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
                defaultMessageListenerContainer.setDestination(e.getDestination());
                defaultMessageListenerContainer.setMessageListener(solver);
                defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
                defaultMessageListenerContainer.initialize();
                defaultMessageListenerContainer.start();
            });
        }
    }
}
