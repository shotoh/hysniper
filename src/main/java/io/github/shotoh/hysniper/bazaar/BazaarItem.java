package io.github.shotoh.hysniper.bazaar;

public class BazaarItem {
    private final String product_id;
    private final BazaarSummary[] buy_summary;
    private final BazaarSummary[] sell_summary;
    private final BazaarQuickStatus quick_status;

    public BazaarItem(String product_id, BazaarSummary[] buy_summary, BazaarSummary[] sell_summary, BazaarQuickStatus quick_status) {
        this.product_id = product_id;
        this.buy_summary = buy_summary;
        this.sell_summary = sell_summary;
        this.quick_status = quick_status;
    }

    public String getProductId() {
        return product_id;
    }

    public BazaarSummary[] getBuySummary() {
        return buy_summary;
    }

    public BazaarSummary[] getSellSummary() {
        return sell_summary;
    }

    public BazaarQuickStatus getQuickStatus() {
        return quick_status;
    }
}
