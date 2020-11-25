package com.github.beastyboo.stocks.domain.port;

import org.bukkit.entity.Player;

/**
 * Created by Torbie on 25.11.2020.
 */
public interface SubCommand {

    boolean onCommand(Player player, String[] args);
    String name();
    String info();
    String permission();
    String[] aliases();

}
