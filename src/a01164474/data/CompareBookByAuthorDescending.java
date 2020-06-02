package a01164474.data;

import java.util.Comparator;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */
public class CompareBookByAuthorDescending implements Comparator<Book> {
	@Override
	public int compare(Book book1, Book book2) {
		return book2.getAuthors().compareToIgnoreCase(book1.getAuthors());
	}
}
