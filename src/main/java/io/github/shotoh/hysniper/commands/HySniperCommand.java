package io.github.shotoh.hysniper.commands;

import gg.essential.api.EssentialAPI;
import gg.essential.universal.UChat;
import io.github.shotoh.hysniper.HySniper;
import io.github.shotoh.hysniper.core.HySniperConfig;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class HySniperCommand extends CommandBase {
    private final HySniper mod;

    public HySniperCommand(HySniper mod) {
        this.mod = mod;
    }

    @Override
    public String getCommandName() {
        return "hysniper";
    }

    @Override
    public List<String> getCommandAliases() {
        return Collections.singletonList("hs");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/hysniper";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            EssentialAPI.getGuiUtil().openScreen(HySniperConfig.INSTANCE.gui());
        } else {
            if (args[0].equalsIgnoreCase("clear")) {
                mod.getAuctionChecker().clearOldAuctions();
                UChat.chat("&5Cleared old auctions!");
            } else if (args[0].equalsIgnoreCase("dev")) {
                mod.setDev(!mod.isDev());
                UChat.chat("&5Developer Mode is now set to: &d" + mod.isDev());
            } else if (args[0].equals("price")) {
                if (args.length > 1) {
                    try {
                        String message = mod.getClipboard().get(UUID.fromString(args[1]));
                        if (message != null) {
                            UChat.chat("&5Copied message!");
                            GuiScreen.setClipboardString(message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}