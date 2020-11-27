package com.github.beastyboo.stocks.application;

import com.github.beastyboo.stocks.config.StockConfig;
import com.github.beastyboo.stocks.config.StockHolderConfig;
import com.github.beastyboo.stocks.usecase.util.FileUtil;
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
    private final StockConfig stockConfig;
    private final StockHolderConfig stockHolderConfig;
    private final FileUtil fileUtil;
    private Economy economy;

    public Stocks(JavaPlugin plugin) {
        this.plugin = plugin;
        stockConfig = new StockConfig(this);
        stockHolderConfig = new StockHolderConfig(this);
        fileUtil = new FileUtil();
        this.economy = null;
    }

    public void load() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "Loading up Stocks.");

        if (!setupEconomy() ) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Stocks - Disabled due to no Vault dependency found!");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }

        stockConfig.load();
        stockHolderConfig.load();
    }

    public void close() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Closing down Stocks.");

        stockHolderConfig.close();
        stockConfig.close();
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

    public FileUtil getFileUtil() {
        return fileUtil;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public StockConfig getStockConfig() {
        return stockConfig;
    }

    public Economy getEconomy() {
        return economy;
    }
}
