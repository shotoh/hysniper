package io.github.shotoh.hysniper.mixins;

import io.github.shotoh.hysniper.events.MouseClickEvent;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GuiContainer.class, priority = -100)
public class MixinGuiContainer {
    @Inject(method = "handleMouseClick", at = @At(value = "HEAD"), cancellable = true)
    public void handleMouseClick(Slot slotIn, int slotId, int clickedButton, int clickType, CallbackInfo ci) {
        if (slotIn == null) return;
        GuiContainer gui = (GuiContainer) (Object) this;
        MouseClickEvent event = new MouseClickEvent(gui, slotIn, slotId, clickedButton, clickType);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled()) {
            ci.cancel();
        }
    }
}
