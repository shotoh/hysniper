package io.github.shotoh.hysniper.core;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.*;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Comparator;

public class HySniperConfig extends Vigilant {
    // General Category
    // Features Subcategory
    @Property(
            type = PropertyType.SWITCH,
            name = "Enabled",
            category = "General",
            subcategory = "Features",
            description = "If the mod's features should be enabled"
    )
    public static boolean enabled = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Sounds",
            category = "General",
            subcategory = "Features",
            description = "Creates a sound when a flip has been found"
    )
    public static boolean sounds = false;
    @Property(
            type = PropertyType.SWITCH,
            name = "Auction Stats",
            category = "General",
            subcategory = "Features",
            description = "Whether statistics such as TTS should be shown after a scan"
    )
    public static boolean auctionStats = false;
    // QOL Subcategory
    @Property(
            type = PropertyType.SWITCH,
            name = "Ghost Blocks",
            category = "General",
            subcategory = "QOL",
            description = "Creates ghost blocks using your pickaxe"
    )
    public static boolean ghostBlocks = false;
    @Property(
            type = PropertyType.NUMBER,
            name = "Pickaxe Slot",
            category = "General",
            subcategory = "QOL",
            description = "Slot number for your pickaxe",
            min = -1,
            max = 7
    )
    public static int pickaxeSlot = -1;
    @Property(
            type = PropertyType.NUMBER,
            name = "Random Slot",
            category = "General",
            subcategory = "QOL",
            description = "Slot number for a random item to swap to",
            min = -1,
            max = 7
    )
    public static int randomSlot = -1;
    @Property(
            type = PropertyType.SWITCH,
            name = "Lobby Hider",
            category = "General",
            subcategory = "QOL",
            description = "Hides your current lobby"
    )
    public static boolean lobbyHider = false;

    // Price Category
    // Options Subcategory
    @Property(
            type = PropertyType.SELECTOR,
            name = "Bazaar Algorithm",
            category = "Price",
            subcategory = "Options",
            description = "Determines the bazaar information by its instant-buy or instant-sell prices",
            options = {"Buy", "Sell"}
    )
    public static int bazaarAlgorithm = 0;
    // Lowballing Subcategory
    @Property(
            type = PropertyType.SWITCH,
            name = "Lowballing",
            category = "Price",
            subcategory = "Lowballing",
            description = "Whether the item price should be calculated after clicking the backslash key to assist in lowballing"
    )
    public static boolean lowballing = false;
    @Property(
            type = PropertyType.PERCENT_SLIDER,
            name = "Lowballing Percent Tax",
            category = "Price",
            subcategory = "Lowballing",
            description = "The percent to take away from a lowball",
            max = 1
    )
    public static float lowballingPricePercent = 0.1f;
    // Flipping Subcategory
    @Property(
            type = PropertyType.SWITCH,
            name = "Flipping",
            category = "Price",
            subcategory = "Flipping",
            description = "Whether the auction house should be randomly scanned for potential flips"
    )
    public static boolean flipping = false;
    @Property(
            type = PropertyType.NUMBER,
            name = "Flipping Minimum Profit",
            category = "Price",
            subcategory = "Flipping",
            description = "The minimum profit the auction should be to alert you (in millions)",
            max = 10000
    )
    public static int flippingMinimumProfit = 1;
    @Property(
            type = PropertyType.PERCENT_SLIDER,
            name = "Flipping Percent Tax",
            category = "Price",
            subcategory = "Flipping",
            description = "The percent to take away from each auction",
            max = 1
    )
    public static float flippingPricePercent = 0.1f;
    @Property(
            type = PropertyType.NUMBER,
            name = "Flipping Minimum Price",
            category = "Price",
            subcategory = "Flipping",
            description = "The minimum price the auction must be (in millions)",
            max = 10000
    )
    public static int flippingMinPrice = 5;
    @Property(
            type = PropertyType.NUMBER,
            name = "Flipping Maximum Price",
            category = "Price",
            subcategory = "Flipping",
            description = "The maximum price the auction must be (in millions)",
            max = 10000
    )
    public static int flippingMaxPrice = 100;
    @Property(
            type = PropertyType.PARAGRAPH,
            name = "Flipping Whitelist",
            category = "Price",
            subcategory = "Flipping",
            description = "Items the mod should target"
    )
    public static String flippingWhitelist = "NONE";

    public static HySniperConfig INSTANCE = new HySniperConfig();

    private HySniperConfig() {
        super(new File("./config/hysniper.toml"), "HySniper", new JVMAnnotationPropertyCollector(),
                new SortingBehavior() {
                    @NotNull
                    @Override
                    public Comparator<? super Category> getCategoryComparator() {
                        return (Comparator<Category>) (o1, o2) -> {
                            if (o1.getName().equals("General")) {
                                return -1;
                            } else if (o2.getName().equals("General")) {
                                return 1;
                            } else {
                                return o1.getName().compareTo(o2.getName());
                            }
                        };
                    }
                });
        initialize();
    }
}