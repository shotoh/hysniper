package io.github.shotoh.hysniper.bazaar;

public class BazaarQuickStatus {
    private final String productId;
    private final double sellPrice;
    private final int sellVolume;
    private final long sellMovingWeek;
    private final int sellOrders;
    private final double buyPrice;
    private final int buyVolume;
    private final long buyMovingWeek;
    private final int buyOrders;

    public BazaarQuickStatus(String productId, double sellPrice, int sellVolume, long sellMovingWeek, int sellOrders, double buyPrice, int buyVolume, long buyMovingWeek, int buyOrders) {
        this.productId = productId;
        this.sellPrice = sellPrice;
        this.sellVolume = sellVolume;
        this.sellMovingWeek = sellMovingWeek;
        this.sellOrders = sellOrders;
        this.buyPrice = buyPrice;
        this.buyVolume = buyVolume;
        this.buyMovingWeek = buyMovingWeek;
        this.buyOrders = buyOrders;
    }

    public String getProductId() {
        return productId;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public int getSellVolume() {
        return sellVolume;
    }

    public long getSellMovingWeek() {
        return sellMovingWeek;
    }

    public int getSellOrders() {
        return sellOrders;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public int getBuyVolume() {
        return buyVolume;
    }

    public long getBuyMovingWeek() {
        return buyMovingWeek;
    }

    public int getBuyOrders() {
        return buyOrders;
    }
}
