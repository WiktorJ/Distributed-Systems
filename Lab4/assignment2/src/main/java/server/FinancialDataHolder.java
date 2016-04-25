package server;

import FinancialNews.Currency;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wiktor on 25/04/16.
 */
public class FinancialDataHolder {

    public static final Map<Currency, InterestExchangeWrapper> interests = new ConcurrentHashMap<>(3);
    static {
        interests.put(Currency.EUR, new InterestExchangeWrapper());
        interests.put(Currency.USD, new InterestExchangeWrapper());
        interests.put(Currency.CHF, new InterestExchangeWrapper());
    }

    public static final Map<assignment2.Currency, Currency> financialCustomCurrency = new ImmutableMap
            .Builder<assignment2.Currency, Currency> ()
            .put(assignment2.Currency.CHF, Currency.CHF)
            .put(assignment2.Currency.EUR, Currency.EUR)
            .put(assignment2.Currency.USD, Currency.USD)
            .build();

    public static class InterestExchangeWrapper {
        private Float exchange;
        private Float interest;

        public InterestExchangeWrapper() {
            exchange = null;
            interest = null;
        }

        public Float getExchange() {
            return exchange;
        }

        public void setExchange(Float exchange) {
            this.exchange = exchange;
        }

        public Float getInterest() {
            return interest;
        }

        public void setInterest(Float interest) {
            this.interest = interest;
        }
    }


}
