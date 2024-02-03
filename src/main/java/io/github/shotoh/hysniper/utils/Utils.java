package io.github.shotoh.hysniper.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.event.ClickEvent;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.IChatComponent;

public class Utils {
    public static boolean inSkyblock = false;

    public static void addMessage(String msg) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (player == null) return;
        player.addChatMessage(new ChatComponentText(msg));
    }

    public static void addCommandMessage(String msg, String command) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (player == null) return;
        IChatComponent component = new ChatComponentText(msg);
        ChatStyle style = new ChatStyle();
        style.setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        component.setChatStyle(style);
        player.addChatMessage(component);
    }

    public static void playSound(String sound, float volume, float pitch) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (player == null) return;
        player.playSound(sound, volume, pitch);
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
}