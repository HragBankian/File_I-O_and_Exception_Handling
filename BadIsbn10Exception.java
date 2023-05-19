//-------------------------------------------------------------
//Assignment 3
//Exception class
//Written by: Hrag Bankian (40245363) and Gregory Demirdjian (40249882)
//-------------------------------------------------------------
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * bad isbn (10 digits) exception class
 */
public class BadIsbn10Exception extends Exception {
	/**
	 * 	exception logs the incorrect record
	 */
	public BadIsbn10Exception (String fileName,  String line) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileOutputStream("semantic_error_file.txt", true));
			pw.println("semantic error in file: " + fileName + "\n==================" + "\nError: invalid ISBN(10)\nRecord: " + line + "\n");
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		finally {
			pw.close();
		}
	}
}

