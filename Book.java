//-------------------------------------------------------------
//Assignment 3
//Book class
//Written by: Hrag Bankian (40245363) and Gregory Demirdjian (40249882)
//-------------------------------------------------------------
import java.io.Serializable;

/**
 * book class
 * @author hrag_gregory
 *
 */
public class Book implements Serializable {
	/**
	 * book attributes
	 */
	private String title;
	private String authors;
	private double price;
	private String isbn;
	private String genre;
	private int year;
	
	/**
	 * parameterized book constructor
	 * @param t
	 * @param a
	 * @param p
	 * @param i
	 * @param g
	 * @param y
	 */
	public Book(String t, String a, double p, String i, String g, int y) {
		title = t;
		authors = a;
		price = p;
		isbn = i;
		genre = g;
		year = y;
	}
	/**
	 * title accessor
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * title mutator
	 * @return
	 */
	public void setTitle(String t) {
		title = t;
	}
	/**
	 * author accessor
	 * @return
	 */
	public String getAuthors() {
		return authors;
	}
	/**
	 * author mutator
	 * @return
	 */
	public void setAuthors(String a) {
		authors = a;
	}
	/**
	 * price accessor
	 * @return
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * price mutator
	 * @return
	 */
	public void setPrice(double p) {
		price = p;
	}
	/**
	 * isbn accessor
	 * @return
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * isbn mutator
	 * @return
	 */
	public void setIsbn(String i) {
		isbn = i;
	}
	/**
	 * genre accessor
	 * @return
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * genre mutator
	 * @return
	 */
	public void setGenre(String g) {
		genre = g;
	}
	/**
	 * year accessor
	 * @return
	 */
	public int getYear() {
		return year;
	}
	/**
	 * year mutator
	 * @return
	 */
	public void setYear(int y) {
		year = y;
	}
	/**
	 * override toString method
	 */
	@Override
	public String toString() {
		return("title: " + title + "\nauthors: " + authors + "\nprice: " + price + "\nisbn: " + isbn + "\ngenre: " + genre + "\nyear: " + year + "\n");
	}
	/**
	 * override equals method
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Book) {
			return title.equals(((Book)o).title) && authors.equals(((Book)o).authors) && price == ((Book)o).price && isbn.equals(((Book)o).isbn) && genre.equals(((Book)o).title) && year == ((Book)o).year;
		}
		else return false;
	}
	
}
