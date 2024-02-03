package io.github.shotoh.hysniper.bazaar;

public class BazaarSummary {
    private final int amount;
    private final double pricePerUnit;
    private final int orders;

    public BazaarSummary(int amount, double pricePerUnit, int orders) {
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
        this.orders = orders;
    }

    public int getAmount() {
        return amount;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public int getOrders() {
        return orders;
    }
}
