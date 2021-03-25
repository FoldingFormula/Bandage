package me.Folding.Bandage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener {
	
	public List<String> list = new ArrayList<String>();
	
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
			
		if(label.equalsIgnoreCase("bandage")) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("Console doesn't need bandages!");
			return true;
		}
		
		Player player = (Player) sender;
		
		if (player.getInventory().firstEmpty() == -1) {
			Location loc = player.getLocation();
			World world = player.getWorld();
			
			world.dropItemNaturally(loc, getBandage());
			player.sendMessage(ChatColor.GOLD + "Dropped a bandage!!");
			return true;
		}
		player.getInventory().addItem(getBandage());
		
			
		}
		
		
		return false;
	}

	
	
	
	public ItemStack getBandage() {
		ItemStack item = new ItemStack(Material.PAPER);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Bandage");
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.translateAlternateColorCodes('&', "&7(Right Click) &a&o: Heal 10HP"));
		
		meta.setLore(lore); // create the lore using the lore array

		
		item.setItemMeta(meta); // Finalize everything
		
		return item;
	}
	
	
	


	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerUse(PlayerInteractEvent event){
	    Player player = event.getPlayer();
	 
    	if((player.getInventory()).getItemInMainHand() != null)
    		if((player.getInventory()).getItemInMainHand().getItemMeta().getDisplayName().contains("Bandage"))
    			if(player.getInventory().getItemInMainHand().getItemMeta().hasLore())
    				player.setHealth(Math.min(20, player.getHealth() + 10.0));
	    
	    }
	
	
	
	
	
	
}
