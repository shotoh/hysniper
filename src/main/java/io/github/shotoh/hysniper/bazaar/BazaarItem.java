package io.github.shotoh.hysniper.bazaar;

import com.google.gson.annotations.SerializedName;

public class BazaarItem {
    private String product_id;
    private BazaarSummary[] buy_summary;
    private BazaarSummary[] sell_summary;
    private BazaarQuickStatus quick_status;

    public String getProductId() {
        return product_id;
    }

    public void setProductId(String productId) {
        this.product_id = productId;
    }

    public BazaarSummary[] getBuySummary() {
        return buy_summary;
    }

    public void setBuySummary(BazaarSummary[] buySummary) {
        this.buy_summary = buySummary;
    }

    public BazaarSummary[] getSellSummary() {
        return sell_summary;
    }

    public void setSellSummary(BazaarSummary[] sellSummary) {
        this.sell_summary = sellSummary;
    }

    public BazaarQuickStatus getQuickStatus() {
        return quick_status;
    }

    public void setQuickStatus(BazaarQuickStatus quickStatus) {
        this.quick_status = quickStatus;
    }
}
