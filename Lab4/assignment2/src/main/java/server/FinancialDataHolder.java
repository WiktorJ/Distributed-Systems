package server;

import FinancialNews.Currency;
import com.google.common.collect.ImmutableMap;
import org.apache.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wiktor on 25/04/16.
 */
public class FinancialDataHolder {

    public static final Map<Currency, InterestExchangeWrapper> interests = new ConcurrentHashMap<>(3);
    private static final Logger logger = Logger.getLogger(FinancialDataHolder.class);


    static {
        interests.put(Currency.EUR, new InterestExchangeWrapper());
        interests.put(Currency.USD, new InterestExchangeWrapper());
        interests.put(Currency.CHF, new InterestExchangeWrapper());
    }

    public static final Map<assignment2.Currency, Currency> financialCustomCurrency = new ImmutableMap
            .Builder<assignment2.Currency, Currency>()
            .put(assignment2.Currency.CHF, Currency.CHF)
            .put(assignment2.Currency.EUR, Currency.EUR)
            .put(assignment2.Currency.USD, Currency.USD)
            .build();

    public static class InterestExchangeWrapper {
        private Float exchange;
        private Float interest;

        private final Lock exchangeLock = new ReentrantLock();
        private final Lock interestLock = new ReentrantLock();
        private final Condition exchangeCondition = exchangeLock.newCondition();
        private final Condition interestCondition = interestLock.newCondition();

        public InterestExchangeWrapper() {
            exchange = null;
            interest = null;
        }

        public Float getExchange() {
            if (exchange == null) {
                threadSafeGet(exchangeLock, exchangeCondition, exchange);
            }
            return exchange;
        }

        public void setExchange(Float exchange) {
            if (this.exchange == null) {
                try {
                    exchangeLock.lock();
                    this.exchange = exchange;
                    exchangeCondition.signalAll();
                } finally {
                    exchangeLock.unlock();
                }
            } else {
                this.exchange = exchange;
            }
        }

        public Float getInterest() {
            if (interest == null) {
                threadSafeGet(interestLock, interestCondition, interest);
            }
            return interest;
        }

        public void setInterest(Float interest) {
            if (this.interest == null) {
                try {
                    interestLock.lock();
                    this.interest = interest;
                    interestCondition.signalAll();
                } finally {
                    interestLock.unlock();
                }
            } else {
                this.interest = interest;
            }

        }

        private void threadSafeGet(Lock lock, Condition condition, Float toGet) {
            try {
                lock.lock();
                while (toGet == null) {
                    condition.await();
                }
            } catch (InterruptedException e) {
                logger.warn("Interrupt in getInterest", e);
            } finally {
                lock.unlock();
            }
        }

    }


}
