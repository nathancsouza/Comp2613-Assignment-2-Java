package a01164474.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import a01164474.book.ApplicationException;
import a01164474.io.BookReader;
import a01164474.io.CustomerReader;
import a01164474.io.PurchaseReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */
public class CustomerData {
	private static final Logger lOG = LogManager.getLogger();
	private static Map<Long, Book> listOfBook;
	private static Map<Long, Customer> listOfCustomer;
	private static Map<Long, Purchase> listOfPurchase;
	
	public static void retrieveData() throws IOException, ApplicationException{
		lOG.debug("Starting data loading...");			
		//customer			
		listOfCustomer = CustomerReader.read();
		lOG.debug("Customer has been loaded");
		//book
		BookReader readBookFile = new BookReader();			
		listOfBook = readBookFile.read();
		lOG.debug("Book has been loaded");
		//purchase
		PurchaseReader readPurchaseFile = new PurchaseReader();			
		listOfPurchase = readPurchaseFile.read();
		lOG.debug("Purchase has been loaded");
		lOG.debug("All data has been loaded successfully!");
	}		  
	  
	  public static Map<Long, Customer> getCustomers() {
	    return listOfCustomer;
	  }	  
	  public static Map<Long, Book> getBooks() {
	    return listOfBook;
	  }	  
	  public static Map<Long, Purchase> getPurchases() {
	    return listOfPurchase;
	  }	
	  
	  public static List<Customer> getListOfCustomer(){
		  Collection<Customer> listOfCustomer = getCustomers().values();
		  List<Customer> newListCustomers = new ArrayList<>(listOfCustomer);
		  return newListCustomers;
	  }
	  
	  public static List<Book> getListOfBooks(){
		  Collection<Book> listOfBook = getBooks().values();
		  List<Book> newListBooks = new ArrayList<>(listOfBook);
		  return newListBooks;
	  }
	  
	  public static List<Purchase> getListOfPurchases(){
		  Collection<Purchase> listOfPurchase = getPurchases().values();
		  List<Purchase> newListPurchase = new ArrayList<>(listOfPurchase);
		  return newListPurchase;
	  }
	  
	  public static List<Buyer> getListOfBuyers() {
		  List<Buyer> buyers = new ArrayList<>();
		  	Collection<Purchase> listOfPurchase = CustomerData.getPurchases().values();
			Map<Long, Customer> customers = CustomerData.getCustomers();
			Map<Long, Book> books = CustomerData.getBooks();		  	
		  	
			for (Purchase purchase : listOfPurchase) {
				long customerID = purchase.getCustomerID();
				long bookID = purchase.getBookID();
				Book book = books.get(Long.valueOf(bookID));
			    Customer customer = customers.get(Long.valueOf(customerID));
			    double price = purchase.getPrice();	
			    
			    buyers.add(new Buyer.Builder()
						.setFirstName(customer.getFirstName())
						.setLastName(customer.getLastName())
						.setTitle(book.getTitle())
						.setPrice(price)
						.setCustomerID(customerID)
						.build());	    		    
			}
			return buyers;
	   }
}
