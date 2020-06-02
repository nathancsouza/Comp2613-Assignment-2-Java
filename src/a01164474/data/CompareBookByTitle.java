package a01164474.data;

import java.util.Comparator;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */
public class CompareBookByTitle implements Comparator<Book> {
	@Override
	public int compare(Book book1, Book book2) {
		return book1.getTitle().compareToIgnoreCase(book2.getTitle());
	}
}
