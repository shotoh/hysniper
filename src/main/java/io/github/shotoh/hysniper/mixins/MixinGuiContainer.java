package io.github.shotoh.hysniper.mixins;

import gg.essential.universal.UChat;
import io.github.shotoh.hysniper.events.MouseClickEvent;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiContainer.class, priority = 1100)
public class MixinGuiContainer {
    @Inject(method = "handleMouseClick", at = @At(value = "HEAD"), cancellable = true)
    public void handleMouseClick(Slot slotIn, int slotId, int clickedButton, int clickType, CallbackInfo ci) {
        UChat.chat(1);
        if (slotIn == null) return;
        UChat.chat(2);
        GuiContainer gui = (GuiContainer) (Object) this;
        MouseClickEvent event = new MouseClickEvent(gui, slotIn, slotId, clickedButton, clickType);
        MinecraftForge.EVENT_BUS.post(event);
        UChat.chat(3);
        if (event.isCanceled()) {
            UChat.chat(4);
            ci.cancel();
        }
        UChat.chat(5);
    }
}
