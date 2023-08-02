package io.github.shotoh.hysniper.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.StringUtils;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {
    private static boolean inSkyblock = false;
    private static boolean soundPlayed = false;
    private static String[] reforges = new String[] {
            "Epic", "Fair", "Fast", "Gentle", "Heroic", "Legendary", "Odd", "Sharp", "Spicy", "Coldfused", "Dirty", "Fabled",
            "Gilded", "Suspicious", "Warped", "Withered", "Bulky", "Jerry's", "Awkward", "Deadly", "Fine", "Grand", "Hasty",
            "Neat", "Rapid", "Rich", "Unreal", "Precise", "Spiritual", "Headstrong", "Clean", "Fierce", "Heavy", "Light",
            "Mythic", "Pure", "Titanic", "Smart", "Wise", "Candied", "Submerged", "Perfect", "Reinforced", "Renowned",
            "Spiked", "Hyper", "Giant", "Jaded", "Cubic", "Necrotic", "Empowered", "Ancient", "Undead", "Loving", "Ridiculous",
            "Bustling", "Mossy", "Festive", "Glistening", "Strengthened", "Waxed", "Fortified", "Rooted", "Blooming", "Snowy",
            "Salty", "Treacherous", "Lucky", "Stiff", "Dirty", "Chomp", "Pitchin'", "Unyielding", "Prospector's", "Excellent",
            "Sturdy", "Fortunate", "Ambered", "Auspicious", "Fleet", "Heated", "Magnetic", "Mithraic", "Refined", "Stellar",
            "Fruitful", "Great", "Rugged", "Lush", "Lumberjack's", "Double-Bit", "Moil", "Toil", "Blessed", "Earthy", "Robust",
            "Zooming", "Pleasant", "Green Thumb", "Blessed", "Bountiful"
    };

    public static boolean isInSkyblock() {
        return inSkyblock;
    }

    public static boolean isSoundPlayed() {
        return soundPlayed;
    }

    public static void setSoundPlayed(boolean soundPlayed) {
        Utils.soundPlayed = soundPlayed;
    }

    public static void update() {
        // ty sba
        Minecraft mc = Minecraft.getMinecraft();
        if (mc != null && mc.theWorld != null && mc.thePlayer != null) {
            if (mc.isSingleplayer() || mc.thePlayer.getClientBrand() == null ||
                    !mc.thePlayer.getClientBrand().toLowerCase().contains("hypixel")) {
                inSkyblock = false;
                return;
            }
            Scoreboard scoreboard = mc.theWorld.getScoreboard();
            ScoreObjective sidebarObjective = scoreboard.getObjectiveInDisplaySlot(1);
            if (sidebarObjective != null) {
                String objectiveName = sidebarObjective.getDisplayName().replaceAll("(?i)\\u00A7.", "");
                if (objectiveName.contains("SKYBLOCK")) {
                    inSkyblock = true;
                    return;
                }
            }
            inSkyblock = false;
        }
    }

    public static void playSound(String sound, float volume, float pitch) {
        Minecraft.getMinecraft().thePlayer.playSound(sound, volume, pitch);
    }

    public static String formatPrice(long price) {
        double div = 1.0;
        String suffix = "";
        if (price >= 1000000) {
            div = 1000000.0;
            suffix = "M";
        } else if (price >= 1000) {
            div = 1000.0;
            suffix = "K";
        }
        String val = price / div + "";
        if (val.contains(".")) {
            if (val.substring(val.indexOf(".")).length() > 2) {
                val = val.substring(0, val.indexOf(".") + 2);
            }
        }
        return val + suffix;
    }

    public static String formatTime(long time) {
        String val = time / 1000.0 + "";
        if (val.contains(".")) {
            if (val.substring(val.indexOf(".")).length() > 4) {
                val = val.substring(0, val.indexOf(".") + 4);
            }
        }
        return val;
    }

    public static String stripItemName(String name) {
        name = StringUtils.stripControlCodes(name);
        for (String reforge : reforges) {
            name = name.replace(reforge + " ", "");
        }
        name = name.replace(" ✪", "");
        name = name.replace("✪", "");
        name = name.replace("➊", "");
        name = name.replace("➋", "");
        name = name.replace("➌", "");
        name = name.replace("➍", "");
        name = name.replace("➎", "");
        return name;
    }
}