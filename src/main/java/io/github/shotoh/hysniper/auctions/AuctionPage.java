package io.github.shotoh.hysniper.auctions;

import java.util.List;

public class AuctionPage {
    private final boolean success;
    private final int totalPages;
    private final long lastUpdated;
    private final List<AuctionItem> auctions;

    public AuctionPage(boolean success, int totalPages, long lastUpdated, List<AuctionItem> auctions) {
        this.success = success;
        this.totalPages = totalPages;
        this.lastUpdated = lastUpdated;
        this.auctions = auctions;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public List<AuctionItem> getAuctions() {
        return auctions;
    }
}