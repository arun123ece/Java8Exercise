package com.learn.e02.streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import com.learn.vo.Country;
import com.learn.vo.Player;



public class StreamOperationsExercise {
	
	public StreamOperationsExercise() {
		
	}

	static List<Player> playerList = new ArrayList<>();

	static {

		playerList.add(new Player("Sachin", 480, 28000, 241, new Country(1, "India")));
		playerList.add(new Player("Ganguli", 350, 15000, 100, new Country(1, "India")));
		playerList.add(new Player("Ponting", 400, 23000, 250, new Country(2, "Austrailia")));
		playerList.add(new Player("Cook", 285, 16000, 100, new Country(3, "England")));
		playerList.add(new Player("Smith", 100, 18500, 96, new Country(4, "South Africa")));
		playerList.add(new Player("Jayasurya", 425, 1800, 300, new Country(5, "Srilanka")));
	}
	public static void displayPlayers() {

		playerList.stream().map(p -> p.getPlayerName()).forEach(System.out :: println);
	}
	public static void displayPlayersForCountry(String country) {

		playerList.stream().filter(p -> p.getHighestScore() > 100 && p.getCountry().getCountryName().equalsIgnoreCase(country))
		.map(p -> p.getPlayerName()).forEach(System.out :: println);
	}

	public static LinkedList<String> getPlayerNames(){

		return  playerList.stream()
				.filter(p -> p.getRuns() > 5000)
				.map(m -> m.getPlayerName())
				.sorted(Comparator.reverseOrder())
				.collect(Collectors.toCollection(LinkedList :: new));
	}
	public static double getAverageRunsByCountry(String country) {

		return  playerList.stream()
				.filter(p -> p.getCountry().getCountryName().equalsIgnoreCase(country))
				.mapToDouble(Player :: getRuns)
				.average().getAsDouble();
	}

	public static List<String> getPlayerNamesSorted(){

		Comparator<Player> sortByCountry = (p1,p2) -> p1.getCountry().getCountryName().compareToIgnoreCase(p2.getCountry().getCountryName());
		Comparator<Player> sortBymatchedPalyed = (p1,p2) -> p2.getMatchesPlayed().compareTo(p1.getMatchesPlayed());

		return playerList.stream().sorted(sortByCountry.thenComparing(sortBymatchedPalyed))
				.map(p -> p.getPlayerName()).collect(Collectors.toList());
	}

	public static Map<String, String> getPlayerCountry(){

		return playerList.stream().filter(p -> p.getMatchesPlayed() > 200)
				.collect(Collectors.toMap(Player :: getPlayerName, x -> x.getCountry().getCountryName()));
	}
	public static Player getMaxRunsPlayer() {

		OptionalInt maxRun = playerList.stream().mapToInt(map -> map.getRuns()).max();
		return	playerList.stream().filter(p -> p.getRuns() ==  maxRun.getAsInt()).collect(Collectors.toList()).get(0);
	}
	public static Player findPlayer(String name, String country) {

		return  playerList.stream()
				.filter(p -> p.getCountry().getCountryName().equalsIgnoreCase(country) && p.getPlayerName().equalsIgnoreCase(name))
				.collect(Collectors.toList()).get(0);
	}
	public static boolean findPlayer(String country) {

		return playerList.stream().filter(p -> p.getCountry().getCountryName().equalsIgnoreCase(country) && p.getRuns() > 10000)
				.collect(Collectors.toList()).size() > 0;
	}

	public static void main(String[] args) {

		System.out.println("***********  List of all players ***********");
		displayPlayers();

		System.out.println("\n***********  List of all players whose highest score is > 100 for particular country ***********");
		displayPlayersForCountry("India");

		System.out.println("\n***********  List containing names of all Players, whose have scored more than 5000 runs, sorted in descending order of names ***********");
		System.out.println(getPlayerNames());

		System.out.println("\n*********** Average runs scored by players from a particular Country  ***********");
		System.out.println(getAverageRunsByCountry("India"));

		System.out.println("\n***********  List with names of Players sorted as per country and then by matchesPlayed(descending) ***********");
		System.out.println(getPlayerNamesSorted());

		System.out.println("\n*********** Map with the PlayerName and CountryName of all players who have played more than 200 matches  ***********");
		System.out.println(getPlayerCountry());

		System.out.println("\n***********  Player who has scored maximum runs ***********");
		System.out.println(getMaxRunsPlayer());

		System.out.println("\n***********  Player for a given name and country ***********");
		System.out.println(findPlayer("Sachin", "India"));

		System.out.println("\n*********** Find whether thery is any play in the given country who has scored more than 10000 runs. Return a boolean.  ***********");
		System.out.println(findPlayer("Srilanka"));
	}
}
