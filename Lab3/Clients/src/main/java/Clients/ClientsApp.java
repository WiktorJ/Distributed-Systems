package Clients;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import configuration.Configuration;
import configuration.Initializer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by wiktor on 09/04/16.
 */
@SpringBootApplication
public class ClientsApp {


    @Bean
    CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(new ActiveMQConnectionFactory(Configuration.ACTIVEMQ_ADDRESS));
        cachingConnectionFactory.setSessionCacheSize(2);
        return cachingConnectionFactory;
    }

    @Bean
    Initializer initializer() {
        return new Initializer();
    }

    @Bean
    List<ClientConfig> clientsConfig() {
        List<ClientConfig> res = new ArrayList<>();
        List<String> operations = Lists.newArrayList(Configuration.PROPERTIES_MAP.keySet());
        for (int i = 0; i < Configuration.CLIENTS_NUMBER; i++) {
            Collections.shuffle(operations);
            res.add(new ClientConfig(operations.subList(0, ThreadLocalRandom.current().nextInt(operations.size()/3, operations.size())),
                    ThreadLocalRandom.current().nextBoolean()));
        }
        return res;
    }


    public static void main(String[] args) {
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
        SpringApplication.run(ClientsApp.class, args);
    }

}
