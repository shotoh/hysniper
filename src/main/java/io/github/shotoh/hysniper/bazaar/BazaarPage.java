package io.github.shotoh.hysniper.bazaar;

import java.util.Map;

public class BazaarPage {
    private final boolean success;
    private final long lastUpdated;
    private final Map<String, BazaarItem> products;

    public BazaarPage(boolean success, long lastUpdated, Map<String, BazaarItem> products) {
        this.success = success;
        this.lastUpdated = lastUpdated;
        this.products = products;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public Map<String, BazaarItem> getProducts() {
        return products;
    }
}
