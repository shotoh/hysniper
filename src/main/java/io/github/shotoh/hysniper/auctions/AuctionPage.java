package io.github.shotoh.hysniper.auctions;

import java.util.List;

public class AuctionPage {
    private boolean success;
    private int totalPages;
    private long lastUpdated;
    private List<AuctionItem> auctions;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<AuctionItem> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<AuctionItem> auctions) {
        this.auctions = auctions;
    }
}