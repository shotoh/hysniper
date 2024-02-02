package io.github.shotoh.hysniper;

import com.google.gson.Gson;
import io.github.shotoh.hysniper.auctions.AuctionChecker;
import io.github.shotoh.hysniper.bazaar.BazaarChecker;
import io.github.shotoh.hysniper.commands.HySniperCommand;
import io.github.shotoh.hysniper.core.HySniperConfig;
import io.github.shotoh.hysniper.events.OnTickEvent;
import io.github.shotoh.hysniper.features.qol.GhostBlocks;
import io.github.shotoh.hysniper.prices.PriceChecker;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Mod(modid = HySniper.MODID, name = HySniper.NAME, version = HySniper.VERSION, clientSideOnly = true)
public class HySniper {
    public static final String MODID = "hysniper";
    public static final String NAME = "HySniper";
    public static final String VERSION = "1.2.0";

    public static HySniperConfig CONFIG;

    private final Gson gson;
    private final ScheduledExecutorService pool;
    private final ExecutorService executor;
    private final HashMap<UUID, String> clipboard;

    private final AuctionChecker auctionChecker;
    private final BazaarChecker bazaarChecker;
    private final PriceChecker priceChecker;
    private boolean dev;

    public HySniper() {
        this.gson = new Gson();
        this.pool = Executors.newSingleThreadScheduledExecutor();
        this.executor = Executors.newFixedThreadPool(2);
        this.clipboard = new HashMap<>();
        this.auctionChecker = new AuctionChecker(this);
        this.bazaarChecker = new BazaarChecker(this);
        this.priceChecker = new PriceChecker(this);
        this.dev = false;
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        CONFIG = new HySniperConfig();
        ClientCommandHandler.instance.registerCommand(new HySniperCommand(this));
        MinecraftForge.EVENT_BUS.register(new OnTickEvent());
        MinecraftForge.EVENT_BUS.register(new GhostBlocks());
        MinecraftForge.EVENT_BUS.register(priceChecker);

        // DataManager.load(this);

        auctionChecker.checkAuctions();
        // sans undertale
    }

    public Gson getGson() {
        return gson;
    }

    public ScheduledExecutorService getPool() {
        return pool;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public HashMap<UUID, String> getClipboard() {
        return clipboard;
    }

    public AuctionChecker getAuctionChecker() {
        return auctionChecker;
    }

    public BazaarChecker getBazaarChecker() {
        return bazaarChecker;
    }

    public PriceChecker getPriceChecker() {
        return priceChecker;
    }

    public boolean isDev() {
        return dev;
    }

    public void setDev(boolean dev) {
        this.dev = dev;
    }
}