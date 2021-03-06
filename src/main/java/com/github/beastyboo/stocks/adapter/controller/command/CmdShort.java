package com.github.beastyboo.stocks.adapter.controller.command;

import com.github.beastyboo.stocks.adapter.type.StockType;
import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.port.SubCommand;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.math.RoundingMode;
import java.util.UUID;

/**
 * Created by Torbie on 27.11.2020.
 */
public class CmdShort  implements SubCommand {

    private final Stocks core;
    private final Economy econ;

    public CmdShort(Stocks core) {
        this.core = core;
        econ = core.getEconomy();
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        if(args.length != 2) {
            p.sendMessage(info());
            return true;
        }

        Stock stock = null;

        /**
         * Swallowing exception, as I don't want the plugin to send any erros
         * if players type a ticker name which don't exist.
         */
        try {
            stock = YahooFinance.get(args[0].toUpperCase());
        } catch (IOException ex) {
            p.sendMessage("§cCould not find the stock: " + args[0].toUpperCase());
            return true;
        }

        int i;

        /**
         * Swallowing exception, as I don't want the plugin to send any erros
         * if players type a number which don't exist.
         */
        try {
            i = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            p.sendMessage(ChatColor.RED + args[1] + " Is not a valid number.");
            return true;
        }

        double boughtPrice = stock.getQuote().getPrice().setScale(2, RoundingMode.HALF_UP).doubleValue() * i;

        if(econ.getBalance(p) < boughtPrice) {
            p.sendMessage("§cYou don't have enough money to continue with this order.");
            return true;
        }

        if(core.getStockHolderConfig().getPurchaseStock().purchaseStock(p.getUniqueId(), UUID.randomUUID(), stock, StockType.SHORT, boughtPrice, i) == true) {
            econ.withdrawPlayer(p, boughtPrice);
            p.sendMessage("§cYou have successfully shorted stocks!");
        }else {
            p.sendMessage("§cCould not proceed with purchase");
        }

        return true;
    }

    @Override
    public String name() {
        return "short";
    }

    @Override
    public String info() {
        return "/stock short <ticker/symbol> <shares/amount>";
    }

    @Override
    public String permission() {
        return "stocks.short";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
