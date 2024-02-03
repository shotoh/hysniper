package io.github.shotoh.hysniper;

import com.google.gson.Gson;
import io.github.shotoh.hysniper.commands.HySniperCommand;
import io.github.shotoh.hysniper.core.HySniperConfig;
import io.github.shotoh.hysniper.events.OnTickEvent;
import io.github.shotoh.hysniper.features.GhostBlocks;
import io.github.shotoh.hysniper.prices.PriceChecker;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Mod(modid = HySniper.MODID, name = HySniper.NAME, version = HySniper.VERSION, clientSideOnly = true)
public class HySniper {
    public static final String MODID = "hysniper";
    public static final String NAME = "HySniper";
    public static final String VERSION = "1.3.0";

    public static HySniperConfig CONFIG;
    public static Gson GSON;
    public static Map<UUID, String> CLIPBOARD;
    public static ScheduledExecutorService POOL;

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        CONFIG = new HySniperConfig();
        GSON = new Gson();
        CLIPBOARD = new HashMap<>();
        POOL = Executors.newSingleThreadScheduledExecutor();

        ClientCommandHandler.instance.registerCommand(new HySniperCommand());
        MinecraftForge.EVENT_BUS.register(new OnTickEvent());
        MinecraftForge.EVENT_BUS.register(new GhostBlocks());
        MinecraftForge.EVENT_BUS.register(new PriceChecker());

        POOL.schedule(PriceChecker::scanPrices, 60, TimeUnit.SECONDS);
        // sans undertale
    }
}