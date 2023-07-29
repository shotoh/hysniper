package io.github.shotoh.hysniper.events;

import io.github.shotoh.hysniper.utils.Utils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class OnTickEvent {
    private static int ticks = 0;

    @SubscribeEvent
    public void onClientTickEvent(TickEvent.ClientTickEvent e) {
        if (ticks % 20 == 0) {
            Utils.update();
        }
        ticks++;
    }
}