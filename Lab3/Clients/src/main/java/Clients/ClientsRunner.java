package Clients;

import configuration.Initializer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import java.util.List;

/**
 * Created by wiktor on 10/04/16.
 */
@Component
public class ClientsRunner implements CommandLineRunner {

    private final Logger logger = Logger.getLogger(ClientsRunner.class);

    private final static String ACTIVEMQ_CLIENTID = "SampleId2";

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private Initializer initializer;

    @Resource
    private List<ClientConfig> clientsConfig;

    @Override
    public void run(String... strings) throws Exception {
        for (int i = 0; i < clientsConfig.size(); i++) {
            Client client = new Client(String.valueOf(i + 1));
            final int finalI = i;
            System.out.print("Client " + (finalI + 1) + " Will subscribe to operations: ");
            clientsConfig.get(finalI).getSubscribeChannels().stream().forEach(s -> System.out.print(s + " "));
            System.out.println("Client is durable: " + clientsConfig.get(finalI).isDurableSubscription());
            connectionFactory.setClientId(ACTIVEMQ_CLIENTID);
            connectionFactory.resetConnection();
            initializer.initializeTopics(clientsConfig.get(i).getSubscribeChannels()).stream().forEach(e -> {
                DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
                defaultMessageListenerContainer.setDestination(e.getDestination());
                defaultMessageListenerContainer.setMessageListener(client);
                defaultMessageListenerContainer.setConnectionFactory(connectionFactory);
                if (clientsConfig.get(finalI).isDurableSubscription()) {
                    defaultMessageListenerContainer.setSubscriptionDurable(clientsConfig.get(finalI).isDurableSubscription());
                    defaultMessageListenerContainer.setSubscriptionName(String.valueOf(finalI + 1 + e.getOperation()));
                }
                defaultMessageListenerContainer.initialize();
                defaultMessageListenerContainer.start();
            });
        }
    }
}
