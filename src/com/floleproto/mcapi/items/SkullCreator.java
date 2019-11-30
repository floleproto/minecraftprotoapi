package com.floleproto.mcapi.items;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.floleproto.mcapi.utils.MojangParser;

public class SkullCreator<T> {
	
	private T skullOwner;
	private int amount = 1;
	private String name = null;
	private List<String> lores = null;
	private Map<Enchantment, Integer> enchantments = null;
	private List<ItemFlag> itemFlags = null;
	
	public SkullCreator(T skullOwner, int amount) {
		this.skullOwner = skullOwner;
		this.amount = amount;
	}
	
	public SkullCreator(T skullOwner, int amount, String name) {
		this.skullOwner = skullOwner;
		this.amount = amount;
		this.name = name;
	}
	
	public SkullCreator(T skullOwner, int amount, String name, List<String> lores) {
		this.skullOwner = skullOwner;
		this.amount = amount;
		this.name = name;
		this.lores = lores;
	}
	
	public SkullCreator(T skullOwner, int amount, String name, List<String> lores, Map<Enchantment, Integer> enchantments) {
		this.skullOwner = skullOwner;
		this.amount = amount;
		this.name = name;
		this.lores = lores;
		this.enchantments = enchantments;
	}
	
	public SkullCreator(T skullOwner, int amount, String name, List<String> lores, Map<Enchantment, Integer> enchantments, List<ItemFlag> itemFlags) {
		this.skullOwner = skullOwner;
		this.amount = amount;
		this.name = name;
		this.lores = lores;
		this.enchantments = enchantments;
		this.itemFlags = itemFlags;
	}
	
	public T getSkullOwner() { return this.skullOwner; }
	
	public void setSkullOwner(T skullOwner) { this.skullOwner = skullOwner; }
	
	public int getAmount() { return this.amount; }
	
	public void setAmount(int amount) { this.amount = amount; }
	
	public String getName() { return this.name; }
	
	public void setName(String name) { this.name = name; }
	
	public List<String> getLores() { return this.lores; }
	
	public void setLores(List<String> lores) { this.lores = lores; }
	
	public Map<Enchantment, Integer> getEnchantments() { return this.enchantments; }
	
	public void setEnchantments(Map<Enchantment, Integer> enchantments) { this.enchantments = enchantments; }
	
	public List<ItemFlag> getItemFlags() {return this.itemFlags; }
	
	public void setItemFlags(List<ItemFlag> itemFlags) {this.itemFlags = itemFlags; }
	
	public ItemStack create() {
		
		ItemStack item = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta itemM = (SkullMeta) item.getItemMeta();
		
		item.setAmount(amount);
		
		if(name != null) {
			itemM.setDisplayName(name);
		}
		
		if(lores != null) {
			itemM.setLore(lores);
		}
		
		if(enchantments != null) {
			for(Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
				itemM.addEnchant(entry.getKey(), entry.getValue(), true);
			}
		}
		
		if(itemFlags != null) {
			for(ItemFlag flag : itemFlags) {
				itemM.addItemFlags(flag);
			}
		}
		
		if(skullOwner instanceof UUID) {
			itemM.setOwningPlayer(Bukkit.getOfflinePlayer((UUID)skullOwner));
		} else if(skullOwner instanceof String){
			itemM.setOwningPlayer(Bukkit.getOfflinePlayer(MojangParser.getUUIDByPlayerName((String) skullOwner)));
		} else {
			throw new IllegalArgumentException();
		}
		
		
		item.setItemMeta(itemM);
		return item;
	}
	
}
