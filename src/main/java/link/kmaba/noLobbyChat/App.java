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

    @Override
    public void onEnable() {
        saveDefaultConfig();
        noChatMessage = getConfig().getString("message", "&c&lYou cannot chat in lobby, join a server to chat.");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (event.getPlayer().hasPermission("nolobbychat.admin")) {
            return;
        }
        event.setCancelled(true);
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', noChatMessage));
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
            sender.sendMessage(ChatColor.GRAY + "Use /nlc <message> to change it");
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