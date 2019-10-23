package com.learn.e01.lamdas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class LambdaExercise {

	private static List<String> countries = new ArrayList<>();
	private static Map<String, String> countryCapitals = new HashMap<>();

	static {

		countries.add("India");
		countries.add("England");
		countries.add("Austrailia");
		countries.add("Shrilanka");
		countries.add("Bangladesh");
		countries.add("Pakistan");
		countries.add("Mangolia");

		countryCapitals.put("India", "New Delhi");
		countryCapitals.put("England", "London");
		countryCapitals.put("Austrailia", "Canberra");
		countryCapitals.put("Shrilanka", "Colombo");
		countryCapitals.put("Bangladesh", "Dhaka");
	}

	public static void displayCountries() {

		countries.forEach(System.out :: println);
	}
	public static void displayCountryCapitals() {

		countryCapitals.forEach((key, value) -> System.out.println("Country :: "+key+"\t"+"Capital :: "+value));
	}
	public static void sortCountriesByName() {

		countries.sort((obj1, obj2) -> obj2.compareToIgnoreCase(obj1));
		countries.forEach(System.out :: println);
	}
	public static void sortCountriesBylength() {

		countries.sort(LambdaExercise :: sortCountryByNameAndLength);
		countries.forEach(System.out :: println);
	}
	public static void removeCountry() {

		//	countries.stream().filter(p -> p.length() > 6).forEach(System.out :: println);;
		Predicate<String> predicate = p -> p.length() > 6;
		countries.removeIf(predicate);
		countries.forEach(System.out :: println);

	}
	public static int sortCountryByNameAndLength(String obj1, String obj2) {

		if(obj1.length() - obj2.length() == 0)
			return obj1.compareTo(obj2);
		else
			return obj1.length() - obj2.length();

	}
	public static void main(String[] args) {

		System.out.println("=========== Displaying All Country Name ==============");
		displayCountries();
		System.out.println("=========== Displaying All Country Name And Capitals ==============");
		displayCountryCapitals();
		System.out.println("=========== Sort(Decending) Country by Name  ==============");
		sortCountriesByName();
		System.out.println("=========== Sort(Decending) Country by Name and Length ==============");
		sortCountriesBylength();
		System.out.println("=========== Remove Country whose Name is grater than 6 character  ==============");
		removeCountry();

	}

}
