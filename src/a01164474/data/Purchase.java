package a01164474.data;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

public class Purchase {
	
	public static final int ATTRIBUTE_COUNT = 4;
	
	private long purchaseID;
	private long customerID;
	private long bookID;
	private double price;
	
	public static class Builder {
		//required parameters
		private long id;
		private double price;
		//optional parameters
		private long customerID;
		private long bookID;
		
		public Builder(long id, double price) {
			this.id = id;
			this.price = price;
		}
		
		public Builder setCustomerID(long customerID) {
			this.customerID = customerID;
			return this;
		}
		
		public Builder setBookID(long bookID) {
			this.bookID = bookID;
			return this;
		}
		
		public Purchase build() {
			return new Purchase(this);
		}
	}
	
	private Purchase(Builder builder) {
		purchaseID = builder.id;
		price = builder.price;
		customerID = builder.customerID;
		bookID = builder.bookID;
	}

	/**
	 * @return the id
	 */
	public long getPurchaseID() {
		return purchaseID;
	}

	/**
	 * @return the customerID
	 */
	public long getCustomerID() {
		return customerID;
	}

	/**
	 * @return the bookID
	 */
	public long getBookID() {
		return bookID;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param id the id to set
	 */
	public void setPurchaseID(long id) {
		this.purchaseID = id;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	/**
	 * @param bookID the bookID to set
	 */
	public void setBookID(long bookID) {
		this.bookID = bookID;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + purchaseID + ", customerID=" + customerID + ", bookID=" + bookID + ", price=" + price + "]";
	}

}
