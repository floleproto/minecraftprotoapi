package com.floleproto.mcapi.utils;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

import net.minecraft.server.v1_14_R1.EntityPlayer;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_14_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_14_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_14_R1.PacketPlayOutPlayerInfo.EnumPlayerInfoAction;

public class DisplayName {
	public static void setDisplayName(Player p, String name) {
		
		for(Player other : Bukkit.getServer().getOnlinePlayers()) {
			
			CraftPlayer otherCraft = (CraftPlayer) other;
			GameProfile gp = new GameProfile(p.getUniqueId(), name);
			CraftPlayer craftP = (CraftPlayer) p;
			EntityPlayer newP = new EntityPlayer(craftP.getHandle().server, craftP.getHandle().getWorldServer(), gp, craftP.getHandle().playerInteractManager);
			
			otherCraft.getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, craftP.getHandle()));;
			otherCraft.getHandle().playerConnection.sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, newP));
			
			if(other == p) { continue; }
			
			otherCraft.getHandle().playerConnection.sendPacket(new PacketPlayOutEntityDestroy(craftP.getEntityId()));
			otherCraft.getHandle().playerConnection.sendPacket(new PacketPlayOutNamedEntitySpawn(craftP.getHandle()));
		}
	}
}
