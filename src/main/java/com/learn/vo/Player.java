package com.learn.vo;

public class Player {
	
	// - Define a POJO class Player with playerName, matchesPlayed, runs, highestScore, Country as member variables
	private String playerName;
	private Integer matchesPlayed;
	private Integer runs;
	private Integer highestScore;
	private Country country;
	
	public Player(){
		
	}

	public Player(String playerName, Integer matchesPlayed, Integer runs, Integer highestScore, Country country) {
		super();
		this.playerName = playerName;
		this.matchesPlayed = matchesPlayed;
		this.runs = runs;
		this.highestScore = highestScore;
		this.country = country;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Integer getMatchesPlayed() {
		return matchesPlayed;
	}

	public void setMatchesPlayed(Integer matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}

	public Integer getRuns() {
		return runs;
	}

	public void setRuns(Integer runs) {
		this.runs = runs;
	}

	public Integer getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(Integer highestScore) {
		this.highestScore = highestScore;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return playerName+" :: "+matchesPlayed+" :: "+runs+" :: "+highestScore+" "+country;
	}
}
