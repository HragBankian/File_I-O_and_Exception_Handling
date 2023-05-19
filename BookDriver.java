//-------------------------------------------------------------
//Assignment 3
//Driver
//Written by: Hrag Bankian (40245363) and Gregory Demirdjian (40249882)
//-------------------------------------------------------------

//this program reads records from files, filters out records with syntax and semantic errors and writes them into their respective file
//creates objects out of the correct records, and then places those objects into an array to then write them in binary files categorized by genre
//reads from the binary files and creates an interactive menu for the user

/**
 * driver
 * @author hrag_gregory
 *
 */
public class BookDriver {
/**
 * call part 1, part 2 and part 3
 * @param args
 */
	public static void main(String[] args) {
		part1.openInputFiles_part1();
		part2.do_part2();
		part3.do_part3();
	}
}
