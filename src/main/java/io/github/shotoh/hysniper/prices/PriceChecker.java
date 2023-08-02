package io.github.shotoh.hysniper.prices;

import gg.essential.universal.wrappers.message.UTextComponent;
import io.github.shotoh.hysniper.HySniper;
import io.github.shotoh.hysniper.core.HySniperConfig;
import io.github.shotoh.hysniper.utils.Utils;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.event.ClickEvent;
import net.minecraft.inventory.Slot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

public class PriceChecker {
    private final HySniper mod;
    private final HashMap<String, SkyblockItem> prices;

    public PriceChecker(HySniper mod) {
        this.mod = mod;
        this.prices = new HashMap<>();
    }

    public HashMap<String, SkyblockItem> getPrices() {
        return prices;
    }

    /*
    public void addLowestBin(AuctionItem item) {
        String name = Utils.stripItemName(item.getItemName());
        if (name.equals("Armor")) {
            return;
        }
        long lowestPrice = lowestBin.getOrDefault(name, 0L);
        if (item.getStartingBid() <= lowestPrice || lowestPrice == 0) {
            lowestBin.put(name, item.getStartingBid());
        }
    }
     */

    public PriceInfo checkPrice(NBTTagCompound tag) {
        long price = 0;
        StringBuilder builder = new StringBuilder();
        if (tag.hasKey("i")) {
            tag = tag.getTagList("i", 10).getCompoundTagAt(0).getCompoundTag("tag");
        }
        if (tag.hasKey("display") && tag.hasKey("ExtraAttributes")) {
            NBTTagCompound display = tag.getCompoundTag("display");
            NBTTagCompound ea = tag.getCompoundTag("ExtraAttributes");
            String name = display.getString("Name");
            builder.append("Lowballing ").append(StringUtils.stripControlCodes(name)).append("\n");
            String id = ea.getString("id");
            price += mod.getAuctionChecker().getLowestBins().getOrDefault(id, 0.0).longValue();
            builder.append("Base Price: ").append(Utils.formatPrice(price)).append("\n");
            price += getPrice(builder, "RECOMBOBULATOR_3000", ea.getInteger("rarity_upgrades"));
            price += getPrice(builder, "HOT_POTATO_BOOK", Math.min(ea.getInteger("hot_potato_count"), 10));
            price += getPrice(builder, "FUMING_POTATO_BOOK", (ea.getInteger("hot_potato_count") - 10));
            price += getPrice(builder, "THE_ART_OF_WAR", ea.getInteger("art_of_war_count"));
            price += getPrice(builder, "THE_ART_OF_PEACE", ea.getInteger("artOfPeaceApplied"));
            int stars = Math.max(ea.getInteger("dungeon_item_level"), ea.getInteger("upgrade_level"));
            price += getPrice(builder, "FIRST_MASTER_STAR", stars > 5 ? 1 : 0);
            price += getPrice(builder, "SECOND_MASTER_STAR", stars > 6 ? 1 : 0);
            price += getPrice(builder, "THIRD_MASTER_STAR", stars > 7 ? 1 : 0);
            price += getPrice(builder, "FOURTH_MASTER_STAR", stars > 8 ? 1 : 0);
            price += getPrice(builder, "FIFTH_MASTER_STAR", stars > 9 ? 1 : 0);
            if (ea.hasKey("ability_scroll")) {
                NBTTagList scrolls = ea.getTagList("ability_scroll", 8);
                for (int i = 0; i < scrolls.tagCount(); i++) {
                    price += getPrice(builder, scrolls.getStringTagAt(i), 1);
                }
            }
            if (name.contains("Jaded")) {
                price += getPrice(builder, "JADERALD", 1);
            } else if (name.contains("Mossy")) {
                price += getPrice(builder, "OVERGROWN_GRASS", 1);
            }
            if (ea.hasKey("enchantments")) {
                NBTTagCompound enchantments = ea.getCompoundTag("enchantments");
                for (String key : enchantments.getKeySet()) {
                    int number = 1;
                    String enchantName = "ENCHANTMENT_" + key.toUpperCase(Locale.ROOT) + "_" + enchantments.getInteger(key);
                    if (enchantName.contains("ULTIMATE")) {
                        number = (int) Math.pow(2, enchantments.getInteger(key) - 1);
                        enchantName = enchantName.substring(0, enchantName.length() - 1) + "1";
                    }
                    price += getPrice(builder, enchantName, number);
                }
            }
        }
        return new PriceInfo(price, builder);
    }

    public void sendPriceInfo(long price, StringBuilder builder) {
        UUID uuid = UUID.randomUUID();
        mod.getClipboard().put(uuid, builder.toString());
        UTextComponent component = new UTextComponent("&5[&r&d&l!&r&5] &l" + Utils.formatPrice(price));
        component.setClickAction(ClickEvent.Action.RUN_COMMAND);
        component.setClickValue("/hs price " + uuid);
        component.chat();
    }

    private double getPrice(StringBuilder builder, String id, int multiplier) {
        SkyblockItem skyblockItem = prices.get(id);
        if (skyblockItem != null) {
            if (multiplier <= 0) {
                return 0;
            }
            builder.append(skyblockItem.getName()).append(" (x").append(multiplier)
                    .append("): +").append(Utils.formatPrice(skyblockItem.getPrice() * multiplier)).append("\n");
            return skyblockItem.getPrice() * multiplier;
        } else {
            return 0;
        }
    }

    @SubscribeEvent
    public void onKeyboardInputPre(GuiScreenEvent.KeyboardInputEvent.Pre e) {
        if (!Utils.isInSkyblock()) {
            return;
        }
        if (HySniperConfig.lowballing && Keyboard.getEventKey() == Keyboard.KEY_BACKSLASH && Keyboard.getEventKeyState()) {
            if (e.gui instanceof GuiContainer) {
                GuiContainer gui = (GuiContainer) e.gui;
                Slot slot = gui.getSlotUnderMouse();
                if (slot != null && slot.getHasStack()) {
                    PriceInfo priceInfo = checkPrice(slot.getStack().getTagCompound());
                    long price = priceInfo.getPrice();
                    StringBuilder builder = priceInfo.getBuilder();
                    double tax = price * HySniperConfig.lowballingPricePercent;
                    price -= tax;
                    builder.append("Lowball Tax: -").append(Utils.formatPrice((long) tax)).append("\n");
                    builder.append("Total Price: ").append(Utils.formatPrice(price));
                    sendPriceInfo(price, builder);
                }
            }
        }
    }
}
