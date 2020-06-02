package a01164474.data;

import a01164474.data.Book.Builder;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

@SuppressWarnings("unused")
public class Buyer {
	
	private String firstName;    
    private String lastName;    
    private String title;    
    private double price;
    private long customerID;
    
    /*public Buyer(String firstName, String lastName, String title, double price) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.price = price;        
      }*/
    
    public static class Builder {
    	private String firstName;
    	private String lastName;
    	private String title;
    	private double price;
    	private long customerID;
    	
    	public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}
    	
    	public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}
    	
    	public Builder setTitle(String title) {
			this.title = title;
			return this;
		}
    	
    	public Builder setPrice(double price) {
			this.price = price;
			return this;
		}
    	
    	public Builder setCustomerID(long customerID) {
			this.customerID = customerID;
			return this;
		}
    	
    	public Buyer build() {
    		return new Buyer(this);
    	}
    }
    
    private Buyer(Builder builder) {
		firstName = builder.firstName;
		lastName = builder.lastName;
		title = builder.title;
		price = builder.price;
		customerID = builder.customerID;
	}
    
	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Buyer [firstName=" + firstName + ", lastName=" + lastName + ", title=" + title + ", price=" + price
				+ ", customerID=" + customerID + "]";
	}
    
}
