package io.github.shotoh.hysniper.auctions;

import com.google.gson.annotations.SerializedName;

public class AuctionItem {
    private String uuid;
    private String item_name;
    private long starting_bid;
    private String item_bytes;
    private boolean bin;

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String itemName) {
        this.item_name = itemName;
    }

    public long getStartingBid() {
        return starting_bid;
    }

    public void setStartingBid(long startingBid) {
        this.starting_bid = startingBid;
    }

    public String getItemBytes() {
        return item_bytes;
    }

    public void setItemBytes(String itemBytes) {
        this.item_bytes = itemBytes;
    }

    public boolean isBin() {
        return bin;
    }

    public void setBin(boolean bin) {
        this.bin = bin;
    }
}