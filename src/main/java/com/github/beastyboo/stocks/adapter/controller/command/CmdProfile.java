package com.github.beastyboo.stocks.adapter.controller.command;

import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.port.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Created by Torbie on 27.11.2020.
 */
public class CmdProfile  implements SubCommand {

    private final Stocks core;

    public CmdProfile(Stocks core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(Player player, String[] args) {

         if(args.length == 0) {

            core.getStockHolderConfig().showProfile(player, player);

        } else {

            Player target = Bukkit.getPlayer(args[0]);

            if(target == null) {
                player.sendMessage(ChatColor.RED + args[0] + " was not found...");
                return true;
            }

            core.getStockHolderConfig().showProfile(target, player);

        }

        return true;
    }

    @Override
    public String name() {
        return "profile";
    }

    @Override
    public String info() {
        return "/stock profile <target>";
    }

    @Override
    public String permission() {
        return "stocks.profile";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
