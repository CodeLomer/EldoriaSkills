package com.github.codelomer.eldoriaskills.util;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class SimpleLogger {

    private static final ConsoleCommandSender CONSOLE = Bukkit.getConsoleSender();
    private final String prefix;

    public SimpleLogger(@NonNull Class<?> clazz) {
        prefix = "["+clazz.getSimpleName()+"] ";
    }

    public SimpleLogger(@NonNull JavaPlugin plugin){
        prefix = "["+plugin.getName()+"] ";
    }
    
    public SimpleLogger(@NonNull String prefix) {
        this.prefix = prefix;
    }

    public  void sendMessage(String message, CommandSender sender, boolean usePrefix, ChatColor color) {
        if (message == null || sender == null) return;
        message = ColorUtilities.toColorText(message);
        if (usePrefix) message = prefix + message;
        if (color != null) message = color + message;
        sender.sendMessage(message);
    }

    public void sendMessage(String message, ChatColor color) {
        sendMessage(message, CONSOLE, true, color);
    }

    public void errorMessage(String message, CommandSender sender, boolean usePrefix) {
        sendMessage(message, sender, usePrefix, ChatColor.RED);
    }

    public void fineMessage(String message, CommandSender sender, boolean usePrefix) {
        sendMessage(message, sender, usePrefix, ChatColor.GREEN);
    }

    public void warnMessage(String message, CommandSender sender, boolean usePrefix) {
        sendMessage(message, sender, usePrefix, ChatColor.YELLOW);
    }

    public void infoMessage(String message, CommandSender sender, boolean usePrefix) {
        sendMessage(message, sender, usePrefix, ChatColor.BLUE);
    }

    public void errorMessage(String message) {
        sendMessage(message, ChatColor.RED);
    }

    public void fineMessage(String message) {
        sendMessage(message, ChatColor.GREEN);
    }

    public void warnMessage(String message) {
        sendMessage(message, ChatColor.YELLOW);
    }

    public void infoMessage(String message) {
        sendMessage(message, ChatColor.BLUE);
    }

    public void sendMessages(List<String> messages, CommandSender sender, boolean usePrefix, ChatColor color) {
        if (messages == null || sender == null) return;
        for (String message : messages) {
            sendMessage(message, sender, usePrefix, color);
        }
    }

    public void errorMessages(List<String> messages, CommandSender sender, boolean usePrefix) {
        sendMessages(messages, sender, usePrefix, ChatColor.RED);
    }

    public void fineMessages(List<String> messages, CommandSender sender, boolean usePrefix) {
        sendMessages(messages, sender, usePrefix, ChatColor.GREEN);
    }

    public void warnMessages(List<String> messages, CommandSender sender, boolean usePrefix) {
        sendMessages(messages, sender, usePrefix, ChatColor.YELLOW);
    }

    public void infoMessages(List<String> messages, CommandSender sender, boolean usePrefix) {
        sendMessages(messages, sender, usePrefix, ChatColor.BLUE);
    }

    public void errorMessages(List<String> messages) {
        sendMessages(messages, CONSOLE, true, ChatColor.RED);
    }

    public void fineMessages(List<String> messages) {
        sendMessages(messages, CONSOLE, true, ChatColor.GREEN);
    }

    public void warnMessages(List<String> messages) {
        sendMessages(messages, CONSOLE, true, ChatColor.YELLOW);
    }

    public void infoMessages(List<String> messages) {
        sendMessages(messages, CONSOLE, true, ChatColor.BLUE);
    }
}
