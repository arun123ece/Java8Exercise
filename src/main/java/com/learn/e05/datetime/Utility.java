package com.learn.e05.datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Utility {

	
	
	public Utility() {
		
	}
	/*- getBusSchedule(String start, Duration frequency) : List<String>
	- Method should return a List of String containing the bus timings for a day given the start time and duration as parameters.
	- The timing in the list should be in 24 hour format hh:mm*/
	public static List<String> getBusSchedule(String start, Duration frequency){
		
		LocalTime startTime = LocalTime.parse(start);
        LocalTime endTime = LocalTime.of(18, 00);
        
        List<String> frequncyList = new ArrayList<>();
    
        for(LocalTime time = startTime; time.isBefore(endTime); time = time.plusMinutes(frequency.toMinutes())) {
            frequncyList.add(time.format(DateTimeFormatter.ofPattern("hh:mm")).toString());
        }
        return frequncyList;
    }
    
    public static void main(String[] args) {
    
        new Utility().getBusSchedule(LocalTime.of(9, 00).toString(), Duration.ofMinutes(30)).forEach(System.out :: println);;
	}
}
