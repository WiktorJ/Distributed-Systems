package configuration;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Created by wiktor on 08/04/16.
 */
public final class Configuration {

    public static final int GENERATORS_NUMBER = 2;
    public static final int SOLVERS_NUMBER = 2;
    public static final int CLIENTS_NUMBER = 2;
    public static final String OPERATION_PROPERTY_NAME = "OPERATION_PROPERTY";
    public static final String ACTIVEMQ_ADDRESS = "tcp://localhost:61616";

    public static final Map<String,OperationProperties> PROPERTIES_MAP = ImmutableMap.<String, OperationProperties>builder()
            .put("+", new OperationProperties("plusTopic", "plusQueue",
                    new Constrains(-Integer.MAX_VALUE, Integer.MAX_VALUE, -Integer.MAX_VALUE, Integer.MAX_VALUE)))
            .put("-", new OperationProperties("minusTopic", "minusQueue",
                    new Constrains(-Integer.MAX_VALUE, Integer.MAX_VALUE, -Integer.MAX_VALUE, Integer.MAX_VALUE)))
            .put("/", new OperationProperties("divideTopic", "divideQueue",
                    new Constrains(-Integer.MAX_VALUE, Integer.MAX_VALUE, -Integer.MAX_VALUE, Integer.MAX_VALUE)))
            .put("*", new OperationProperties("multiplyTopic", "multiplyQueue",
                    new Constrains(-Integer.MAX_VALUE, Integer.MAX_VALUE, -Integer.MAX_VALUE, Integer.MAX_VALUE)))
            .put("^", new OperationProperties("powerTopic", "powerQueue",
                    new Constrains(-Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 20)))
            .build();

    static class OperationProperties {
        private final String topicName;
        private final String queueName;
        private final Constrains constrains;

        public OperationProperties(String topicName, String queueName, Constrains constrains) {
            this.topicName = topicName;
            this.queueName = queueName;
            this.constrains = constrains;
        }

        public String getTopicName() {
            return topicName;
        }

        public String getQueueName() {
            return queueName;
        }

        public Constrains getConstrains() {
            return constrains;
        }
    }

}
