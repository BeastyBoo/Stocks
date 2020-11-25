package com.github.beastyboo.stocks.application;

import org.bukkit.plugin.java.JavaPlugin;

public final class StocksPlugin extends JavaPlugin {

    private Stocks core;

    @Override
    public void onEnable() {
       core = new Stocks(this);
       core.load();

    }

    @Override
    public void onDisable() {
        core.close();
        core = null;
    }

}
