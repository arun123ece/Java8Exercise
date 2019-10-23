package com.learn.e05.datetime;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class BankDepositExercise {

	public BankDepositExercise() {

	}
	public static String getMaturityDate(final String investmentDate, final Period duration) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd-MM-yyyy][dd/MM/yyyy]");
		LocalDate investedDate = LocalDate.parse(investmentDate, formatter);

		LocalDate maturityDate = investedDate.plus(duration);

		return maturityDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
	}

	//- getInvestmentPeriod(String investmentDate, String maturityDate)
	//	- Method should take investment date and maturity dates as input and 
	// return the duration as a string in the format <x years, y months>
	public static String getInvestmentPeriod(String investmentDate, String maturityDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd-MM-yyyy][dd/MM/yyyy]");
		LocalDate investedDate = LocalDate.parse(investmentDate, formatter);
		LocalDate mDate = LocalDate.parse(maturityDate, formatter);

		Period duration = Period.between(investedDate, mDate);

		return duration.getYears()+" Years "+duration.getMonths()+ " Months";
	}
	public static void main(String[] args) {

		//Adding period of Time

		String maturityDate = getMaturityDate("10-10-2019", Period.of(5, 2, 15));
		System.out.println("Maturity Date :: "+maturityDate);

		String investmentPeriod = getInvestmentPeriod("10-10-2019", "11-11-2022");
		System.out.println("Investment Period :: "+investmentPeriod);

	}
}
