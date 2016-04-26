package server;

import Evictor.EvictorBase;
import Ice.Current;
import Ice.LocalObjectHolder;
import Ice.Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import impl.Customer;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by wiktor on 24/04/16.
 */
public class BankServantEvictor extends EvictorBase {

    private static final Logger logger = Logger.getLogger(BankServantEvictor.class);
    private static final String repoLocation = "src/main/backups/";

    private ObjectMapper objectMapper;

    public BankServantEvictor(int size) {
        super(size);
        objectMapper = new ObjectMapper();
    }

    @Override
    public Object add(Current c, LocalObjectHolder cookie) {
        Customer customer;
        cookie.value = c;
        try {
            customer = objectMapper.readValue(new File(repoLocation + c.id.name), Customer.class);
        } catch (IOException e) {
            logger.warn("No previous state for object " + c.id.name + " new one will be created", e);
            customer = randCustomer();
        }
        return customer;
    }

    @Override
    public void evict(Object servant, java.lang.Object cookie) {
        try {
            objectMapper.writeValue(new File(repoLocation + ((Current) cookie).id.name), servant);
        } catch (IOException e) {
            logger.warn("Couldn't create backup for " + ((Current) cookie).id.name, e);
        }
    }

    private Customer randCustomer() {
        long beginTime = Timestamp.valueOf("2011-01-01 00:00:00").getTime();
        long endTime = Timestamp.valueOf("2015-12-31 00:58:00").getTime();
        long diff = endTime - beginTime + 1;
        long rand = beginTime + (long) (Math.random() * diff);
        return new Customer(LocalDateTime.ofInstant(new Date(rand).toInstant(), ZoneOffset.UTC), new Random().nextInt(10));
    }

}
