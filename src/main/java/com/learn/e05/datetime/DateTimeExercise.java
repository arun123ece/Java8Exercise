package com.learn.e05.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.learn.vo.Tablet;

public class DateTimeExercise {

	public DateTimeExercise() {

	}
	//- create a variable tablets of type ArrayList<Tablet> and initialize with some tablet objects 
	static List<Tablet> tabletList = new ArrayList<>();

	static {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		tabletList.add(new Tablet("Actavis", "Actavis", LocalDate.parse("10/12/2015", format), LocalDate.parse("25/08/2020", format)));
		tabletList.add(new Tablet("Syruph", "Actalion", LocalDate.parse("12/02/2014", format), LocalDate.parse("04/02/2018", format)));
		tabletList.add(new Tablet("Combiflam", "Almirall", LocalDate.parse("14/09/2015", format), LocalDate.parse("15/02/2020", format)));
		tabletList.add(new Tablet("Synthoroid", "Almirall", LocalDate.parse("11/04/2012", format), LocalDate.parse("05/05/2022", format)));
		tabletList.add(new Tablet("Systane", "Amgen", LocalDate.parse("09/05/2015", format), LocalDate.parse("16/04/2025", format)));
		tabletList.add(new Tablet("Synthoroid1", "Almirall", LocalDate.parse("11/04/2012", format), LocalDate.parse("16/04/2016", format)));
		tabletList.add(new Tablet("Synthoroid2", "Almirall", LocalDate.parse("11/04/2012", format), LocalDate.parse("16/04/2019", format)));
	}

	//- getExpiringTables(int months):List<String>
	//- method should take number of months as parameter and return a List of 
	//tablet names expiring within the given months from today.

	public List<String> getExpiringTables(int months){

		LocalDate todayDate = LocalDate.now().plusMonths(months);

		List<String> expiredMedicineList = tabletList.stream()
				.filter(p -> p.getExpiryDate().isBefore(todayDate) && p.getExpiryDate().isAfter(LocalDate.now()))
				.map(m -> m.getTabletName()).collect(Collectors.toList());
		return expiredMedicineList;
	}
	//-	getExpiringTabletsSorted
	//- return a  List of Tablets, in the ascending order of expiry date

	public  List<String> getExpiringTabletsSorted(){

		List<String> sortedExpiredList = tabletList.stream()
				.filter(p -> p.getExpiryDate().isBefore(LocalDate.now()))
				.map(m -> m.getTabletName())
				.sorted().collect(Collectors.toList());
		return sortedExpiredList;
	}
	//- getTabletExpiryPeriod
	//- return a Map with the tablet name as key and the period between 
	// the manufacture date and expiry date as value. 
	//  The period should be a string containing years, months and days 
	//(ex: 1 year/s 2 month/s 5 day/s , 3 month/s 5 day/s, 3 year/s ..)

	public  Map<String, String> getTabletExpiryPeriod(){

		return tabletList.stream().collect(Collectors.toMap(Tablet :: getTabletName, k -> getPeriod(k.getManufactureDate(), k.getExpiryDate())));
	}
	//- getSameYearExpiry
	//- Return a Map with key as manufacturer and value as list of tablet names 
	//which are manufactured in the current year and are already expired

	public  Map<String, List<String>> getSameYearExpiry(){

		Map<String, List<String>> mapSameYearExpiry = tabletList.stream()
				.filter(p -> p.getExpiryDate().isBefore(LocalDate.now()) && p.getExpiryDate().getYear() == LocalDate.now().getYear())
				.collect(Collectors.groupingBy(Tablet :: getManufacturer, Collectors.mapping(Tablet :: getTabletName, Collectors.toList())));

		return mapSameYearExpiry;
	}
	public static String getPeriod(LocalDate manufactureDate, LocalDate expiryDate) {

		Period duration = Period.between(manufactureDate, expiryDate);

		String years = duration.getYears() > 1 ? duration.getYears()+" years " : duration.getYears()+" year ";
		String months = duration.getMonths() > 1 ? duration.getMonths()+" month " : duration.getMonths()+" month ";
		String days = duration.getDays() > 1 ? duration.getMonths()+" days" : duration.getMonths()+" day";

		return years + months + days;
	}
	public static void main(String[] args) {

		DateTimeExercise objDateTimeExercise = new DateTimeExercise();
		
		System.out.println("***** List of Table Name which is expired in given Months *****");
		System.out.println(objDateTimeExercise.getExpiringTables(9));
		
		System.out.println("\n******* Sorted medicine which is expired ******");
		System.out.println(objDateTimeExercise.getExpiringTabletsSorted());

		System.out.println("\n***** Table Name and Period Between Manufacture and Expiry Date *****");
		System.out.println(objDateTimeExercise.getTabletExpiryPeriod());

		System.out.println("\n***** Map of Manufacture and List of Expired Medicine in Current Year *****");
		System.out.println(objDateTimeExercise.getSameYearExpiry());

	}
}
