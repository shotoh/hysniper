package io.github.shotoh.hysniper.prices;

public class PriceInfo {
    private final long price;
    private final StringBuilder builder;

    public PriceInfo(long price, StringBuilder builder) {
        this.price = price;
        this.builder = builder;
    }

    public long getPrice() {
        return price;
    }

    public StringBuilder getBuilder() {
        return builder;
    }
}
