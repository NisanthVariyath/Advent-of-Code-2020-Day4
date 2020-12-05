package io.zerotomastery.day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PassportProcessingPartOne {
	static int counter = 0;

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		ArrayList<String> listRecord = new ArrayList<>();
		PassportProcessingPartOne puzzleOne = new PassportProcessingPartOne();

		try {
			BufferedReader br = new BufferedReader(new FileReader("src\\io\\zerotomastery\\day4\\input.txt"));
			String line;
			while ((line = br.readLine()) != null) {

				if (line.isBlank()) {
					

					puzzleOne.scanLine(listRecord);
					listRecord = puzzleOne.removeListbyposition(listRecord.size(), listRecord);

					continue;
				} else {
					

					listRecord.add(line);
					

				}

			}
			
			//  gets last record in the input file
			if((line = br.readLine()) == null) {
				puzzleOne.scanLine(listRecord);
				listRecord = puzzleOne.removeListbyposition(listRecord.size(), listRecord);

			}
			
			br.close();

			System.out.println(" Number of Valid pasports = " + counter);

			// calculate execution time
			long endTime = System.nanoTime();
			long duration = (endTime - startTime);
			System.out.println();
			System.out.println("Execution time in nanoseconds  : " + duration);
			System.out.println("Execution time in milliseconds : " + duration / 1000000);

		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException");
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	void scanLine(ArrayList<String> listRecord) {
		String eachLine = "";
		String passportField = "";
		int recordCounter = 0;
		ArrayList<String> passportFieldKeys = new ArrayList<>();

		passportFieldKeys.add("byr");

		passportFieldKeys.add("iyr");

		passportFieldKeys.add("eyr");

		passportFieldKeys.add("hgt");

		passportFieldKeys.add("hcl");

		passportFieldKeys.add("ecl");

		passportFieldKeys.add("pid");

		ArrayList<String> passportFieldRecord = new ArrayList<>();

		for (int i = 0; i < listRecord.size(); i++) {

			eachLine = eachLine.concat(listRecord.get(i));

			// add first 3 char from each line
			passportField = passportField.concat(Character.toString(eachLine.charAt(0)));
			passportField = passportField.concat(Character.toString(eachLine.charAt(1)));
			passportField = passportField.concat(Character.toString(eachLine.charAt(2)));
			passportFieldRecord.add(passportField);

			passportField = "";
			
            // add first 3 char from each line after a space
			for (int j = 3; j < eachLine.length(); j++) {

				if (String.valueOf(eachLine.charAt(j)).equals(" ")) {

					passportField = passportField.concat(Character.toString(eachLine.charAt(j + 1)));
					passportField = passportField.concat(Character.toString(eachLine.charAt(j + 2)));
					passportField = passportField.concat(Character.toString(eachLine.charAt(j + 3)));
					passportFieldRecord.add(passportField);

					passportField = "";

				}

			}
			eachLine = "";

		}

		// find password keys in a record and find total number

		if (passportFieldRecord.size() == (passportFieldKeys.size() + 1))
			counter++;
		else {

			for (int j = 0; j < 7; j++) {

				for (int i = 0; i < passportFieldRecord.size(); i++) {

					if ((passportFieldRecord.get(i)).equals(passportFieldKeys.get(j))) {

						recordCounter++;
					}
				}

			}

		}
		if (recordCounter == 7)
			counter++;

		recordCounter = 0;
	}

	ArrayList<String> removeListbyposition(int size, ArrayList<String> listRecord) {

		for (int i = 0; i < size; i++) {

			listRecord.remove(0);

		}
		return listRecord;

	}

}
