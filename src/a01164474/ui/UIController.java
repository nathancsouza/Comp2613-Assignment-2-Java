package a01164474.ui;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import a01164474.book.Controller;
import a01164474.data.Book;
import a01164474.data.Buyer;
import a01164474.data.CompareBookByAuthor;
import a01164474.data.CompareBookByAuthorDescending;
import a01164474.data.CompareCustomerByJoinedDate;
import a01164474.data.ComparePurchaseByLastName;
import a01164474.data.ComparePurchaseByLastNameDescending;
import a01164474.data.ComparePurchaseByTitle;
import a01164474.data.ComparePurchaseByTitleDescending;
import a01164474.data.Customer;
import a01164474.data.CustomerData;
import a01164474.database.BookDao;
import a01164474.database.BuyerDao;
import a01164474.database.CustomerDao;
import a01164474.database.DbConstants;
import a01164474.database.PurchaseDao;
import a01164474.ui.CustomerDialog;


/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

public class UIController {	
	
	private static final Logger lOG = LogManager.getLogger();
	private static JFrame mainFrame;
	private static PurchasesListDialog purchaseDialog;
	private static BookListDialog bookDialog;
	private static CustomerListDialog customerDialog;
	private static List<Buyer> filteredBuyers;
	private static boolean isFiltered;
	private static long inputFilter;
	private static String customer;
	
	@SuppressWarnings("static-access")
	public UIController(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	protected static class CustomerCount implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				lOG.debug("Option Customers Count was chosen");
				lOG.debug("Customers Count: " + CustomerData.getCustomers().size());
				JOptionPane.showMessageDialog(mainFrame, 
						"Number of Customers loaded: " + CustomerData.getCustomers().size(), "Customers", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				e1.printStackTrace();
			}		
		}		
	}
	
	protected static class Exit implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lOG.debug("Option quit was chosen");			
			System.out.println("Program Closed");
			System.exit(0);
		}
	}	
	
	protected static class ListCustomers implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				lOG.debug("Option List in Customers Menu was chosen");
				CustomerDao customerDao = CustomerDao.getInstance();				
				List<Customer> customerList = customerDao.getCustomers();				
				if(MainFrame.byJoinDate.getState()) {
					customerList.sort(new CompareCustomerByJoinedDate());
					lOG.debug("Checkbox byJoinDate in Customers Menu was selected");
				}
				customerDialog = new CustomerListDialog(customerList);
				customerDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				customerDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				customerDialog.setVisible(true);
			} catch (Exception exception) {
				exception.printStackTrace();
				lOG.error(exception.getMessage());
			}
		}
	}
	
	protected static class CustomerListSelection implements ListSelectionListener {
		private JList<Customer> customerList;
		private DefaultListModel<Customer> listModel;
		
		public CustomerListSelection(JList<Customer> customerList) {
			this.customerList = customerList;
			listModel = (DefaultListModel<Customer>) customerList.getModel();
		}

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				return;
			}

			Customer customer = customerList.getSelectedValue();
			if (customer != null) {
				lOG.debug("Customer selected: " + customerList.getSelectedIndex());
				updateCustomer(customer, customerList.getSelectedIndex());
			}
		}
			
		private void updateCustomer(Customer customer, int index) {
			try {
				CustomerDao customerDao = CustomerDao.getInstance();
				CustomerDialog dialog = new CustomerDialog(customer);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setModalityType(ModalityType.APPLICATION_MODAL);
				dialog.setVisible(true);
				lOG.debug("Updating customer ID: " + customer.getCustomerID());
				customer = dialog.getCustomer();
				customerDao.update(customer);
				listModel.set(index, customer);				
			} catch (Exception exception) {
				exception.printStackTrace();
				lOG.error(exception.getMessage());
			}
			customerList.clearSelection();
		}		
	}
	
	protected static class BooksCount implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				lOG.debug("Option Books Count was chosen");
				lOG.debug("Books Count: " + CustomerData.getBooks().size());
				JOptionPane.showMessageDialog(mainFrame, 
						"Number of Books loaded: " + CustomerData.getBooks().size(), "Books", JOptionPane.INFORMATION_MESSAGE);
			} catch (Exception e1) {
				e1.printStackTrace();
			}		
		}
	}

	protected static class BooksList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				lOG.debug("Option List in Book Menu was chosen");
				BookDao bookDao = BookDao.getInstance();				
				List<Book> bookList = bookDao.getBooks();				
				if(MainFrame.booksByAuthor.getState()) {
					if(MainFrame.booksDescending.getState()) {
						bookList.sort( new CompareBookByAuthorDescending());
						lOG.debug("Checkbox booksDescending in Books Menu was selected");
					}else {
						bookList.sort( new CompareBookByAuthor());
						lOG.debug("Checkbox booksByAuthor in Books Menu was selected");
					}
				}
				bookDialog = new BookListDialog(bookList);
				bookDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				bookDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				bookDialog.setVisible(true);
			} catch (Exception exception) {
				exception.printStackTrace();
				lOG.error(exception.getMessage());
			}
		}
	}
	
	protected static class About implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String about = "Assignment 2\nNathan Souza A01164474";
			lOG.debug("About: " + about);
			JOptionPane.showMessageDialog(mainFrame, 
					about, "Author", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	protected static class PurchaseCount implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				lOG.debug("Option Purchases Count was chosen");
				BuyerDao buyerDao = BuyerDao.getInstance();
				
				if(customer == null || customer.isEmpty()) {					
					filteredBuyers = buyerDao.getPurchases();
				}				
				
				if(customer != null) {
					if(isFiltered) {
						filteredBuyers = new ArrayList<>();
						buyerDao.getBuyerByID(customer);					
							for(Buyer b : buyerDao.getPurchases()) {
								if(b.getCustomerID() == inputFilter) {
									filteredBuyers.add(b);
								} 
							}				 
					}					
				}
				lOG.debug("Purchases Count: " + filteredBuyers.size());
				JOptionPane.showMessageDialog(mainFrame, 
						"Number of Purchases loaded: " + filteredBuyers.size(), "Purchases", JOptionPane.INFORMATION_MESSAGE);				
			} catch (Exception e1) {
				e1.printStackTrace();
			}		
		}
	}	
	
	protected static class TotalPurchase implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lOG.debug("Option Total in Purchases Menu was chosen");
			double total = 0.0;
			try {
				BuyerDao buyerDao = BuyerDao.getInstance();
				
				if(customer == null || customer.isEmpty()) {
					filteredBuyers = buyerDao.getPurchases();
				}				
				
				if(customer != null) {
					if(isFiltered) {
						filteredBuyers = new ArrayList<>();
						buyerDao.getBuyerByID(customer);					
							for(Buyer b : buyerDao.getPurchases()) {
								if(b.getCustomerID() == inputFilter) {
									filteredBuyers.add(b);
								} 
							}				 
					}					
				}
				
				for (Buyer i : filteredBuyers) {
					total += i.getPrice();
				}
			} catch (Exception exception) {
				exception.printStackTrace();
				lOG.error(exception.getMessage());
			}
			lOG.debug(String.format("Total of purchases: $%,.2f\n ", total));
			JOptionPane.showMessageDialog(mainFrame, String.format("Total of purchases: $%,.2f\n ", total));
		}
	}
	
	protected static class PurchaseList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {			
			try {
				lOG.debug("Option List in Purchases Menu was chosen");
				BuyerDao buyerDao = BuyerDao.getInstance();				
				filteredBuyers = buyerDao.getPurchases();
				if(customer == null || customer.isEmpty()) {
					if(MainFrame.purchaseByLastName.getState()) {
						if(MainFrame.purchasesDescending.getState()) {
							filteredBuyers.sort(new ComparePurchaseByLastNameDescending());
							lOG.debug("Checkbox purchasesDescending in Purchase Menu was selected");
						}else {
							filteredBuyers.sort(new ComparePurchaseByLastName());
							lOG.debug("Checkbox purchaseByLastName in Purchase Menu was selected");
						}					
					}
					if(MainFrame.purchaseByTitle.getState()) {
						if(MainFrame.purchasesDescending.getState()) {
							filteredBuyers.sort(new ComparePurchaseByTitleDescending());
							lOG.debug("Checkbox purchasesDescending in Purchase Menu was selected");
						}else {
							filteredBuyers.sort(new ComparePurchaseByTitle());
							lOG.debug("Checkbox purchaseByTitle in Purchase Menu was selected");
						}					
					}
				}
				
				if(customer != null) {
					if(isFiltered) {
						filteredBuyers = new ArrayList<>();
						buyerDao.getBuyerByID(customer);					
							for(Buyer b : buyerDao.getPurchases()) {
								if(b.getCustomerID() == inputFilter) {
									filteredBuyers.add(b);
								} 
							}				 
					}
					if(filteredBuyers.size() <= 0) {
						JOptionPane.showMessageDialog(mainFrame, "No customer found");
					} 
				}				
				purchaseDialog = new PurchasesListDialog(filteredBuyers);				
				purchaseDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				purchaseDialog.setModalityType(ModalityType.APPLICATION_MODAL);
				purchaseDialog.setVisible(true);
			} catch (Exception exception) {
				exception.printStackTrace();
				lOG.error(exception.getMessage());
			}
		}
	}
	
	protected static class DropCustomerTable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				lOG.debug("Option drop customer in File/Drop Menu was chosen");				
				CustomerDao.getInstance().drop();
				lOG.debug(DbConstants.CUSTOMER_TABLE_NAME + " was dropped");
			} catch (Exception exception) {
				exception.printStackTrace();
				lOG.error(exception.getMessage());
			}
		}
	}
	
	protected static class DropBookTable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				lOG.debug("Option drop book in File/Drop Menu was chosen");
				BookDao.getInstance().drop();
				lOG.debug(DbConstants.BOOK_TABLE_NAME + " was dropped");
			} catch (Exception exception) {
				exception.printStackTrace();
				lOG.error(exception.getMessage());
			}
		}
	}
	
	protected static class DropPurchaseTable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				lOG.debug("Option drop purchase in File/Drop Menu was chosen");
				PurchaseDao.getInstance().drop();
				BuyerDao.getInstance().drop();
				lOG.debug(DbConstants.PURCHASE_TABLE_NAME + " was dropped\n" 
						+ DbConstants.BUYER_TABLE_NAME + " was dropped");
			} catch (Exception exception) {
				exception.printStackTrace();
				lOG.error(exception.getMessage());
			}
		}
	}
	
	protected static class LoadCustomerTable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				lOG.debug("Option load customer in File/Load Menu was chosen");				
				Controller.initCustomer();
			} catch (Exception exception) {
				exception.printStackTrace();
				lOG.error(exception.getMessage());
			}
		}
	}
	
	protected static class LoadBookTable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				lOG.debug("Option load book in File/Load Menu was chosen");
				Controller.initBook();
			} catch (Exception exception) {
				exception.printStackTrace();
				lOG.error(exception.getMessage());
			}
		}
	}
	
	protected static class LoadPurchaseTable implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				lOG.debug("Option load purchase in File/Load Menu was chosen");
				Controller.initPurchase();
				Controller.initBuyer();
			} catch (Exception exception) {
				exception.printStackTrace();
				lOG.error(exception.getMessage());
			}
		}
	}
	
	protected static class FilterByID implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lOG.debug("Option Filter By ID in Purchase Menu was chosen");
			String string = JOptionPane.showInputDialog("Enter Customer ID: \n");
			lOG.debug("Input filter is: " + string);
			if (string == null || string.isEmpty()) {
				isFiltered = false;	
				JOptionPane.showMessageDialog(mainFrame, "No customer found");
			} else {
				customer = string;
				isFiltered = true;
				inputFilter = Long.parseLong(customer);
			}
		}
	}
}
