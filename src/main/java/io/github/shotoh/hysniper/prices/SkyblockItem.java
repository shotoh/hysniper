package io.github.shotoh.hysniper.prices;

public class SkyblockItem {
    public static final SkyblockItem EMPTY_ITEM = new SkyblockItem(null, null, 0.0, 0);

    private String id;
    private String name;
    private double percent;
    private long price;

    public SkyblockItem(String id, String name, double percent, long price) {
        this.id = id;
        this.name = name;
        this.percent = percent;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}