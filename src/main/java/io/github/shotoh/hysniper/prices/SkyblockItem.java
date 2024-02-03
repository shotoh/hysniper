package io.github.shotoh.hysniper.prices;

public class SkyblockItem {
    private final String id;
    private final String name;
    private final double percent;
    private final long price;

    public SkyblockItem(String id, String name, double percent, long price) {
        this.id = id;
        this.name = name;
        this.percent = percent;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPercent() {
        return percent;
    }

    public long getPrice() {
        return price;
    }
}
