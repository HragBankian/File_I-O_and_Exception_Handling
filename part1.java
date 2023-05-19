//-------------------------------------------------------------
//Assignment 3
//Part 1
//Written by: Hrag Bankian (40245363) and Gregory Demirdjian (40249882)
//-------------------------------------------------------------
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * part 1 class
 * @author hrag_gregory
 *
 */
public class part1 {
	/**
	 * total number of syntactically valid files
	 */
	private static int validCountSyntax;
	/**
	 * validCountSyntax accessor
	 * @return validCountSyntax
	 */
	public static int getValidCountSyntax() {
		return validCountSyntax;
	}
	/**
	 * execute do_part1 for input files
	 */
	public static void openInputFiles_part1() {
	Scanner sc = null;
	try {
		sc = new Scanner(new FileInputStream("part1_input_file_names.txt"));
		do_part1(sc);
	}
	catch (FileNotFoundException e) {
		System.out.println("File not found!");	
	}
	finally {
		sc.close();
	}
}
	/**
	 * validate syntax for each record
	 * @param sc
	 */
	public static void do_part1(Scanner sc) {
		final int i = Integer.parseInt(sc.nextLine()); //i=16
		PrintWriter pw = null;
		int countCCB = 0, countHCB = 0, countMTV = 0, countMRB = 0, countNEB = 0, countOTR = 0, countSSM = 0, countTPA = 0;
		String[] errorTypeArr = {"title", "authors", "price", "isbn", "genre", "year"};
		String[] filesArr = new String[i];
		String[] genreArr = {"CCB" , "HCB", "MTV", "MRB", "NEB", "OTR", "SSM", "TPA"};
		int[] countArr = {countCCB, countHCB, countMTV, countMRB, countNEB, countOTR, countSSM, countTPA};
		String[] genreFilesArr = {"Cartoons_Comics_Books.csv.txt", "Hobbies_Collectibles_Books.csv.txt", "Movies_TV.csv.txt", "Music_Radio_Books.csv.txt", "Nostalgia_Eclectic_Books.csv.txt", "Old_Time_Radio.csv.txt", "Sports_Sports_Memorabilia.csv.txt", "Trains_Planes_Automobiles.csv.txt"};
	
		//read file names
		while (sc.hasNextLine()) {
			for (int j = 0; j < i; j++) {
				filesArr[j] = sc.nextLine();
			}
		}
		
		//loop through each file
		for (int j = 0; j < i; j++) {
			try {
				sc = new Scanner(new FileInputStream(filesArr[j]));
			}
			catch (FileNotFoundException e) {
				System.out.println("File not found!");
			}
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				
				//case 1: title contains quotation marks
				if (line.charAt(0) == '\"') { //checking for quotation marks
					//line.substring(line.indexOf('\"'), line.indexOf('\"', (line.indexOf('\"')+1))+1); //Extract title from record
					String lineNoTitle = line.substring(line.indexOf('\"', (line.indexOf('\"')+1))+2); //Record without title
					String[] nbFieldsArr5 = lineNoTitle.split(","); //array of fields without title
					int missingIndex = -1;
					boolean missing = false;
					boolean error = false;
					
					//detecting syntax errors and their respective exceptions
					if (line.charAt(line.length()-1) == ',' && (nbFieldsArr5.length != 5)) {
						try {
							throw new MissingFieldException(filesArr[j], "year", line);
						}
						catch (MissingFieldException e){
						}
					}
					else if (line.charAt(line.length()-1) == ',' && (nbFieldsArr5.length == 5)) {
						try {
							throw new TooManyFieldsException(filesArr[j], line);
						}
						catch (TooManyFieldsException e) {
						}
					}
					else if (nbFieldsArr5.length < 5) {
						try {
							throw new TooFewFieldsException(filesArr[j], line);
						}
						catch (TooFewFieldsException e) {
						}
					}
					else if (nbFieldsArr5.length > 5) {
						try {
							throw new TooManyFieldsException(filesArr[j], line);
						}
						catch (TooManyFieldsException e) {
						}
					}
					
					//check missing field and its index
					else if (nbFieldsArr5.length == 5) {
						if (true){
							for (int y = 0; y < nbFieldsArr5.length; y++) {
								if (nbFieldsArr5[y] == "") {
									missingIndex = y;
									break;
								}
							}
							
							//throw corresponding exception based on missing field
							for (int y = 0; y < nbFieldsArr5.length; y++) {
								if (missingIndex == y) {
									try {
										error = true;
										throw new MissingFieldException(filesArr[j], errorTypeArr[y+1], line);
									}
									catch (MissingFieldException e) {
									}
								}
							}
						}

					//writing syntactically correct files into files that correspond to their genre	
					boolean genreFound = false;
						for (int y = 0; y < genreArr.length; y++) {
							if (nbFieldsArr5[3].equals(genreArr[y]) && error == false) {
									try {
										pw = new PrintWriter(new FileOutputStream(genreFilesArr[y], true));
										pw.println(line);
										pw.close();
										countArr[y]++;
										genreFound = true;	
									}
									catch (FileNotFoundException e) {
										System.out.println("File not found!");
									}
							}
						}
					//detecting invalid genres
					if (genreFound == false && error == false) {
						try {
							throw new UnknownGenreException(filesArr[j], line);
						}
						catch (UnknownGenreException e) {
						}
					}
				}
				}
				
				//case 2: title does not contain quotation marks, repeat verifications from case 1
				else if (line.charAt(0) != '\"') { //checking absence of quotation marks
					boolean error = false;
					String[] nbFieldsArr6 = line.split(","); //array of fields with title
					if (line.charAt(line.length()-1) == ',' && (nbFieldsArr6.length != 6)) {
						try {
							throw new MissingFieldException(filesArr[j], "year", line);
						}
						catch (MissingFieldException e){
						}
						continue;
					}
					else if (line.charAt(line.length()-1) == ',' && (nbFieldsArr6.length == 6)) {
						try {
							throw new TooManyFieldsException(filesArr[j], line);
						}
						catch (TooManyFieldsException e) {
						}
					}
					else if (nbFieldsArr6.length < 6) {
						try {
							throw new TooFewFieldsException(filesArr[j], line);
						}
						catch (TooFewFieldsException e) {
						}
					}
					else if (nbFieldsArr6.length > 6) {
						try {
							throw new TooManyFieldsException(filesArr[j], line);
						}
						catch (TooManyFieldsException e) {
						}
					}
					int missingIndex = -1;
					
					if (nbFieldsArr6.length == 6 ) {
						if (true) {
							for (int y = 0; y < nbFieldsArr6.length; y++) {
								if (nbFieldsArr6[y] == "") {
									missingIndex = y;
									break;
								}
							}
							for (int y = 0; y < nbFieldsArr6.length; y++) {
								if (missingIndex == y) {
									try {
										error = true;
										throw new MissingFieldException(filesArr[j], errorTypeArr[y], line);
									}
									catch (MissingFieldException e) {
									}
								}
							}
						}
						
					boolean genreFound = false;
					for (int y = 0; y < genreArr.length; y++) {
						if (nbFieldsArr6[4].equals(genreArr[y]) && error == false) {
							try {
								pw = new PrintWriter(new FileOutputStream(genreFilesArr[y], true));
								pw.println(line);
								pw.close();
								countArr[y]++;
								genreFound = true;	
							}
							catch (FileNotFoundException e) {
								System.out.println("File not found!");
							}
						}
					}
					if (genreFound == false && error == false) {
						try {
							throw new UnknownGenreException(filesArr[j], line);
						}
						catch (UnknownGenreException e) {
						}
					}
				}
				}
			}
			sc.close();
		}
		int count = 0;
		for (int k = 0; k < countArr.length; k++) {
			count += countArr[k];
		}
		validCountSyntax = count; //number of syntactically correct files
	}
}