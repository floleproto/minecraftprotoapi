package com.floleproto.mcapi.items;

import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

public class BannerCreator {

	private Material material;
	private List<Pattern> patterns;
	private int amount = 1;
	private String name = null;
	private List<String> lores = null;
	private Map<Enchantment, Integer> enchantments = null;
	private List<ItemFlag> itemFlags = null;
	
	public BannerCreator(Material material, List<Pattern> patterns, int amount) {
		this.material = material;
		this.patterns = patterns;
		this.amount = amount;
	}
	
	public BannerCreator(Material material, List<Pattern> patterns, int amount, String name) {
		this.material = material;
		this.patterns = patterns;
		this.amount = amount;
		this.name = name;
	}
	
	public BannerCreator(Material material, List<Pattern> patterns, int amount, String name, List<String> lores) {
		this.material = material;
		this.patterns = patterns;
		this.amount = amount;
		this.name = name;
		this.lores = lores;
	}
	
	public BannerCreator(Material material, List<Pattern> patterns, int amount, String name, List<String> lores, Map<Enchantment, Integer> enchantments) {
		this.material = material;
		this.patterns = patterns;
		this.amount = amount;
		this.name = name;
		this.lores = lores;
		this.enchantments = enchantments;
	}
	
	public BannerCreator(Material material, List<Pattern> patterns, int amount, String name, List<String> lores, Map<Enchantment, Integer> enchantments, List<ItemFlag> itemFlags) {
		this.material = material;
		this.patterns = patterns;
		this.amount = amount;
		this.name = name;
		this.lores = lores;
		this.enchantments = enchantments;
		this.itemFlags = itemFlags;
	}
	
	public Material getMaterial() { return this.material; }
	
	public void setMaterial(Material material) { this.material = material; }
	
	public int getAmount() { return this.amount; }
	
	public void setPatterns(List<Pattern> patterns) { this.patterns = patterns; }
	
	public List<Pattern> getPatterns() { return this.patterns; }
	
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
		
		ItemStack item = new ItemStack(material);
		BannerMeta itemM = (BannerMeta) item.getItemMeta();
		
		item.setAmount(amount);
		
		itemM.setPatterns(patterns);
		
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
		
		item.setItemMeta(itemM);
		return item;
	}
	
}
