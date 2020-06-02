package a01164474.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import a01164474.book.ApplicationException;
import a01164474.data.Book;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */
public class BookReader {
	
	public static final String BOOK_FILENAME = "books500.csv";
	private static final Logger lOG = LogManager.getLogger();
		
	public Map<Long, Book> read() throws ApplicationException {		

		File file = new File(BOOK_FILENAME);
		FileReader in;
		Iterable<CSVRecord> records;
		try {
			in = new FileReader(file);
			records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}

		Map<Long, Book> bookList = new HashMap<>();
		Book newBook;
		
		lOG.debug("Reading " + file.getAbsolutePath());
		
		for (CSVRecord record : records) {
			long id = Integer.parseInt(record.get("book_id"));
			String isbn = record.get("isbn");
			String authors = record.get("authors");
			String title = record.get("original_title");
			int publicationYear = Integer.parseInt(record.get("original_publication_year"));			
			double averageRating = Double.parseDouble(record.get("average_rating"));
			int ratingsCount = Integer.parseInt(record.get("ratings_count"));
			String imageURL = record.get("image_url");
				
			newBook = new Book.Builder(id, isbn)
						.setAuthors(authors)
						.setTitle(title)
						.setPublicationYear(publicationYear)				
						.setAverageRating(averageRating)
						.setRatingsCount(ratingsCount)
						.setImageURL(imageURL).build();
				
			bookList.put(Long.valueOf(newBook.getId()), newBook);
				
			}		
			return bookList;
		}	
}


