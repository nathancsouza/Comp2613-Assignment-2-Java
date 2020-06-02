package a01164474.data;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

public class Book {

	public static final int ATTRIBUTE_COUNT = 8;
	
	private long id;
	private String isbn;
	private String authors;
	private String title;
	private int publicationYear;	
	private double averageRating;
	private int ratingsCount;
	private String imageURL;
	
	public static class Builder {
		//required parameters
		private long id;
		private String isbn;
		
		//optional parameters
		private String authors;
		private String title;
		private int publicationYear;		
		private double averageRating;
		private int ratingsCount;
		private String imageURL;
		
		public Builder(long id, String isbn ) {
			this.id = id;
			this.isbn = isbn;
		}
		
		public Builder setId(long id) {
			this.id = id;
			return this;
		}
		
		public Builder setIsbn(String isbn) {
			this.isbn = isbn;
			return this;
		}
		
		public Builder setAuthors(String authors) {
			this.authors = authors;
			return this;
		}
		
		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}
		
		public Builder setPublicationYear(int publicationYear) {
			this.publicationYear = publicationYear;
			return this;	
		}
				
		public Builder setAverageRating(double averageRating) {
			this.averageRating = averageRating;
			return this;
		}
		
		public Builder setRatingsCount(int ratingsCount) {
			this.ratingsCount = ratingsCount;
			return this;
		}
		
		public Builder setImageURL(String imageURL) {
			this.imageURL = imageURL;
			return this;
		}
		
		public Book build() {
			return new Book(this);
		}
	}
	
	private Book(Builder builder) {
		id = builder.id;
		isbn = builder.isbn;
		authors = builder.authors;
		title = builder.title;
		publicationYear = builder.publicationYear;
		averageRating = builder.averageRating;
		ratingsCount = builder.ratingsCount;
		imageURL = builder.imageURL;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the isbn
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @return the authors
	 */
	public String getAuthors() {
		return authors;
	}

	/**
	 * @return the publicationYear
	 */
	public int getPublicationYear() {
		return publicationYear;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the averageRating
	 */
	public double getAverageRating() {
		return averageRating;
	}

	/**
	 * @return the ratingsCount
	 */
	public int getRatingsCount() {
		return ratingsCount;
	}

	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param isbn the isbn to set
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	/**
	 * @param publicationYear the publicationYear to set
	 */
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param averageRating the averageRating to set
	 */
	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	/**
	 * @param ratingscount the ratingsCount to set
	 */
	public void setRatingsCount(int ratingsCount) {
		this.ratingsCount = ratingsCount;
	}

	/**
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", isbn=" + isbn + ", authors=" + authors + ", publicationYear=" + publicationYear
				+ ", title=" + title + ", averageRating=" + averageRating + ", ratingsCount=" + ratingsCount
				+ ", imageURL=" + imageURL + "]";
	}	
}
