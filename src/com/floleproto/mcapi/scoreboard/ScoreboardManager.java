package com.floleproto.mcapi.scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager implements Listener{
	
	private Map<UUID, Scoreboard> scoreboard = new HashMap<UUID, Scoreboard>();
	private List<Line> lines = new ArrayList<Line>();
	private String title;
	
	public ScoreboardManager(String title) {
		this.title = title;
	}
	
	public void registerPlayer(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
		scoreboard.put(p.getUniqueId(), board);
	}
	
	public void removePlayer(Player p) {
		if (scoreboard.containsKey(p.getUniqueId())){
			scoreboard.remove(p.getUniqueId());
		}
	}
	
	public Scoreboard getScoreboard(Player p) {
		return scoreboard.get(p.getUniqueId());
	}
	
	public void addLine(Line line) {
		
		if(hasLine(line.getName())) { return; }
		lines.add(line);
		
	}
	
	public void removeLineToAll(String name) {
		for(Line l : lines) {
			if(l.getName() == name) {
				lines.remove(l);
			}
		}
		
	}
	
	public boolean hasLine(String name) {
		for(Line l : lines) {
			if(l.getName() == name) {
				return true;
			} else {
				continue;
			}
		}
		return false;
	}
	
	public void UpdateValue() {
		for(Map.Entry<UUID, Scoreboard> entry : scoreboard.entrySet()) {
			Scoreboard board = entry.getValue();
			Objective objective = board.getObjective("board");
			if(objective != null) {
				objective.setDisplayName(title);
				objective.setDisplaySlot(DisplaySlot.SIDEBAR);
			}
		}
	}
}
