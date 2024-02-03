package io.github.shotoh.hysniper.core;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Number;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;

public class HySniperConfig extends Config {
    public HySniperConfig() {
        super(new Mod("hysniper", ModType.SKYBLOCK), "hysniper.json");
        initialize();
    }

    @Switch(
            name = "Scanning",
            subcategory = "Features",
            description = "If the mod should scan the auction house and bazaar"
    )
    public static boolean scanning = false;
    @Switch(
            name = "Statistics",
            subcategory = "Features",
            description = "Whether statistics such as TTS should be shown after a scan"
    )
    public static boolean statistics = false;
    @Switch(
            name = "Ghost Blocks",
            subcategory = "Features",
            description = "Creates ghost blocks using your pickaxe"
    )
    public static boolean ghostBlocks = false;
    @Number(
            name = "Pickaxe Slot",
            subcategory = "Features",
            description = "Slot number for your pickaxe",
            min = -1,
            max = 7
    )
    public static int pickaxeSlot = -1;
    @Number(
            name = "Random Slot",
            subcategory = "Features",
            description = "Slot number for a random item to swap to",
            min = -1,
            max = 7
    )
    public static int randomSlot = -1;
    @Switch(
            name = "Lobby Hider",
            subcategory = "Features",
            description = "Hides your current lobby"
    )
    public static boolean lobbyHider = false;
    @Dropdown(
            name = "Bazaar Algorithm",
            category = "Price",
            subcategory = "Features",
            description = "Determines the bazaar information by its instant-buy or instant-sell prices",
            options = {"Buy", "Sell"}
    )
    public static int bazaarAlgorithm = 0;
    @Switch(
            name = "Lowballing",
            category = "Price",
            subcategory = "Features",
            description = "Whether the item price should be calculated after clicking the backslash key to assist in lowballing"
    )
    public static boolean lowballing = false;
    @Slider(
            name = "Lowballing Tax",
            category = "Price",
            subcategory = "Features",
            description = "The percent to tax from a lowball",
            min = 0f,
            max = 100f
    )
    public static float lowballingTax = 10f;
}