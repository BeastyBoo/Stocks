package com.github.beastyboo.stocks.usecase.command;

import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.entity.CommandEntity;
import com.github.beastyboo.stocks.domain.port.SubCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Torbie on 25.11.2020.
 */
public class CmdHelp implements SubCommand{

    private final Stocks core;
    private final int perPage;
    private final CommandEntity entity;

    public CmdHelp(Stocks core, CommandEntity entity) {
        this.core = core;
        this.entity = entity;
        perPage = 6;
    }

    private List<String> getSubCommandUsage(){
        List<String> subCommandUsage = new ArrayList<>();

        for(SubCommand sub : entity.getSubCommands().values()) {
            if(!sub.equals(this)) {
                subCommandUsage.add(sub.info());
            }
        }
        return subCommandUsage;
    }


    @Override
    public boolean onCommand(Player player, String[] args) {
        if(args.length == 0) {
            sendCommands(player, 1);
        }
        else if(args.length == 1) {
            int page;
            try {
                page = Integer.parseInt(args[0]);
            } catch (NumberFormatException ex) {
                player.sendMessage(ChatColor.RED + "You did not specify a valid page");
                return false;
            }

            if(page > 0 && page <= getTotalPages()) {
                sendCommands(player, page);
            } else {
                player.sendMessage(ChatColor.RED + "You did not specify a valid page");
            }

        }

        return true;
    }

    private void sendCommands(Player p, int page) {

        List<String> subCommandUsage = getSubCommandUsage();

        int displayPage = page;
        page = page - 1;

        int lowerBound = perPage * page;
        int upperBound;

        if(lowerBound + perPage > subCommandUsage.size()) {
            upperBound = subCommandUsage.size();
        } else {
            upperBound = lowerBound + perPage;
        }

        List<String> shownCommands = new ArrayList<>();
        for(int i = lowerBound; i < upperBound; i++) {
            shownCommands.add(subCommandUsage.get(i));
        }

        p.sendMessage(ChatColor.RED + "Commands: [Page " + displayPage + "/" + getTotalPages() + "]");
        for(String cmd : shownCommands) {
            p.sendMessage(ChatColor.GREEN + "- " + ChatColor.GRAY + cmd);
        }

    }

    private int getTotalPages() {
        return (int) Math.ceil((double) this.getSubCommandUsage().size() / perPage);
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public String info() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String permission() {
        return entity.getCommandName().toLowerCase() + ".help";
    }

    @Override
    public String[] aliases() {
        return new String[] {
                "h"
        };
    }
}
