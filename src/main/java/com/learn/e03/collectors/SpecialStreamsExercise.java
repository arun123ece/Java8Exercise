package com.learn.e03.collectors;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
### Special Streams
---------------------
- Create a class SpecialStreamExercise with the following methods

 	- getSquaresOfThree()
    	Use IntStream to generate a range of numbers from 20 to 50 inclusive. From the stream, store the square of odd multiples of 3 in an LinkedList and return it

 	- getMultiplesOfFive()
		- Generate a infinite stream with multiples of 5 and collect the first 20 multiples in an array and return the array

 */

public class SpecialStreamsExercise {

	public SpecialStreamsExercise() {

	}
	public static LinkedList<Integer> getSquaresOfThree(){


		IntStream intStream = IntStream.rangeClosed(20, 50)
				.filter(p -> p % 3 == 0 &&  p % 2 != 0).map(m -> m*m);
		return intStream.boxed().collect(Collectors.toCollection(LinkedList :: new));

	}
	public static int[] getMultiplesOfFive() {

		return IntStream.iterate(5, i -> i + 5).limit(20).toArray();
	}
	public static void main(String[] args) {

		System.out.println("**** generate a range of numbers from 20 to 50 inclusive. From the stream, store the square of odd multiples of 3 in an LinkedList and return it");
		System.out.println(getSquaresOfThree());

		System.out.println("***** Generate a infinite stream with multiples of 5 and collect the first 20 multiples in an array and return the array");
		Arrays.stream(getMultiplesOfFive()).forEach(System.out :: println);
	}
}
