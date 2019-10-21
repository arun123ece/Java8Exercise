package com.learn.e03.collectors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.learn.vo.Country;
import com.learn.vo.Player;

public class StreamCollectorsExercise {

	List<Player> playerList = new ArrayList<>();

	public StreamCollectorsExercise() {

		playerList.add(new Player("Sachin", 480, 28000, 241, new Country(1, "India")));
		playerList.add(new Player("Ganguli", 350, 15000, 100, new Country(1, "India")));
		playerList.add(new Player("Ponting", 400, 23000, 250, new Country(2, "Austrailia")));
		playerList.add(new Player("Cook", 285, 16000, 100, new Country(3, "England")));
		playerList.add(new Player("Smith", 100, 18500, 96, new Country(4, "South Africa")));
		playerList.add(new Player("Jayasurya", 425, 1800, 300, new Country(5, "Srilanka")));
	}

	//- Return a map with key as country name and value as List of players
	public  Map<String, List<Player>> getPlayersByCountry(){

		Map<String, List<Player>> gropuByCountryNameMap = playerList.stream().collect(Collectors.groupingBy(p -> p.getCountry().getCountryName()));
		return gropuByCountryNameMap;
	}

	//- Return a map with key as country name and value as List of player Names who have played more than 100 matches
	public  Map<String, List<Player>> getPlayerNamesByCountry(){

		Map<String, List<Player>> gropuByCountryNameMap = playerList.stream().filter(p -> p.getMatchesPlayed() > 100).collect(Collectors.groupingBy(p -> p.getCountry().getCountryName()));
		return gropuByCountryNameMap;
	}

	//- Return a LinkedHashMap with key as country name and value as number of players in each country
	public LinkedHashMap<String, Long> getTotalPlayersByCountry(){

		LinkedHashMap<String, Long> returnLinkedHashMap = playerList.stream()
				.collect(Collectors.groupingBy(p -> p.getCountry().getCountryName(),LinkedHashMap :: new, Collectors.counting()));
		return returnLinkedHashMap;
	}

	//- Return a map with key as country name and value as sum total runs of all players
	public Map<String, Integer> getTotalRunsByCountry(){

		Map<String, Integer> returnMap = playerList.stream()
				.collect(Collectors.groupingBy(p -> p.getCountry().getCountryName(), Collectors.summingInt(Player :: getRuns)));
		return returnMap;
	}
	//- Return a map with key as country name and value as maximum of the runs scored by players in that country
	public Map<Object, Optional<Player>> getMaxScoreByCountry(){

		Map<Object, Optional<Player>> countryNameWithPlayer = playerList.stream()
				.collect(Collectors.groupingBy(p -> p.getCountry().getCountryName(), Collectors.maxBy(Comparator.comparing(Player :: getRuns))));

				return countryNameWithPlayer;
	}

	//- Return a map with key as country name and value as String of player Names separated by comma
	public Map<String, String> getPlayerNamesStringByCountry(){

		Map<String, String> countryNameWithPlayer = playerList.stream()
				.collect(Collectors.groupingBy(p -> p.getCountry().getCountryName(), Collectors.mapping(Player :: getPlayerName, Collectors.joining(", "))));

				return countryNameWithPlayer;
	}

	public static void main(String[] args) {

		StreamCollectorsExercise objStreamCollectorsExercise = new StreamCollectorsExercise();

		System.out.println("*****  Return a map with key as country name and value as List of players  *****");
		System.out.println(objStreamCollectorsExercise.getPlayersByCountry());

		System.out.println("\n*****  Return a map with key as country name and value as List of player Names who have played more than 100 matches  *****");
		System.out.println(objStreamCollectorsExercise.getPlayerNamesByCountry());

		System.out.println("\n*****  Return a LinkedHashMap with key as country name and value as number of players in each country *****");
		System.out.println(objStreamCollectorsExercise.getTotalPlayersByCountry());
		
		System.out.println("\n*****  Return a map with key as country name and value as sum total runs of all players  *****");
		System.out.println(objStreamCollectorsExercise.getTotalRunsByCountry());
		
		System.out.println("\n*****  Return a map with key as country name and value as maximum of the runs scored by players in that country  *****");
		System.out.println(objStreamCollectorsExercise.getMaxScoreByCountry());
		
		System.out.println("\n*****  Return a map with key as country name and value as String of player Names separated by comma  *****");
		System.out.println(objStreamCollectorsExercise.getPlayerNamesStringByCountry());

	}
}
