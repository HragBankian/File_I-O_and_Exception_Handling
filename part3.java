//-------------------------------------------------------------
//Assignment 3
//Part 3
//Written by: Hrag Bankian (40245363) and Gregory Demirdjian (40249882)
//-------------------------------------------------------------
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * part 3 class
 * @author hrag_gregory
 *
 */
public class part3 {
	/**
	 * part 3 interactive menu
	 */
	public static void do_part3() {
		String[] genre = {"Cartoons_Comics_Books.csv.ser", "Hobbies_Collectibles_Books.csv.ser", "Movies_TV.csv.ser", "Music_Radio_Books.csv.ser", "Nostalgia_Eclectic_Books.csv.ser", "Old_Time_Radio.csv.ser", "Sports_Sports_Memorabilia.csv.ser", "Trains_Planes_Automobiles.csv.ser"};
		Book[][] book2D = new Book[genre.length][];
		ObjectInputStream ois = null;
		String choice1 = "";
		int choice2 = 1;
		String currentFile = genre[0];
		Scanner input = new Scanner(System.in);
		int i = 0;
		int n = 0;
		int currentIndex = 0;
		int tempChoice2 = 1;
		
		//read array of books from binary files
		for (i = 0; i < genre.length; i++) {
			try {
				ois = new ObjectInputStream(new FileInputStream(genre[i]));
				book2D[i] = (Book[]) ois.readObject();
				ois.close();
			}
			catch (FileNotFoundException e) {
				System.out.println("File not found!");
			}
			catch (ClassNotFoundException e1) {
				System.out.println("Class not found!");
			}
			catch (IOException e2) {
				System.out.println("Problem reading in the file");
			}
		}
		
		//display main menu
		while (true) {
			System.out.println("-----------------------------");
			System.out.println("\tMain Menu");
			System.out.println("-----------------------------");
			System.out.println(" v  View the selected file: " + currentFile + " (" + book2D[choice2-1].length + " records)");
			System.out.println(" s  Select a file to view");
			System.out.println(" x  Exit");
			System.out.println("-----------------------------");
			System.out.println("\nSelect an option: ");

			choice1 = input.next().toLowerCase(); //user input for menu
			
			//user input v (view records)
			if (choice1.equals("v")) {
				System.out.println("viewing: " + currentFile + " (" + book2D[choice2-1].length + " records)");
				boolean validInput1 = false;
				//validate input n
				while (validInput1 == false) {
					try {
							System.out.println("Please enter an integer value n: ");
							n = input.nextInt();
							validInput1 = true;
					}
					catch (InputMismatchException e) {
						System.out.println("Invalid choice! Please enter a valid integer value: ");
						input = new Scanner(System.in);
					}
				}
				boolean firstIteration = true;
				//loop prompt until 0 is entered
 				while (n != 0) {
					if (firstIteration == false) {
						validInput1 = false;
						while (validInput1 == false) {
							try {
									System.out.println("Please enter an integer value n: ");
									n = input.nextInt();
									validInput1 = true;
							}
							catch (InputMismatchException e) {
								System.out.println("Invalid choice! Please enter a valid integer value: ");
								input = new Scanner(System.in);
							}
						}
					}
					//case 1: algorithm for positive n
					if (n > 0) {
						for (int j = currentIndex; j < (currentIndex+n); j++) {
							System.out.println(book2D[choice2-1][j]);
							if (j == (book2D[choice2-1].length-1)) {
								System.out.println("EOF has been reached");
								currentIndex = (book2D[choice2-1].length-1);
								break;
							}
							if (j == (currentIndex+n-1)) {
								currentIndex += n-1;
								break;
							}
						}
						firstIteration = false;
					}
					//case 2: algorithm for negative n
					else if (n < 0) {
						for (int j = currentIndex; j > (currentIndex+(n)); j--) {
							if (j >= 0 ) {
								System.out.println(book2D[choice2-1][j]);
							}
							if (j < 0) {
								System.out.println("BOF has been reached");
								currentIndex = 0;
								break;
							}
							if (j == (currentIndex+n+1)) {
								currentIndex += n+1;
								break;
							}
						}
						firstIteration = false;
					}
				}
			}
			//input is s (select a file to view)
			else if (choice1.equals("s")) {
				boolean validInput = false;
				subMenu(book2D); //display subMenu
				//loop until valid input
				while (validInput == false) {
					try {
							choice2 = input.nextInt();
							if (choice2 < 0 || choice2 > 9) {
								throw new InputMismatchException();
							}
							validInput = true;
					}
					catch (InputMismatchException e) {
						System.out.println("Invalid choice! Please enter a valid integer value: ");
						input = new Scanner(System.in);
					}
				}
					//select file and save selection
					if (choice2 != 9) {
						currentFile = genre[choice2-1];
						tempChoice2 = choice2;
					}
					else if (choice2 == 9) {
						choice2 = tempChoice2;
					}
			}
			//input is x, terminate
			else if(choice1.equals("x")) {
				System.out.println("The program will now be terminated!");
				input.close();
				System.exit(0);
			}
			//invalid string
			else
				System.out.println("Invalid! Please reenter your choice");
		}
	}
	
	/**
	 * display submenu
	 * @param book2D
	 */
	public static void subMenu(Book book2D[][]) {
		System.out.println("------------------------------");
		System.out.println("\tFile Sub-Menu");
		System.out.println("------------------------------");
		System.out.println(" 1  Cartoons_Comics_Books.csv.ser" + " (" + book2D[0].length + " records)");
		System.out.println(" 2  Hobbies_Collectibles_Books.csv.ser"+ " (" + book2D[1].length + " records)");
		System.out.println(" 3  Movies_TV.csv.ser"+ " (" + book2D[2].length + " records)");
		System.out.println(" 4  Music_Radio_Books.csv.ser"+ " (" + book2D[3].length + " records)");
		System.out.println(" 5  Nostalgia_Eclectic_Books.csv.ser"+ " (" + book2D[4].length + " records)");
		System.out.println(" 6  Old_Time_Radio.csv.ser"+ " (" + book2D[5].length + " records)");
		System.out.println(" 7  Sports_Sports_Memorabilia.csv.ser"+ " (" + book2D[6].length + " records)");
		System.out.println(" 8  Trains_Planes_Automobiles.csv.ser"+ " (" + book2D[7].length + " records)");
		System.out.println(" 9  Exit");
		System.out.println("------------------------------");
		System.out.print("\nSelect a file: ");
	}
}