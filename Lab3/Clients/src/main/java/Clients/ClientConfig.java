package Clients;

import java.util.List;

/**
 * Created by wiktor on 10/04/16.
 */
public class ClientConfig {

    private final List<String> subscribeChannels;
    private final boolean durableSubscription;

    public ClientConfig(List<String> subscribeChannels, boolean durableSubscription) {
        this.subscribeChannels = subscribeChannels;
        this.durableSubscription = durableSubscription;
    }

    public List<String> getSubscribeChannels() {
        return subscribeChannels;
    }

    public boolean isDurableSubscription() {
        return durableSubscription;
    }
}
