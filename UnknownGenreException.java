//-------------------------------------------------------------
//Assignment 3
//Exception class
//Written by: Hrag Bankian (40245363) and Gregory Demirdjian (40249882)
//-------------------------------------------------------------
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * unknown genre exception class
 */
public class UnknownGenreException extends Exception {
	/**
	 * 	exception logs the incorrect record
	 */
	public UnknownGenreException(String fileName, String line) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream("syntax_error_file.txt", true));
			pw.println("syntax error in file: " + fileName + "\n==================" + "\nError: invalid genre " + "\nRecord: " + line + "\n");
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		finally {
			pw.close();
		}
	}
}
