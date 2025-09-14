package link.kmaba.noLobbyChat;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin implements Listener {
    private String noChatMessage;
    private boolean silentMode;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        noChatMessage = getConfig().getString("message", "&c&lYou cannot chat in lobby, join a server to chat.");
        silentMode = getConfig().getBoolean("silent-mode", false);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().hasPermission("nolobbychat.admin")) {
            return;
        }
        event.setCancelled(true);
        if (!silentMode) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', noChatMessage));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("nlc")) return false;
        
        if (!sender.hasPermission("nolobbychat.admin")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "Current message: " + ChatColor.RESET + 
                ChatColor.translateAlternateColorCodes('&', noChatMessage));
            sender.sendMessage(ChatColor.YELLOW + "Silent mode: " + ChatColor.RESET + 
                (silentMode ? ChatColor.GREEN + "enabled" : ChatColor.RED + "disabled"));
            sender.sendMessage(ChatColor.GRAY + "Use /nlc <message> to change the message");
            sender.sendMessage(ChatColor.GRAY + "Use /nlc silent <true/false> to toggle silent mode");
            return true;
        }

        if (args.length >= 2 && args[0].equalsIgnoreCase("silent")) {
            boolean newSilentMode = Boolean.parseBoolean(args[1]);
            getConfig().set("silent-mode", newSilentMode);
            saveConfig();
            silentMode = newSilentMode;
            
            sender.sendMessage(ChatColor.GREEN + "Silent mode " + 
                (silentMode ? "enabled" : "disabled") + " successfully!");
            return true;
        }

        String newMessage = String.join(" ", args);
        getConfig().set("message", newMessage);
        saveConfig();
        noChatMessage = newMessage;
        
        sender.sendMessage(ChatColor.GREEN + "Message updated successfully!");
        return true;
    }

    @Override
    public void onDisable() {
    }
}