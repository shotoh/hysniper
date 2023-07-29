package io.github.shotoh.hysniper.bazaar;

public class BazaarQuickStatus {
    private String productId;
    private double sellPrice;
    private int sellVolume;
    private long sellMovingWeek;
    private int sellOrders;
    private double buyPrice;
    private int buyVolume;
    private long buyMovingWeek;
    private int buyOrders;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getSellVolume() {
        return sellVolume;
    }

    public void setSellVolume(int sellVolume) {
        this.sellVolume = sellVolume;
    }

    public long getSellMovingWeek() {
        return sellMovingWeek;
    }

    public void setSellMovingWeek(long sellMovingWeek) {
        this.sellMovingWeek = sellMovingWeek;
    }

    public int getSellOrders() {
        return sellOrders;
    }

    public void setSellOrders(int sellOrders) {
        this.sellOrders = sellOrders;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getBuyVolume() {
        return buyVolume;
    }

    public void setBuyVolume(int buyVolume) {
        this.buyVolume = buyVolume;
    }

    public long getBuyMovingWeek() {
        return buyMovingWeek;
    }

    public void setBuyMovingWeek(long buyMovingWeek) {
        this.buyMovingWeek = buyMovingWeek;
    }

    public int getBuyOrders() {
        return buyOrders;
    }

    public void setBuyOrders(int buyOrders) {
        this.buyOrders = buyOrders;
    }
}
