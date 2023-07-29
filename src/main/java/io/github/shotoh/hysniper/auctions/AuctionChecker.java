package io.github.shotoh.hysniper.auctions;

import com.google.common.reflect.TypeToken;
import gg.essential.universal.UChat;
import io.github.shotoh.hysniper.HySniper;
import io.github.shotoh.hysniper.core.HySniperConfig;
import io.github.shotoh.hysniper.utils.Utils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;

public class AuctionChecker {
    private final HySniper mod;
    private final CloseableHttpClient httpClient;
    private final HashSet<AuctionItem> oldAuctions;
    private HashMap<String, Double> lowestBins;
    // private long oldUpdated;

    public AuctionChecker(HySniper mod) {
        this.mod = mod;
        this.httpClient = HttpClientBuilder.create().build();
        this.oldAuctions = new HashSet<>();
        this.lowestBins = new HashMap<>();
    }

    public void checkAuctions() {
        if (Utils.isInSkyblock() && HySniperConfig.enabled) {
            long start = System.currentTimeMillis();

            mod.getPool().schedule(this::checkAuctions, 60000 /* lastUpdated + 57000 - System.currentTimeMillis() */, TimeUnit.MILLISECONDS);
            mod.getBazaarChecker().checkBazaar(); // todo move to its own section
            UChat.chat("&7Updating Lowest Bins...");
            mod.getExecutor().execute(this::updateLowestBins);
            Utils.setSoundPlayed(false);
            if (HySniperConfig.auctionStats) {
                long tts = System.currentTimeMillis() - start;
                // UChat.chat("&5TTS avg:&r&d " + (tts / totalPages) + " ms");
                UChat.chat("&5TTS all:&r&d " + Utils.formatTime(tts) + " seconds");
            }


            /*
            try {
                AuctionPage firstPage = getAuctionPage(0);
                if (firstPage != null && firstPage.isSuccess()) {
                    int totalPages = firstPage.getTotalPages();
                    long lastUpdated = firstPage.getLastUpdated();
                    if (lastUpdated != oldUpdated) {
                        mod.getPool().schedule(this::checkAuctions, 60000 /* lastUpdated + 57000 - System.currentTimeMillis() , TimeUnit.MILLISECONDS);
                    oldUpdated = lastUpdated;
                    // UChat.chat("&7Scanning Auctions...");
                    mod.getBazaarChecker().checkBazaar(); // todo move to its own section
                    UChat.chat("&7Updating Lowest Bins...");
                    mod.getExecutor().execute(this::updateLowestBins);
                    // creates futures between page 1 to total pages and collects them into a list
                    List<CompletableFuture<AuctionPage>> futures = IntStream.range(0, totalPages)
                            .mapToObj(i -> CompletableFuture.supplyAsync(() -> getAuctionPage(i), mod.getExecutor()))
                            .collect(Collectors.toList());
                    // waits for all the futures to invoke (finish)
                    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();
                    // streams through all futures, gets all pages, then gets all auctions, filters bins and checks each
                    futures.stream().map(future -> {
                        try {
                            return future.get().getAuctions();
                        } catch (Exception e) {
                            e.printStackTrace();
                            return new ArrayList<AuctionItem>();
                        }
                    }).flatMap(Collection::stream).filter(AuctionItem::isBin).forEach(this::check);
                    // big thanks to cryptic (so cool)
                    Utils.setSoundPlayed(false);
                    if (HySniperConfig.auctionStats) {
                        long tts = System.currentTimeMillis() - start;
                        UChat.chat("&5TTS avg:&r&d " + (tts / totalPages) + " ms");
                        UChat.chat("&5TTS all:&r&d " + Utils.formatTime(tts) + " seconds");
                    }
                } else {
                    mod.getPool().schedule(this::checkAuctions, 500, TimeUnit.MILLISECONDS);
                }
            } else {
                mod.getPool().schedule(this::checkAuctions, 57, TimeUnit.SECONDS);
                UChat.chat("&cFailed to fetch auctions");
            }
        }
             */
        } else {
            mod.getPool().schedule(this::checkAuctions, 57, TimeUnit.SECONDS);
        }
    }

    public AuctionPage getAuctionPage(int page) {
        HttpGet httpGet = new HttpGet("https://api.hypixel.net/skyblock/auctions?page=" + page);
        httpGet.addHeader("content-type", "application/json; charset=UTF-8");
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            return mod.getGson().fromJson(new InputStreamReader(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8), AuctionPage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateLowestBins() {
        HttpGet httpGet = new HttpGet("https://moulberry.codes/lowestbin.json");
        httpGet.addHeader("content-type", "application/json; charset=UTF-8");
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            this.lowestBins = mod.getGson().fromJson(new InputStreamReader(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8),
                    new TypeToken<HashMap<String, Double>>() {}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void check(AuctionItem item) {
        if (oldAuctions.stream().map(AuctionItem::getUUID).noneMatch(item.getUUID()::equals)) {
            // mod.getLowballChecker().addLowestBin(item);
            oldAuctions.add(item);
            /*
            try {
                NBTTagCompound itemTag = CompressedStreamTools.readCompressed(
                        new ByteArrayInputStream(Base64.getDecoder().decode(item.getItemBytes()))
                );
                long price = (long) (getPriceFromNBT(itemTag) * HySniperConfig.bazaarPercent) + HySniperConfig.basePrice;
                if (price - item.getStartingBid() >= HySniperConfig.priceDifference) {
                    flipAlert(item, price);
                    oldAuctions.add(item);
                }
            } catch (IOException e) {
                e.printStackTrace();
                UChat.chat("&cFailed to parse NBT data!");
            }
             */
        }
    }

    /*
    public void flipAlert(AuctionItem item, long price) {
        UTextComponent component = new UTextComponent("&9&lFLIP!&r&b " + item.getItemName() +
                "&r&e [" + Utils.formatPrice(item.getStartingBid()) + " -> " + Utils.formatPrice(price) + "] (" +
                Utils.formatPrice(price - item.getStartingBid()) + ")");
        component.setClickAction(ClickEvent.Action.RUN_COMMAND);
        component.setClickValue("/viewauction " + item.getUUID());
        component.chat();
        if (HySniperConfig.sounds && !Utils.isSoundPlayed()) {
            Utils.setSoundPlayed(true);
            Utils.playSound("note.pling", 1, 0.5f);
        }
    }

    public long getPriceFromNBT(NBTTagCompound itemTag) {
        SkyblockInfo si = HySniper.si;
        long price = 0L;
        NBTTagCompound tag;
        if (itemTag.hasKey("i")) {
            tag = itemTag.getTagList("i", 10).getCompoundTagAt(0).getCompoundTag("tag");
        } else {
            tag = itemTag;
        }
        if (tag.hasKey("display", 10) && tag.hasKey("ExtraAttributes", 10)) {
            NBTTagCompound display = tag.getCompoundTag("display");
            NBTTagCompound ea = tag.getCompoundTag("ExtraAttributes");
            String itemName = display.getString("Name");
            for (String baseKey : si.getBaseMap().keySet()) {
                if (itemName.contains(baseKey)) {
                    price += si.getBaseMap().getOrDefault(baseKey, 0L);
                }
            }
            price += si.getRecomMap().getOrDefault(ea.getInteger("rarity_upgrades"), 0L);
            price += si.getHpbMap().getOrDefault(ea.getInteger("hot_potato_count"), 0L);
            NBTTagList lore = display.getTagList("Lore", 8);
            for (int i = 0; i < lore.tagCount(); i++) {
                String s = lore.getStringTagAt(i);
                if (s.matches("(?s).*\\B§.\\[§..§.]\\B.*")) {
                    int bracketCount = StringUtils.countMatches(s, "[");
                    int lockedCount = StringUtils.countMatches(s, "[§8");
                    price += si.getGemstoneMap().getOrDefault(bracketCount - lockedCount, 0L);
                }
            }
            price += si.getReforgeMap().getOrDefault(ea.getString("modifier"), 0L);
            if (ea.getInteger("dungeon_item_level") > ea.getInteger("upgrade_level")) {
                price += si.getStarMap().getOrDefault(ea.getInteger("dungeon_item_level"), 0L);
            } else {
                price += si.getStarMap().getOrDefault(ea.getInteger("upgrade_level"), 0L);
            }
            if (ea.hasKey("enchantments", 10)) {
                NBTTagCompound enchantments = ea.getCompoundTag("enchantments");
                for (String key : enchantments.getKeySet()) {
                    String enchantName = key + ": " + enchantments.getInteger(key);
                    price += si.getEnchantMap().getOrDefault(enchantName, 0L);
                }
            }
        }
        return price;
    }

     */

    public void clearOldAuctions() {
        oldAuctions.clear();
    }

    /*
    @SubscribeEvent
    public void onKeyboardInputPre(GuiScreenEvent.KeyboardInputEvent.Pre e) {
        if (!Utils.isInSkyblock()) {
            return;
        }
        if (HySniperConfig.itemCheck && Keyboard.getEventKey() == Keyboard.KEY_BACKSLASH && Keyboard.getEventKeyState()) {
            if (e.gui instanceof GuiContainer) {
                GuiContainer gui = (GuiContainer) e.gui;
                Slot slot = gui.getSlotUnderMouse();
                if (slot != null && slot.getHasStack()) {
                    long price = getPriceFromNBT(slot.getStack().getTagCompound());
                    UTextComponent component = new UTextComponent("&9&lCHECK!&r&b >>>&e [" + Utils.formatPrice(price) + "]");
                    component.chat();
                }
            }
        }
    }
     */

    public HashMap<String, Double> getLowestBins() {
        return lowestBins;
    }
}