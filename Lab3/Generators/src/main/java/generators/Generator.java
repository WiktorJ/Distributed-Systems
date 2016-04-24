package generators;

import com.google.common.collect.Lists;
import configuration.Configuration;
import configuration.OperationTemplateWrapper;
import configuration.Initializer;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.jms.TextMessage;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by wiktor on 09/04/16.
 */
public class Generator implements Runnable{


    private List<OperationTemplateWrapper<ActiveMQQueue>> wrappers;
    private String id;

    public Generator(Initializer initializer, String id, List<String> supportedOperations) {
        this.id = id;
        System.out.println("Generator " + id + "  started");
        wrappers = initializer.initializeQueues(supportedOperations);
    }

    @Override
    public void run() {
        while (true) {
            OperationTemplateWrapper<ActiveMQQueue> wrapper =
                    wrappers.get(ThreadLocalRandom.current().nextInt(0, wrappers.size()));
            int leftOp = ThreadLocalRandom.current().nextInt(wrapper.getConstrains().getLeftOperandMin(), wrapper.getConstrains().getLeftOperandMax());
            int rightOP = ThreadLocalRandom.current().nextInt(wrapper.getConstrains().getRightOperandMin(), wrapper.getConstrains().getRightOperandMax());
            String exp = "(" + leftOp + ")" + wrapper.getOperation() + "(" + rightOP + ")" ;

            wrapper.getJmsTemplate().send(session -> {
                TextMessage textMessage = session.createTextMessage(exp);
                textMessage.setStringProperty(Configuration.OPERATION_PROPERTY_NAME, wrapper.getOperation());
                return textMessage;
            });
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

}
