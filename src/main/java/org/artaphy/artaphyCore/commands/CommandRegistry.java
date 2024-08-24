package org.artaphy.artaphyCore.commands;

import org.artaphy.artaphyCore.ArtaphyCore;
import org.artaphy.artaphyCore.commands.list.FlyCommand;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;

import java.util.Objects;
import java.util.ServiceLoader;

public class CommandRegistry {
    private final ArtaphyCore plugin;

    public CommandRegistry(ArtaphyCore plugin) {
        this.plugin = plugin;
    }

    public void registerCommands() {
        registerCommand("fly", new FlyCommand(plugin));
    }

    private void registerCommand(String name, CommandExecutor executor) {
        PluginCommand command = plugin.getCommand(name);
        if (command != null) {
            command.setExecutor(executor);
            plugin.getLogger().info("Succeeded to register command: " + name);
            if (executor instanceof TabCompleter) command.setTabCompleter((TabCompleter) executor);
        } else {
            plugin.getLogger().warning("Failed to register command: " + name);
        }
    }
}
