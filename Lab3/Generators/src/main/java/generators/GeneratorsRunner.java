package generators;

import configuration.Configuration;
import configuration.Initializer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wiktor on 10/04/16.
 */
@Component
public class GeneratorsRunner implements CommandLineRunner{
    private final Logger logger = Logger.getLogger(GeneratorsRunner.class);

    @Autowired
    private Initializer initializer;

    @Resource
    private List<String> supportedOperations;

    @Override
    public void run(String... strings) throws Exception {
        for (int i = 0; i < Configuration.GENERATORS_NUMBER; i++) {
            new Thread(new Generator(initializer, String.valueOf(i+1), supportedOperations)).start();
        }
    }
}
