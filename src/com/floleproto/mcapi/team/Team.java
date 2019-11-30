package com.floleproto.mcapi.team;

import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_14_R1.EntityPlayer;
import net.minecraft.server.v1_14_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_14_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

public class Team {
	
	private String name;
	private String prefix = "";
	private String suffix = "";
	private ItemStack icon;
	private static Map<Player, Team> teams;
	private ChatColor color;
	
	public Team(String name) {
		this.name = name;
	}
	
	public Team(String name, ChatColor color) {
		this.name = name;
		this.color = color;
	}
	
	public Team(String name, ChatColor color, String prefix) {
		this.name = name;
		this.color = color;
		this.prefix = prefix;
	}
	
	public Team(String name, ChatColor color, String prefix, String suffix) {
		this.name = name;
		this.color = color;
		this.prefix = prefix;
		this.suffix = suffix;
	}
	
	public Team(String name, ChatColor color, String prefix, String suffix, ItemStack icon) {
		this.name = name;
		this.color = color;
		this.prefix = prefix;
		this.suffix = suffix;
		this.icon = icon;
	}
	
	public String getName() { return this.name;}
	
	public void setName(String name) {this.name = name;}
	
	public String getSuffix() {return this.suffix;}
	
	public void setSuffix(String suffix) {this.suffix = suffix;}
	
	public String getPrefix() {return this.prefix;}
	
	public void setPrefix(String prefix) {this.prefix = prefix;}
	
	public ChatColor getColor() {return this.color;}
	
	public void setColor(ChatColor color) {this.color = color;}
	
	public ItemStack getIcon() {return this.icon;}
	
	public void setIcon(ItemStack icon) {this.icon = icon;}
	
	public void addPlayer(Player p) {
		
		if(teams.containsKey(p)) {
			teams.remove(p);
		}
		
		teams.put(p, this);
		
	}
	
	public void removePlayer(Player p) {
		if(teams.containsKey(p)) {
			teams.remove(p);
		}

		
		p.setPlayerListName(p.getCustomName());
	}
	
	public static String getFormattedPlayerName(Player p) {
		Team t = teams.get(p);
		return t.color + t.prefix + " " + p.getCustomName() + " " + t.getSuffix();
	}
	
	public static Team getPlayerTeam(Player p) {
		return teams.get(p);
	}
	
}

