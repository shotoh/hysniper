package io.github.shotoh.hysniper.prices;

public class PriceInfo {
    private long price;
    private StringBuilder builder;

    public PriceInfo(long price, StringBuilder builder) {
        this.price = price;
        this.builder = builder;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public StringBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(StringBuilder builder) {
        this.builder = builder;
    }
}
