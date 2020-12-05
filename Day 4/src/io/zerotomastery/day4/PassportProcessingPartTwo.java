package io.zerotomastery.day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PassportProcessingPartTwo {
	static int counter = 0;
	static int validPassportCounter = 0;

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		ArrayList<String> listRecord = new ArrayList<>();
		PassportProcessingPartTwo puzzleOne = new PassportProcessingPartTwo();

		try {
			BufferedReader br = new BufferedReader(new FileReader("src\\io\\zerotomastery\\day4\\input.txt"));
			String line;
			while ((line = br.readLine()) != null) {

				if (line.isBlank()) {
					// System.out.println(listRecord);

					puzzleOne.scanLine(listRecord);
					listRecord = puzzleOne.removeListbyposition(listRecord.size(), listRecord);

					continue;
				} else {

					listRecord.add(line);

				}

			}

			// gets last record in the input file
			if ((line = br.readLine()) == null) {
				puzzleOne.scanLine(listRecord);
				listRecord = puzzleOne.removeListbyposition(listRecord.size(), listRecord);

			}

			br.close();

			System.out.println(" Number of Valid pasports (Part One) = " + counter);
			System.out.println("Number of Vaild Passport with Correct Data(Part two) = " + validPassportCounter);

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

// function 	
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

		if (passportFieldRecord.size() == (passportFieldKeys.size() + 1)) {
			counter++;

			scanPasportData(listRecord, passportFieldKeys);

		} else {

			for (int j = 0; j < 7; j++) {

				for (int i = 0; i < passportFieldRecord.size(); i++) {

					if ((passportFieldRecord.get(i)).equals(passportFieldKeys.get(j))) {

						recordCounter++;
					}
				}

			}

		}
		if (recordCounter == 7) {
			counter++;

			scanPasportData(listRecord, passportFieldKeys);

		}

		recordCounter = 0;
	}

	ArrayList<String> removeListbyposition(int size, ArrayList<String> listRecord) {

		for (int i = 0; i < size; i++) {

			listRecord.remove(0);

		}
		return listRecord;

	}

	void scanPasportData(ArrayList<String> listRecord, ArrayList<String> passportFieldKeys) {
		String eachLine = "";
		ArrayList<String> passportKeys = new ArrayList<>();
		ArrayList<String> passportData = new ArrayList<>();
		int counterValid = 0;
		// System.out.println("listsize = " + listRecord.size() );
		// System.out.println( listRecord);
		for (int i = 0; i < listRecord.size(); i++) {
			// System.out.println( listRecord.get(i));
			eachLine = eachLine.concat(listRecord.get(i));

			String[] arrOfStr = eachLine.split(" ", 10);

			for (String a : arrOfStr) {
				// System.out.println(a);
				String[] arrOfStr2 = a.split(":", 2);
				// System.out.println(arrOfStr2);
				for (String a1 : arrOfStr2) {

					if (counterValid == 1)
						passportData.add(a1);
					else
						passportKeys.add(a1);

					// System.out.println(a1);
					// System.out.println(counterValid);
					counterValid++;
				}
				counterValid = 0;

			}

			eachLine = "";

		}
		// System.out.println(passportKeys);
		// System.out.println(passportData);

		// Check the data for against each key
		ArrayList<String> flag = new ArrayList<>();
		
		for (int i = 0; i < passportKeys.size(); i++)
			for (int j = 0; j < passportFieldKeys.size(); j++) {
				if ((passportKeys.get(i)).equals(passportFieldKeys.get(j))) {

					if ((passportKeys.get(i).equals("byr"))) {
						int byr = Integer.parseInt(String.valueOf(passportData.get(i)));
						// System.out.println("BYR = " + byr);
						if ((byr >= 1920) && (byr <= 2002))
							flag.add("true");
						else
							flag.add("false");
					}
					if ((passportKeys.get(i).equals("iyr"))) {
						int iyr = Integer.parseInt(String.valueOf(passportData.get(i)));
						// System.out.println("IYR = " + iyr);
						if ((iyr >= 2010) && (iyr <= 2020))
							flag.add("true");
						else
							flag.add("false");
					}

					if ((passportKeys.get(i).equals("eyr"))) {
						int eyr = Integer.parseInt(String.valueOf(passportData.get(i)));
						// System.out.println("EYR = " + eyr);
						if ((eyr >= 2020) && (eyr <= 2030))
							flag.add("true");
						else
							flag.add("false");
					}

					if ((passportKeys.get(i).equals("hgt"))) {

						if (passportData.get(i).length() >= 4) {
							String heightMatrix = passportData.get(i).substring(passportData.get(i).length() - 2,
									passportData.get(i).length());
							String heightString = passportData.get(i).substring(0, passportData.get(i).length() - 2);
							// System.out.println("heightMatrix = " + heightMatrix);

							int height = Integer.parseInt(heightString);
							// System.out.println("height = " + height);

							// System.out.println("HGT = " + height + heightMatrix);
							if (heightMatrix.equals("cm")) {
								if (height >= 150 && height <= 193)
									flag.add("true");
								else
									flag.add("false");

							} else if (heightMatrix.equals("in")) {
								if (height >= 59 && height <= 76)
									flag.add("true");
								else
									flag.add("false");

							}
						} else
							flag.add("false");

					}

					if ((passportKeys.get(i).equals("ecl"))) {
						// System.out.println("ECL =" + passportData.get(i));
						switch (passportData.get(i)) {
						case "amb":
							flag.add("true");
							break;
						case "blu":
							flag.add("true");
							break;
						case "brn":
							flag.add("true");
							break;
						case "gry":
							flag.add("true");
							break;
						case "grn":
							flag.add("true");
							break;
						case "hzl":
							flag.add("true");
							break;
						case "oth":
							flag.add("true");
							break;
						default:
							flag.add("false");
						}

					}

					if ((passportKeys.get(i).equals("pid"))) {

						ArrayList<String> flagPid = new ArrayList<>();
						// System.out.println("PID = " + passportData.get(i));

						// System.out.println("Lenght of PID= " + (passportData.get(i)).length() );
						if (String.valueOf(passportData.get(i)).length() == 9) {

							for (int x = 0; x < passportData.get(i).length(); x++) {
								switch (passportData.get(i).charAt(x)) {

								case '0':
									flagPid.add("true");
									break;
								case '1':
									flagPid.add("true");
									break;
								case '2':
									flagPid.add("true");
									break;
								case '3':
									flagPid.add("true");
									break;
								case '4':
									flagPid.add("true");
									break;
								case '5':
									flagPid.add("true");
									break;
								case '6':
									flagPid.add("true");
									break;
								case '7':
									flagPid.add("true");
									break;
								case '8':
									flagPid.add("true");
									break;
								case '9':
									flagPid.add("true");
									break;
								default:
									flagPid.add("false");

								}
							}

							int countFlag2 = 0;
							for (int index = 0; index < flagPid.size(); index++) {
								if (flagPid.get(index).equals("true"))
									countFlag2++;
							}
							if (countFlag2 == 9) {
								long pid = Long.parseLong(String.valueOf(passportData.get(i)));
								// System.out.println("PID = " + pid);
								if ((pid >= 0) && (pid <= 999999999))
									flag.add("true");
								else
									flag.add("false");
							}
						} else
							flag.add("false");
					}

					ArrayList<String> flagHcl = new ArrayList<>();
					if ((passportKeys.get(i).equals("hcl"))) {

						if ((passportData.get(i).charAt(0) == '#') && passportData.get(i).length() == 7) {
							// System.out.println("HCL = " + passportData.get(i));
							for (int index = 1; index < passportData.get(i).length(); index++) {

								switch (passportData.get(i).charAt(index)) {
								case 'a':
									flagHcl.add("true");
									break;
								case 'b':
									flagHcl.add("true");
									break;
								case 'c':
									flagHcl.add("true");
									break;
								case 'd':
									flagHcl.add("true");
									break;
								case 'e':
									flagHcl.add("true");
									break;
								case 'f':
									flagHcl.add("true");
									break;
								case '0':
									flagHcl.add("true");
									break;
								case '1':
									flagHcl.add("true");
									break;
								case '2':
									flagHcl.add("true");
									break;
								case '3':
									flagHcl.add("true");
									break;
								case '4':
									flagHcl.add("true");
									break;
								case '5':
									flagHcl.add("true");
									break;
								case '6':
									flagHcl.add("true");
									break;
								case '7':
									flagHcl.add("true");
									break;
								case '8':
									flagHcl.add("true");
									break;
								case '9':
									flagHcl.add("true");
									break;
								default:
									flagHcl.add("false");

								}

							}

						} else
							flag.add("false");

						int countFlag1 = 0;
						for (int index = 0; index < flagHcl.size(); index++) {
							if (flagHcl.get(index).equals("true"))
								countFlag1++;
						}
						if (countFlag1 == 6)
							flag.add("true");

					}

				}

			}
		int countFlag = 0;
		for (int i = 0; i < flag.size(); i++) {
			if (flag.get(i).equals("true"))
				countFlag++;
		}
		if (countFlag == 7)
			validPassportCounter++;

		// System.out.println("Flag = " + flag);

		removeListbyposition(passportData.size(), passportData);
		removeListbyposition(passportKeys.size(), passportKeys);

	}

}
