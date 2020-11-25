package com.github.beastyboo.stocks.application;

import com.github.beastyboo.stocks.domain.entity.StockEntity;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * Created by Torbie on 25.11.2020.
 */

public class Stocks {

    private final JavaPlugin plugin;
    private Economy economy;

    public Stocks(JavaPlugin plugin) {
        this.plugin = plugin;
        this.economy = null;
    }

    public void load() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Loading up Stocks.");

        if (!setupEconomy() ) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Stocks - Disabled due to no Vault dependency found!");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
        Stock stock = this.getStock("GOOG");

        //StockEntity entity = new StockEntity.Builder(UUID.randomUUID(), stock, 500.0).shareAmount(3).build();


    }

    public void close() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Closing down Stocks.");
    }

    private boolean setupEconomy() {
        if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = plugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    private Stock getStock(String ticker) {
        try {
            return YahooFinance.get(ticker);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public Economy getEconomy() {
        return economy;
    }
}
