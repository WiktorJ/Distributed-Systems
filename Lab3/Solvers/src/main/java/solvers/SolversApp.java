package solvers;

import com.google.common.collect.Lists;
import configuration.Configuration;
import configuration.Initializer;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.util.List;

/**
 * Created by wiktor on 10/04/16.
 */
@SpringBootApplication
public class SolversApp {

    @Bean
    CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(new ActiveMQConnectionFactory("tcp://localhost:61616"));
        cachingConnectionFactory.setSessionCacheSize(10);
        return cachingConnectionFactory;
    }

    @Bean
    List<String> supportedOperations() {
        return Lists.newArrayList(Configuration.PROPERTIES_MAP.keySet());
    }

    @Bean
    Initializer initializer() {
        return new Initializer();
    }

    public static void main(String[] args) {
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
        SpringApplication.run(SolversApp.class, args);
    }
}
