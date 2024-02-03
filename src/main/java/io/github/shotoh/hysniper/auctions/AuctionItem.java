package io.github.shotoh.hysniper.auctions;

public class AuctionItem {
    private final String uuid;
    private final String item_name;
    private final long starting_bid;
    private final String item_bytes;
    private final boolean bin;

    public AuctionItem(String uuid, String item_name, long starting_bid, String item_bytes, boolean bin) {
        this.uuid = uuid;
        this.item_name = item_name;
        this.starting_bid = starting_bid;
        this.item_bytes = item_bytes;
        this.bin = bin;
    }

    public String getUUID() {
        return uuid;
    }

    public String getItemName() {
        return item_name;
    }

    public long getStartingBid() {
        return starting_bid;
    }

    public String getItemBytes() {
        return item_bytes;
    }

    public boolean isBin() {
        return bin;
    }
}