package io.github.shotoh.hysniper.events;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

@Cancelable
public class MouseClickEvent extends Event {
    public final GuiContainer guiContainer;
    public final Slot slot;
    public final int slotId;
    public int clickedButton;
    public int clickType;

    public MouseClickEvent(GuiContainer guiContainer, Slot slot, int slotId, int clickedButton, int clickType) {
        this.guiContainer = guiContainer;
        this.slot = slot;
        this.slotId = slotId;
        this.clickedButton = clickedButton;
        this.clickType = clickType;
    }
}
