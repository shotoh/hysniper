package io.github.shotoh.hysniper.bazaar;

import java.util.Map;

public class BazaarPage {
    private boolean success;
    private long lastUpdated;
    private Map<String, BazaarItem> products;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Map<String, BazaarItem> getProducts() {
        return products;
    }

    public void setProducts(Map<String, BazaarItem> products) {
        this.products = products;
    }
}
