package io.github.shotoh.hysniper.bazaar;

import io.github.shotoh.hysniper.HySniper;
import io.github.shotoh.hysniper.core.HySniperConfig;
import io.github.shotoh.hysniper.prices.PriceChecker;
import io.github.shotoh.hysniper.prices.SkyblockItem;
import io.github.shotoh.hysniper.utils.Utils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BazaarChecker {
    public static void checkBazaar() {
        try {
            Utils.addMessage("§7Scanning Bazaar...");
            CompletableFuture<BazaarPage> future = CompletableFuture.supplyAsync(BazaarChecker::getBazaarPage);
            BazaarPage page = future.get();
            if (page.isSuccess()) {
                // misc
                addPrice(page, "THE_ART_OF_WAR", "Art of War", 0.6);
                addPrice(page, "FUMING_POTATO_BOOK", "Fuming Potato Book", 0.6);
                addPrice(page, "THE_ART_OF_PEACE", "Art of Peace", 0.8);
                addPrice(page, "RECOMBOBULATOR_3000", "Recombobulator", 0.8);
                addPrice(page, "HOT_POTATO_BOOK", "Hot Potato Book", 1.0);
                addPrice(page, "IMPLOSION_SCROLL", "Implosion", 1.0);
                addPrice(page, "SHADOW_WARP_SCROLL", "Shadow Warp", 1.0);
                addPrice(page, "WITHER_SHIELD_SCROLL", "Wither Shield", 1.0);
                addPrice(page, "JADERALD", "Jaded Reforge", 1.0);
                addPrice(page, "OVERGROWN_GRASS", "Mossy Reforge", 1.0);
                // enchants
                addPrice(page, "ENCHANTMENT_GROWTH_6", "Growth 6", 0.85);
                addPrice(page, "ENCHANTMENT_BIG_BRAIN_5", "Big Brain 5", 0.35);
                addPrice(page, "ENCHANTMENT_CRITICAL_7", "Critical 7", 0.85);
                addPrice(page, "ENCHANTMENT_ENDER_SLAYER_7", "Ender Slayer 7", 0.85);
                addPrice(page, "ENCHANTMENT_EXECUTE_6", "Execute 6", 0.85);
                addPrice(page, "ENCHANTMENT_FIRST_STRIKE_5", "First Strike 5", 0.85);
                addPrice(page, "ENCHANTMENT_GIANT_KILLER_7", "Giant Killer 7", 0.85);
                addPrice(page, "ENCHANTMENT_GROWTH_7", "Growth 7", 0.85);
                addPrice(page, "ENCHANTMENT_LOOTING_5", "Looting 5", 0.85);
                addPrice(page, "ENCHANTMENT_LUCK_7", "Luck 7", 0.85);
                addPrice(page, "ENCHANTMENT_OVERLOAD_5", "Overload 5", 0.35);
                addPrice(page, "ENCHANTMENT_POWER_7", "Power 7", 0.85);
                addPrice(page, "ENCHANTMENT_PRISTINE_5", "Pristine 5", 0.85);
                addPrice(page, "ENCHANTMENT_PROSECUTE_6", "Prosecute 6", 0.85);
                addPrice(page, "ENCHANTMENT_PROTECTION_7", "Protection 7", 0.85);
                addPrice(page, "ENCHANTMENT_SHARPNESS_7", "Sharpness 7", 0.85);
                addPrice(page, "ENCHANTMENT_SNIPE_4", "Snipe 4", 0.85);
                addPrice(page, "ENCHANTMENT_VENOMOUS_6", "Venomous 6", 0.85);
                addPrice(page, "ENCHANTMENT_VICIOUS_5", "Vicious 5", 0.85);
                addPrice(page, "ENCHANTMENT_ULTIMATE_CHIMERA_1", "Chimera 1", 0.85);
                addPrice(page, "ENCHANTMENT_ULTIMATE_REITERATE_1", "Duplex 1", 0.85);
                addPrice(page, "ENCHANTMENT_ULTIMATE_LAST_STAND_1", "Last Stand 1", 0.85);
                addPrice(page, "ENCHANTMENT_ULTIMATE_LEGION_1", "Legion 1", 0.85);
                addPrice(page, "ENCHANTMENT_ULTIMATE_SOUL_EATER_1", "Soul Eater 1", 0.35);
                addPrice(page, "ENCHANTMENT_ULTIMATE_WISE_1", "Ultimate Wise 1", 0.85);
                addPrice(page, "ENCHANTMENT_ULTIMATE_WISDOM_1", "Wisdom 1", 0.85);
                // stars
                addPrice(page, "FIRST_MASTER_STAR", "First Master Star", 1.0);
                addPrice(page, "SECOND_MASTER_STAR", "Second Master Star", 1.0);
                addPrice(page, "THIRD_MASTER_STAR", "Third Master Star", 1.0);
                addPrice(page, "FOURTH_MASTER_STAR", "Fourth Master Star", 1.0);
                addPrice(page, "FIFTH_MASTER_STAR", "Fifth Master Star", 1.0);
                /*
                addLowballPrice(page, "PERFECT_RUBY_GEM", "Perfect Ruby", 1.0);
                addLowballPrice(page, "PERFECT_AMETHYST_GEM", "Perfect Amethyst", 1.0);
                addLowballPrice(page, "PERFECT_JADE_GEM", "Perfect Jade", 1.0);
                addLowballPrice(page, "PERFECT_TOPAZ_GEM", "Perfect Topaz", 1.0);
                addLowballPrice(page, "PERFECT_SAPPHIRE_GEM", "Perfect Sapphire", 1.0);
                addLowballPrice(page, "PERFECT_JASPER_GEM", "Perfect Jasper", 1.0);
                addLowballPrice(page, "PERFECT_AMBER_GEM", "Perfect Amber", 1.0);
                 */
            } else {
                Utils.addMessage("§cFailed to fetch bazaar");
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static BazaarPage getBazaarPage() {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet httpGet = new HttpGet("https://api.hypixel.net/v2/skyblock/bazaar");
            httpGet.addHeader("content-type", "application/json; charset=UTF-8");
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            return HySniper.GSON.fromJson(new InputStreamReader(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8), BazaarPage.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static long getProductPrice(BazaarPage bazaarPage, String id, double percent) {
        BazaarQuickStatus quickStatus = bazaarPage.getProducts().get(id).getQuickStatus();
        if (HySniperConfig.bazaarAlgorithm == 0) {
            return (long) (quickStatus.getBuyPrice() * percent);
        } else if (HySniperConfig.bazaarAlgorithm == 1) {
            return (long) (quickStatus.getSellPrice() * percent);
        }
        return 0;
    }

    private static void addPrice(BazaarPage page, String id, String name, double percent) {
        PriceChecker.BAZAAR_PRICES.put(id, new SkyblockItem(id, name, percent, getProductPrice(page, id, percent)));
    }
}
