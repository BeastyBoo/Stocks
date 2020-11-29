package com.github.beastyboo.stocks.adapter.controller.listener;

import com.github.beastyboo.stocks.adapter.type.StockInventory;
import com.github.beastyboo.stocks.adapter.type.StockType;
import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.entity.StockEntity;
import com.github.beastyboo.stocks.domain.entity.StockHolderEntity;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Torbie on 27.11.2020.
 */
public class InventoryEvent implements Listener{

    private final Stocks core;

    public InventoryEvent(Stocks core) {
        this.core = core;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if(e.getInventory().getHolder() == null || !(e.getInventory().getHolder() instanceof StockInventory)) {
            return;
        }

        if(!(e.getWhoClicked() instanceof Player)) {
            return;
        }

        Player p = (Player) e.getWhoClicked();

        if(e.getCurrentItem()==null || e.getCurrentItem().getType()== Material.AIR||!e.getCurrentItem().hasItemMeta()){
            return;
        }

        e.setCancelled(true);
        final ItemStack item = e.getCurrentItem();
        final StockInventory inv = (StockInventory) e.getInventory().getHolder();
        final StockHolderEntity entity = inv.getStockHolderEntity();
        final StockEntity stock = inv.getStocksBySlot().get(e.getSlot());

        switch(item.getType()) {
            case RED_CONCRETE:
                if(stock == null) {
                    break;
                }
                if(e.getClick() == ClickType.LEFT) {
                    break;
                }
                if(!inv.getTarget().equals(p.getUniqueId())) {
                    break;
                }
                e.getInventory().removeItem(item);
                this.saleStock(p, entity, stock);
                p.updateInventory();
                break;
            case GREEN_CONCRETE:
                if(stock == null) {
                    break;
                }
                if(e.getClick() == ClickType.LEFT) {
                    break;
                }
                if(!inv.getTarget().equals(p.getUniqueId())) {
                    break;
                }
                e.getInventory().removeItem(item);
                this.saleStock(p, entity, stock);
                p.updateInventory();
                break;
            default:
                break;
        }

    }

    private void saleStock(Player p, StockHolderEntity entity, StockEntity stock) {
        double currentWorth = stock.getStock().getQuote().getPrice().doubleValue() * stock.getShareAmount();
        double boughtPrice = stock.getBoughtPrice();
        if(stock.getType() == StockType.SHORT) {
            double depositAmount = (boughtPrice / currentWorth) * boughtPrice;
            core.getEconomy().depositPlayer(p, depositAmount);
        } else {
            core.getEconomy().depositPlayer(p, currentWorth);
        }
        entity.getStocks().remove(stock);
        core.getStockConfig().deleteStock(stock);
    }
}
