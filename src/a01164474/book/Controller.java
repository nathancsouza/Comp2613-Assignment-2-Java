package a01164474.book;

import java.util.Collection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import a01164474.data.Book;
import a01164474.data.Buyer;
import a01164474.data.Customer;
import a01164474.data.CustomerData;
import a01164474.data.Purchase;
import a01164474.database.BookDao;
import a01164474.database.BuyerDao;
import a01164474.database.CustomerDao;
import a01164474.database.Database;
import a01164474.database.PurchaseDao;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

public class Controller {
	
	private static final Logger lOG = LogManager.getLogger();
	
	public static void initCustomer() {
		
		try {
			Collection<Customer> listOfCustomer = CustomerData.getCustomers().values();
			CustomerDao customerDao = CustomerDao.getInstance();
			Database.getInstance();
			
			lOG.debug("customerList: " + listOfCustomer.size());
			lOG.debug("initCustomer()");
			if (!Database.tableExists(CustomerDao.TABLE_NAME)) {
				customerDao.create();

				for (Customer i : listOfCustomer) {
					customerDao.add(i);
				}
				lOG.debug("List of Customers was added to " + CustomerDao.TABLE_NAME);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lOG.error(e.getMessage());
		}
	}
	
	public static void initPurchase() {
		
		try {
			Collection<Purchase> listOfPurchase = CustomerData.getPurchases().values();
			PurchaseDao purchaseDao = PurchaseDao.getInstance();
			Database.getInstance();
			lOG.debug("purchaseList: " + listOfPurchase.size());
			lOG.debug("initPurchase()");
			if (!Database.tableExists(PurchaseDao.TABLE_NAME)) {
				purchaseDao.create();

				for (Purchase i : listOfPurchase) {
					purchaseDao.add(i);
				}
				lOG.debug("List of Purchase was added to " + PurchaseDao.TABLE_NAME);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lOG.error(e.getMessage());
		}
	}
	
	public static void initBook() {
		
		try {
			Collection<Book> listOfBooks = CustomerData.getBooks().values();
			BookDao bookDao = BookDao.getInstance();
			Database.getInstance();

			lOG.debug("bookList: " + listOfBooks.size());
			lOG.debug("initBook()");
			if (!Database.tableExists(BookDao.TABLE_NAME)) {
				bookDao.create();

				for (Book b : listOfBooks) {
					bookDao.add(b);
				}
				lOG.debug("List of Book was added to " + BookDao.TABLE_NAME);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lOG.error(e.getMessage());
		}
	}
	
	public static void initBuyer() {
		try {
			Collection<Buyer> listOfBuyers = CustomerData.getListOfBuyers();
			BuyerDao buyerDao = BuyerDao.getInstance();
			Database.getInstance();

			lOG.debug("buyersList: " + listOfBuyers.size());
			lOG.debug("initBuyer()");
			
			if (!Database.tableExists(BuyerDao.TABLE_NAME)) {
				buyerDao.create();
				
				for (Buyer b : listOfBuyers) {
					buyerDao.add(b);
				}
				
				lOG.debug("List of Buyer was added to " + BuyerDao.TABLE_NAME);
			}
		} catch (Exception e) {
			e.printStackTrace();
			lOG.error(e.getMessage());
		}
	}
}
