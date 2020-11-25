package com.github.beastyboo.stocks.domain.entity;

import com.github.beastyboo.stocks.application.Stocks;
import com.github.beastyboo.stocks.domain.port.SubCommand;
import com.github.beastyboo.stocks.usecase.command.CmdHelp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/**
 * Created by Torbie on 25.11.2020.
 */
public class CommandEntity implements CommandExecutor, TabCompleter {

    private final Stocks core;
    private final String commandName;
    private final Map<String, SubCommand> subCommands;

    public CommandEntity(Stocks core, String commandName) {
        this.core = core;
        this.commandName = commandName;
        subCommands = new HashMap<>();

        SubCommand help = new CmdHelp(core, this);
        subCommands.put(help.name(), help);
        core.getPlugin().getCommand(commandName).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase(commandName)) {

            if(!(sender instanceof Player)) {
                core.getPlugin().getLogger().log(Level.INFO, "Sender is not a player!");
                return true;
            }

            Player p = (Player) sender;

            if(args.length < 1) {
                p.sendMessage("§cUse </" + commandName + " help> to view all possible commands.. ");
                return true;
            }

            SubCommand subCommand = this.getSubCommand(args[0]);

            if(subCommand == null) {
                p.sendMessage("§cUse </" + commandName + " help> to view all possible commands.. ");
                return true;
            }
            if(!p.hasPermission(subCommand.permission())) {
                p.sendMessage("§cYou dont have permission to use this command...");
                return true;
            }

            String[] newArgs = new String[args.length - 1];
            for (int i = 0; i < newArgs.length; i++) {
                newArgs[i] = args[i + 1];
            }

            if(subCommand.onCommand(p, newArgs)) {
                return true;
            } else {
                p.sendMessage(subCommand.info());
            }
        }
        return false;
    }

    public SubCommand getSubCommand(String name) {
        if(subCommands.get(name.toLowerCase()) != null) {
            return subCommands.get(name.toLowerCase());
        }
        for(SubCommand sub : subCommands.values()) {
            for(String alias : sub.aliases()) {
                if(alias.equalsIgnoreCase(name))
                    return sub;
            }
        }
        return null;
    }

    public void registerPermission() {
        PluginManager pm = core.getPlugin().getServer().getPluginManager();
        for(SubCommand sub : subCommands.values()) {
            if(pm.getPermission(sub.permission()) == null) {
                pm.addPermission(new Permission(sub.permission()));
            }
        }
    }

    public String getCommandName() {
        return commandName;
    }

    public Map<String, SubCommand> getSubCommands() {
        return subCommands;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {

        if(cmd.getName().equalsIgnoreCase(commandName)) {

            if(args.length != 1) {
                return null;
            }

            if(!(sender instanceof Player)) {
                return null;
            }

            List<String> list = new ArrayList<>();
            for(SubCommand sub : subCommands.values()) {
                list.add(sub.name());
            }

            if(!list.isEmpty()) {
                return list;
            }
        }
        return null;
    }

}
