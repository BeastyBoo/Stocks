package com.github.beastyboo.stocks.adapter.controller.command;

import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.port.SubCommand;
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
        return false;
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String info() {
        return null;
    }

    @Override
    public String permission() {
        return null;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
