package server;

import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.Object;
import Ice.ObjectAdapter;
import Ice.UserException;
import com.fasterxml.jackson.databind.ObjectMapper;
import impl.Counter;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by wiktor on 24/04/16.
 */
public class POneLocator extends AbstractLocator {

    private static final Logger logger = Logger.getLogger(POneLocator.class);
    private static final String repoLocation = "src/main/resources";

    private Ice.ObjectAdapter adapter;
    private ObjectMapper objectMapper;

    public POneLocator(String id, ObjectAdapter adapter) {
        super(id);
        this.adapter = adapter;
        objectMapper = new ObjectMapper();
        System.out.println("## POneLocator(" + id + ") ##");
    }

    @Override
    public synchronized Object locate(Current current, LocalObjectHolder localObjectHolder) throws UserException {
        Counter counter;
        try {
            counter = objectMapper.readValue(new File(repoLocation + current.id.name), Counter.class);
        } catch (IOException e) {
            logger.warn("No previous state for object " + current.id.name + " new one will be created", e);
            counter = new Counter();
        }
        adapter.add(counter, current.id);
        return counter;
    }

}
