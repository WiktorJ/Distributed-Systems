package server;

import Evictor.EvictorBase;
import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.Object;
import Ice.UserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import impl.Counter;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by wiktor on 24/04/16.
 */
public class PFiveLocator extends EvictorBase {

    private static final Logger logger = Logger.getLogger(POneLocator.class);
    private static final String repoLocation = "src/main/resources/";

    private ObjectMapper objectMapper;

    public PFiveLocator(String id, int size) {
        super(size);
        objectMapper = new ObjectMapper();
        System.out.println("## PTwoLocator(" + id + ") ##");
    }


    @Override
    public Object add(Current c, LocalObjectHolder cookie) {
        cookie.value = c;
        Counter counter;
        try {
            counter = objectMapper.readValue(new File(repoLocation + c.id.name), Counter.class);
        } catch (IOException e) {
            logger.warn("No previous state for object " + c.id.name + " new one will be created", e);
            counter = new Counter();
        }
        return counter;
    }

    @Override
    public void evict(Object servant, java.lang.Object cookie) {
        try {
            objectMapper.writeValue(new File(repoLocation + ((Current)cookie).id.name), servant);
        } catch (IOException e) {
            logger.warn("Couldn't create backup for " + ((Current)cookie).id.name, e);
        }
    }
}
