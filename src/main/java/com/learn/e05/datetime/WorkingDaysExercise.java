package com.learn.e05.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkingDaysExercise {

	public WorkingDaysExercise() {
		
	}
	//- getNextMonthsWorkingDays():List<String>
	//- Method should return a List of String containing the working days for the next month given todays date.
	//- Saturday and Sunday should be considered non working days
	//- The date returned should be in format dd-mm-yyyy
	
	public static List<String> getNextMonthsWorkingDays(){
		
		LocalDate today = LocalDate.now();
        LocalDate nextMonthFirstDay = today.with(TemporalAdjusters.firstDayOfNextMonth());
        LocalDate nextMonthLastDate = nextMonthFirstDay.plusMonths(1);
        //int noOfDays = (int) ChronoUnit.DAYS.between(nextMonthFirstDay, nextMonthLastDate);
        
       /* List<String> dates = new ArrayList<String>(noOfDays);
        
        Set<DayOfWeek> weekend =  EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY) ;
        while( nextMonthFirstDay.isBefore(nextMonthLastDate) ) {
            if( ! weekend.contains( nextMonthFirstDay.getDayOfWeek() ) ) { 
                dates.add(nextMonthFirstDay.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString()) ;
            }
            nextMonthFirstDay = nextMonthFirstDay.plusDays(1);
        }
        return dates;*/
        List<String> dates = Stream.iterate(nextMonthFirstDay, (d) -> d.plusDays(1))
                .filter(i -> !(i.getDayOfWeek() == DayOfWeek.SATURDAY || i.getDayOfWeek() == DayOfWeek.SUNDAY))
                .limit(ChronoUnit.DAYS.between(nextMonthFirstDay, nextMonthLastDate))
                .filter(i -> i.isBefore(nextMonthLastDate))
                .map(i -> i.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).toString())
               .collect(Collectors.toList());
       
       return dates;
	}
	public static void main(String[] args) {
		
		System.out.println("=====No of working days excluding Satuday and Sunday ======");
        List<String> result = getNextMonthsWorkingDays();
        result.forEach(System.out :: println);
	}
}
