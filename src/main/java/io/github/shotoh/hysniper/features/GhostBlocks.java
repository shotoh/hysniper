package io.github.shotoh.hysniper.features;

import io.github.shotoh.hysniper.core.HySniperConfig;
import io.github.shotoh.hysniper.events.MouseClickEvent;
import io.github.shotoh.hysniper.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class GhostBlocks {
    private int phase;
    private long cooldown;

    public GhostBlocks() {
        this.phase = 0;
        this.cooldown = 0L;
    }

    @SubscribeEvent
    public void onGuiOpenEvent(GuiOpenEvent event) {
        if (HySniperConfig.ghostBlocks) {
            phase = 0;
            cooldown = 0L;
        }
    }

    @SubscribeEvent
    public void onMouseClickEvent(MouseClickEvent event) {
        if (!Utils.inSkyblock || !HySniperConfig.ghostBlocks) return;
        Minecraft mc = Minecraft.getMinecraft();
        int pickaxe = HySniperConfig.pickaxeSlot;
        int random = HySniperConfig.randomSlot;
        if (mc.thePlayer != null && mc.theWorld != null && mc.playerController != null &&
                pickaxe != random && pickaxe != -1 && random != -1 &&
                Mouse.getEventButton() == 0 && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            GuiContainer gui = event.guiContainer;
            Slot slot = event.slot;
            if (System.currentTimeMillis() >= cooldown) {
                if (phase == 0 && slot.getHasStack()) {
                    ItemStack is = slot.getStack();
                    if (is.getDisplayName().contains("Diamond Pickaxe")) {
                        phase = 2;
                    } else {
                        phase = 1;
                    }
                }
                if (slot.getSlotIndex() == pickaxe) {
                    if (phase == 1) {
                        mc.playerController.windowClick(gui.inventorySlots.windowId, pickaxe + 36, random, 2, mc.thePlayer);
                        Utils.playSound("note.pling", 1, 0.75f);
                        phase++;
                    } else if (phase == 2) {
                        mc.playerController.windowClick(gui.inventorySlots.windowId, pickaxe + 36, pickaxe, 2, mc.thePlayer);
                        Utils.playSound("note.pling", 1, 1f);
                        phase++;
                    } else if (phase == 3) {
                        mc.playerController.windowClick(gui.inventorySlots.windowId, pickaxe + 36, random, 2, mc.thePlayer);
                        Utils.playSound("note.pling", 1, 1.5f);
                        phase = 0;
                        cooldown = System.currentTimeMillis() + 1000;
                    } else {
                        return;
                    }
                    event.setCanceled(true);
                }
            } else {
                event.setCanceled(true);
            }
        }
    }
}
