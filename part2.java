//-------------------------------------------------------------
//Assignment 3
//Part 2
//Written by: Hrag Bankian (40245363) and Gregory Demirdjian (40249882)
//-------------------------------------------------------------
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * part 2 binary files
 * @author hrag_gregory
 *
 */
public class part2 {
	/**
	 * check for semantic errors and write valid records to binary files based on their genre
	 */
	public static void do_part2() {
		String[] genreFilesArr = {"Cartoons_Comics_Books.csv.txt", "Hobbies_Collectibles_Books.csv.txt", "Movies_TV.csv.txt", "Music_Radio_Books.csv.txt", "Nostalgia_Eclectic_Books.csv.txt", "Old_Time_Radio.csv.txt", "Sports_Sports_Memorabilia.csv.txt", "Trains_Planes_Automobiles.csv.txt"};
		String[] genreOutArr = {"Cartoons_Comics_Books.csv.ser", "Hobbies_Collectibles_Books.csv.ser", "Movies_TV.csv.ser", "Music_Radio_Books.csv.ser", "Nostalgia_Eclectic_Books.csv.ser", "Old_Time_Radio.csv.ser", "Sports_Sports_Memorabilia.csv.ser", "Trains_Planes_Automobiles.csv.ser"};
		Scanner sc = null;
		//loop through each genre file
		for (int i = 0; i < genreFilesArr.length; i++) {
			ObjectOutputStream oos = null;
			Book[] bookArrTemp = new Book[0];
			Book[] bookArr = new Book[0];
			try {
				sc = new Scanner(new FileInputStream(genreFilesArr[i]));
				oos = new ObjectOutputStream(new FileOutputStream(genreOutArr[i]));
			}
			catch (FileNotFoundException e) {
				System.out.println("File not found!");
			}	
			catch (IOException e1) {
				System.out.println("Problem writing in the file!");
			}
			//process each record
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] fieldsArr = line.split(",");
				boolean error = false;
				String title = "";
				String authors = "";
				double price = 0;
				String isbnStr = "";
				int isbnLength = 0;
				int isbnInt = 0;
				String genre = "";
				int year = 0;
				
				//check for semantic errors
				try {
					year = Integer.parseInt(fieldsArr[fieldsArr.length-1]);
					if (year < 1995 || year > 2010) {
						throw new NumberFormatException();
					}
				}
				catch (NumberFormatException e) {
					error = true;
					try {
						throw new BadYearException(genreFilesArr[i], line); 
					}
					catch (BadYearException e1) {
					}
				}
				//if no year error, check for isbn 10 or isbn 13 error
				if (error == false) {
					try {
						isbnStr = (fieldsArr[fieldsArr.length-3]);
						isbnLength = isbnStr.length();
						isbnInt = Integer.parseInt(isbnStr);
						if (isbnLength == 10) {
								try {
									validIsbn10(isbnStr, genreFilesArr[i], line);
								}
								catch (BadIsbn10Exception e) {
								}
						}
						else if (isbnLength == 13) {
							try {
								validIsbn13(isbnStr, genreFilesArr[i], line);
							}
							catch (BadIsbn13Exception e) {
							}
						}
					}
					catch (NumberFormatException e) {
						error = true;
						if (isbnLength == 10) {
							try {
								throw new BadIsbn10Exception(genreFilesArr[i], line);
							}
							catch (BadIsbn10Exception e1) {
							}
						}
						if (isbnLength == 13) {
							try {
								throw new BadIsbn13Exception(genreFilesArr[i], line);
							}
							catch (BadIsbn13Exception e2) {
							}
						}
					}
				}
				//if no year or isbn error, check for price error
				if (error == false) {
					try {
						price = Double.parseDouble(fieldsArr[fieldsArr.length-4]);
						if (price < 0) {
							throw new NumberFormatException();
						}
					}
					catch (NumberFormatException e) {
						try {
							throw new BadPriceException(genreFilesArr[i], line);
						}
						catch (BadPriceException e1) {
							error = true;
						}
					}
				}
				//if no errors, create Book objects of valid records and store them in an array
				if (error == false) {
					if (line.charAt(0) == '\"') {
						title = line.substring(line.indexOf('\"'), line.indexOf('\"', (line.indexOf('\"')+1))+1);
					}
					else {
						title = line.substring(0, line.indexOf(','));
					}
					authors = fieldsArr[fieldsArr.length-5];
					genre = fieldsArr[fieldsArr.length-2];
					Book book = new Book(title, authors, price, isbnStr, genre, year);
					bookArr = Arrays.copyOf(bookArrTemp, bookArrTemp.length+1);
					bookArr[bookArr.length-1] = book;
					bookArrTemp = bookArr;
				}	
			}
			//write array to binary file
			try {
				oos.writeObject(bookArr);
				oos.close();
			}
			catch (IOException e) {
				System.out.println("Problem serializing array of book objects into binary file!");
			}
		}
	}
	/**
	 * method for validating 10 digit isbn
	 * @param isbn10
	 * @param fileName
	 * @param line
	 * @throws BadIsbn10Exception
	 */
	public static void validIsbn10(String isbn10, String fileName, String line) throws BadIsbn10Exception {
		int sum = 0;
		String[] digitsArr = isbn10.split("");
		for (int k = 0; k < digitsArr.length; k++) {
			sum += Integer.parseInt(digitsArr[k])*(isbn10.length()-k);
		}
		if (sum % 11 != 0) {
				throw new BadIsbn10Exception(fileName, line);
		}
	}
	/**
	 * method for validating 13 digit isbn
	 * @param isbn13
	 * @param fileName
	 * @param line
	 * @throws BadIsbn13Exception
	 */
	public static void validIsbn13(String isbn13, String fileName, String line) throws BadIsbn13Exception {
		int sum = 0;
		String[] digitsArr = isbn13.split("");
		for (int k = 0; k < digitsArr.length; k++) {
			if (k % 2 == 0) {
				sum += Integer.parseInt(digitsArr[k]);
			}
			if (k % 2 != 0) {
				sum += Integer.parseInt(digitsArr[k])*3;
			}
		}
		if (sum % 10 != 0) {
			throw new BadIsbn13Exception(fileName, line);
		}
	}
}