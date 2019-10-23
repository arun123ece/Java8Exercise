package com.learn.e04.nio2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.learn.vo.Tablet;

/*### Files - NIO2
The input to the program is a text file, containing tablet(medical) details in each line. Table details include tablet name, manufacturer, manufactured date and expiry date(format dd/mm/yyyy) separated by comma in each line. 

- Create a class Tablet with attributes,tablet name, manufacturer, manufacture date and expiry date.

- Create a class NIO2Exercise with a static method getExpiredTablets(String filename, String manufacturer). 
   - This method should return a Map with key as Tablet name and value as expiry date of tablets for those tablets, which are already expired and 
     are from a given manufacturer.
   - create a method to list all the files ending with .java in the current project's src folder and its subfolders
   - create a method which takes two String parameters. The first parameter is the filename to be searched and the second parameter is the	
     absolute path of the directory to be searched.*/

public class NIO2Exercise {


	public static Map<String, LocalDate> getExpiredTablets(String filename, String manufacturer){

		Path filePath = Paths.get("resources",filename);
		LocalDate today = LocalDate.now();

		try(Stream<String> fileData = Files.newBufferedReader(filePath).lines()){	

			Map<String, LocalDate> tabletMap = fileData.map(NIO2Exercise :: parseTablet)
					.filter(p -> manufacturer.equalsIgnoreCase(p.getManufacturer()))
					.filter(p -> p.getExpiryDate().isBefore(today))
					.collect(Collectors.toMap(Tablet :: getTabletName, Tablet :: getExpiryDate));

			return tabletMap;
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<String> getAllJavaFileName(){

		try (Stream<Path> walk = Files.walk(Paths.get("src"))) {

			List<String> result = walk
					//.filter(Files::isRegularFile)
					.filter(p -> p.toString().endsWith(".java"))
					.map(x -> x.toString().substring(x.toString().lastIndexOf("/")+1, x.toString().length()))
					.collect(Collectors.toList());

			return result;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	/*- create a method which takes two String parameters. The first parameter is the filename to be searched and the second parameter is the	
    absolute path of the directory to be searched.*/
	
	public static boolean searchFileName(final String fileName, final String absolutePath) {
		
		Path path = Paths.get(absolutePath);
		
		try {
			return Files.walk(path).filter(Files :: isRegularFile)
			.filter(p -> p.getFileName().toString().equals(fileName)).findFirst().isPresent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	public static void main(String[] args) {

		System.out.println(getExpiredTablets("medicines.txt", "Almirall"));

		System.out.println("***** List of all file *****");
		List<String> result = getAllJavaFileName();
		result.forEach(System.out :: println);
		
		System.out.println("File exits :: "+searchFileName("medicines.txt", "/home/ubuntu/Desktop/WorkSpace/java8-exercises-boilerplate"));
		
	}
	public static Tablet parseTablet(String line)  {

		String[] tabletDetail = line.split(",");
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		return new Tablet(tabletDetail[0], tabletDetail[1], LocalDate.parse(tabletDetail[2], format), LocalDate.parse(tabletDetail[3], format));
	}
}
