package com.floleproto.mcapi.scoreboard;

import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class Line {
	
	private String name;
	private String content;
	private String value = "";
	private int pos;
	private Team team;
	private Scoreboard board;
	
	public Line(String name, String content, int pos, String value, Scoreboard board) {
		this.name = name;
		this.content = content;
		this.pos = pos;
		this.value = value;
		this.board = board;
		
		this.team = board.registerNewTeam(name);
		team.setSuffix("§r " + value);
		team.addEntry(content);
	}
	
	public String getName() { return this.name; }
	public String getContent() { return this.content; }
	public String getValue() { return this.value; }
	public int getPos() { return this.pos; }
	public Team getTeam() { return this.team; }
	public Scoreboard getScoreboard() { return this.board; }
	
	public void setName(String name) {
		this.name = name; 
	}
	
	public void setContent(String content) {
		team.removeEntry(content);
		board.resetScores(content);
		this.content = content;
		board.getObjective("board").getScore(content).setScore(-pos);
		team.addEntry(content);
	}
	
	public void setValue(String value) { 
		this.value = value; 
		team.setSuffix("§r" + value);
	}
	
	public void setPos(int pos) {
		this.pos = pos; 
		board.getObjective("board").getScore(content).setScore(-pos);
	}
	
	
}
