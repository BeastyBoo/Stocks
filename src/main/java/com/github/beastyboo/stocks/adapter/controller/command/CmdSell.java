package com.github.beastyboo.stocks.adapter.controller.command;

import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.port.SubCommand;
import org.bukkit.entity.Player;

/**
 * Created by Torbie on 27.11.2020.
 */
public class CmdSell implements SubCommand {

    private final Stocks core;

    public CmdSell(Stocks core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(Player player, String[] args) {

        core.getStockHolderConfig().showProfile(player, player);

        return true;
    }

    @Override
    public String name() {
        return "sell";
    }

    @Override
    public String info() {
        return "/stock sell";
    }

    @Override
    public String permission() {
        return "stocks.sell";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
