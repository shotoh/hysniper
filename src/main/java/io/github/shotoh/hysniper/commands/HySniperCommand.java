package io.github.shotoh.hysniper.commands;

import io.github.shotoh.hysniper.HySniper;
import io.github.shotoh.hysniper.core.HySniperConfig;
import io.github.shotoh.hysniper.utils.Utils;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
            HySniper.CONFIG.openGui();
        } else {
            if (args[0].equalsIgnoreCase("clear")) {
                mod.getAuctionChecker().clearOldAuctions();
                Utils.addMessage("§5Cleared old auctions!");
            } else if (args[0].equalsIgnoreCase("dev")) {
                mod.setDev(!mod.isDev());
                Utils.addMessage("§5Developer Mode is now set to: §d" + mod.isDev());
            } else if (args[0].equals("price")) {
                if (args.length > 1) {
                    try {
                        String message = mod.getClipboard().get(UUID.fromString(args[1]));
                        if (message != null) {
                            Utils.addMessage("§5Copied message!");
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