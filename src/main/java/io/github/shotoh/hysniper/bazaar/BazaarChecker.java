package io.github.shotoh.hysniper.bazaar;

import gg.essential.universal.UChat;
import io.github.shotoh.hysniper.HySniper;
import io.github.shotoh.hysniper.core.HySniperConfig;
import io.github.shotoh.hysniper.lowball.LowballItem;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BazaarChecker {
    private final HySniper mod;

    public BazaarChecker(HySniper mod) {
        this.mod = mod;
    }

    public void checkBazaar() {
        try {
            UChat.chat("&7Scanning Bazaar...");
            CompletableFuture<BazaarPage> future = CompletableFuture.supplyAsync(this::getBazaarPage, mod.getExecutor());
            BazaarPage page = future.get();
            if (page.isSuccess()) {
                addLowballPrice(page, "THE_ART_OF_WAR", "Art of War", 0.6);
                addLowballPrice(page, "FUMING_POTATO_BOOK", "Fuming Potato Book", 0.6);
                addLowballPrice(page, "THE_ART_OF_PEACE", "Art of Peace", 0.8);
                addLowballPrice(page, "RECOMBOBULATOR_3000", "Recombobulator", 0.8);
                // enchants
                addLowballPrice(page, "ENCHANTMENT_GROWTH_6", "Growth 6", 0.85);
                addLowballPrice(page, "ENCHANTMENT_BIG_BRAIN_5", "Big Brain 5", 0.35);
                addLowballPrice(page, "ENCHANTMENT_CRITICAL_7", "Critical 7", 0.85);
                addLowballPrice(page, "ENCHANTMENT_ENDER_SLAYER_7", "Ender Slayer 7", 0.85);
                addLowballPrice(page, "ENCHANTMENT_EXECUTE_6", "Execute 6", 0.85);
                addLowballPrice(page, "ENCHANTMENT_FIRST_STRIKE_5", "First Strike 5", 0.85);
                addLowballPrice(page, "ENCHANTMENT_GIANT_KILLER_7", "Giant Killer 7", 0.85);
                addLowballPrice(page, "ENCHANTMENT_GROWTH_7", "Growth 7", 0.85);
                addLowballPrice(page, "ENCHANTMENT_LOOTING_5", "Looting 5", 0.85);
                addLowballPrice(page, "ENCHANTMENT_LUCK_7", "Luck 7", 0.85);
                addLowballPrice(page, "ENCHANTMENT_OVERLOAD_5", "Overload 5", 0.35);
                addLowballPrice(page, "ENCHANTMENT_POWER_7", "Power 7", 0.85);
                addLowballPrice(page, "ENCHANTMENT_PRISTINE_5", "Pristine 5", 0.85);
                addLowballPrice(page, "ENCHANTMENT_PROSECUTE_6", "Prosecute 6", 0.85);
                addLowballPrice(page, "ENCHANTMENT_PROTECTION_7", "Protection 7", 0.85);
                addLowballPrice(page, "ENCHANTMENT_SHARPNESS_7", "Sharpness 7", 0.85);
                addLowballPrice(page, "ENCHANTMENT_SNIPE_4", "Snipe 4", 0.85);
                addLowballPrice(page, "ENCHANTMENT_VENOMOUS_6", "Venomous 6", 0.86);
                addLowballPrice(page, "ENCHANTMENT_VICIOUS_5", "Vicious 5", 0.85);
                addLowballPrice(page, "ENCHANTMENT_ULTIMATE_CHIMERA_1", "Chimera 1", 0.85);
                addLowballPrice(page, "ENCHANTMENT_ULTIMATE_REITERATE_1", "Duplex 1", 0.85);
                addLowballPrice(page, "ENCHANTMENT_ULTIMATE_LAST_STAND_1", "Last Stand 1", 0.85);
                addLowballPrice(page, "ENCHANTMENT_ULTIMATE_LEGION_1", "Legion 1", 0.85);
                addLowballPrice(page, "ENCHANTMENT_ULTIMATE_SOUL_EATER_1", "Soul Eater 1", 0.35);
                addLowballPrice(page, "ENCHANTMENT_ULTIMATE_WISE_1", "Ultimate Wise 1", 0.85);
                addLowballPrice(page, "ENCHANTMENT_ULTIMATE_WISDOM_1", "Wisdom 1", 0.85);
                //
                addLowballPrice(page, "FIRST_MASTER_STAR", "First Master Star", 1.0);
                addLowballPrice(page, "SECOND_MASTER_STAR", "Second Master Star", 1.0);
                addLowballPrice(page, "THIRD_MASTER_STAR", "Third Master Star", 1.0);
                addLowballPrice(page, "FOURTH_MASTER_STAR", "Fourth Master Star", 1.0);
                addLowballPrice(page, "FIFTH_MASTER_STAR", "Fifth Master Star", 1.0);
                /*
                addLowballPrice(page, "PERFECT_RUBY_GEM", "Perfect Ruby", 1.0);
                addLowballPrice(page, "PERFECT_AMETHYST_GEM", "Perfect Amethyst", 1.0);
                addLowballPrice(page, "PERFECT_JADE_GEM", "Perfect Jade", 1.0);
                addLowballPrice(page, "PERFECT_TOPAZ_GEM", "Perfect Topaz", 1.0);
                addLowballPrice(page, "PERFECT_SAPPHIRE_GEM", "Perfect Sapphire", 1.0);
                addLowballPrice(page, "PERFECT_JASPER_GEM", "Perfect Jasper", 1.0);
                addLowballPrice(page, "PERFECT_AMBER_GEM", "Perfect Amber", 1.0);
                 */
                addLowballPrice(page, "HOT_POTATO_BOOK", "Hot Potato Book", 1.0);
                addLowballPrice(page, "IMPLOSION_SCROLL", "Implosion", 1.0);
                addLowballPrice(page, "SHADOW_WARP_SCROLL", "Shadow Warp", 1.0);
                addLowballPrice(page, "WITHER_SHIELD_SCROLL", "Wither Shield", 1.0);
                addLowballPrice(page, "JADERALD", "Jaded Reforge", 1.0);
                addLowballPrice(page, "OVERGROWN_GRASS", "Mossy Reforge", 1.0);
            } else {
                UChat.chat("&cFailed to fetch bazaar");
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public BazaarPage getBazaarPage() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://api.hypixel.net/skyblock/bazaar");
        httpGet.addHeader("content-type", "application/json; charset=UTF-8");
        try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            return mod.getGson().fromJson(new InputStreamReader(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8), BazaarPage.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private long getProductPrice(BazaarPage bazaarPage, String id, double percent) {
        BazaarQuickStatus quickStatus = bazaarPage.getProducts().get(id).getQuickStatus();
        if (HySniperConfig.lowballingBazaarAlgorithm == 0) {
            return (long) (quickStatus.getBuyPrice() * percent);
        } else if (HySniperConfig.lowballingBazaarAlgorithm == 1) {
            return (long) (quickStatus.getSellPrice() * percent);
        }
        return 0;
    }

    private void addLowballPrice(BazaarPage page, String id, String name, double percent) {
        mod.getLowballChecker().getPrices().put(id, new LowballItem(id, name, percent, getProductPrice(page, id, percent)));
    }
}
