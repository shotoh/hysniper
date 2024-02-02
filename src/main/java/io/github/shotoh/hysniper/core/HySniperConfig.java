package io.github.shotoh.hysniper.core;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Number;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;

public class HySniperConfig extends Config {
    public HySniperConfig() {
        super(new Mod("hysniper", ModType.SKYBLOCK), "hysniperconfig.json");
        initialize();
    }

    // General Category
    // Features Subcategory
    @Switch(
            name = "Enabled",
            subcategory = "Features",
            description = "If the mod's features should be enabled"
    )
    public static boolean modEnabled = false;
    @Switch(
            name = "Sounds",
            subcategory = "Features",
            description = "Creates a sound when a flip has been found"
    )
    public static boolean sounds = false;
    @Switch(
            name = "Auction Stats",
            subcategory = "Features",
            description = "Whether statistics such as TTS should be shown after a scan"
    )
    public static boolean auctionStats = false;
    // QOL Subcategory
    @Switch(
            name = "Ghost Blocks",
            subcategory = "QOL",
            description = "Creates ghost blocks using your pickaxe"
    )
    public static boolean ghostBlocks = false;
    @Number(
            name = "Pickaxe Slot",
            subcategory = "QOL",
            description = "Slot number for your pickaxe",
            min = -1,
            max = 7
    )
    public static int pickaxeSlot = -1;
    @Number(
            name = "Random Slot",
            subcategory = "QOL",
            description = "Slot number for a random item to swap to",
            min = -1,
            max = 7
    )
    public static int randomSlot = -1;
    @Switch(
            name = "Lobby Hider",
            subcategory = "QOL",
            description = "Hides your current lobby"
    )
    public static boolean lobbyHider = false;

    // Price Category
    // Options Subcategory
    @Dropdown(
            name = "Bazaar Algorithm",
            category = "Price",
            subcategory = "Options",
            description = "Determines the bazaar information by its instant-buy or instant-sell prices",
            options = {"Buy", "Sell"}
    )
    public static int bazaarAlgorithm = 0;
    // Lowballing Subcategory
    @Switch(
            name = "Lowballing",
            category = "Price",
            subcategory = "Lowballing",
            description = "Whether the item price should be calculated after clicking the backslash key to assist in lowballing"
    )
    public static boolean lowballing = false;
    @Slider(
            name = "Lowballing Percent Tax",
            category = "Price",
            subcategory = "Lowballing",
            description = "The percent to take away from a lowball",
            min = 0f,
            max = 100f
    )
    public static float lowballingPricePercent = 10f;
    // Flipping Subcategory
    @Switch(
            name = "Flipping",
            category = "Price",
            subcategory = "Flipping",
            description = "Whether the auction house should be randomly scanned for potential flips"
    )
    public static boolean flipping = false;
    @Number(
            name = "Flipping Minimum Profit",
            category = "Price",
            subcategory = "Flipping",
            description = "The minimum profit the auction should be to alert you (in millions)",
            min = 0,
            max = 10000
    )
    public static int flippingMinimumProfit = 1;
    @Slider(
            name = "Flipping Percent Tax",
            category = "Price",
            subcategory = "Flipping",
            description = "The percent to take away from each auction",
            min = 0f,
            max = 100f
    )
    public static float flippingPricePercent = 10f;
    @Number(
            name = "Flipping Minimum Price",
            category = "Price",
            subcategory = "Flipping",
            description = "The minimum price the auction must be (in millions)",
            min = 0,
            max = 10000
    )
    public static int flippingMinPrice = 5;
    @Number(
            name = "Flipping Maximum Price",
            category = "Price",
            subcategory = "Flipping",
            description = "The maximum price the auction must be (in millions)",
            min = 1,
            max = 10000
    )
    public static int flippingMaxPrice = 100;
    @Text(
            name = "Flipping Blacklist",
            category = "Price",
            subcategory = "Flipping",
            placeholder = "NONE",
            description = "Items the mod should forget"
    )
    public static String flippingBlacklist = "NONE";
}