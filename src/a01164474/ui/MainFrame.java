package a01164474.ui;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBoxMenuItem;
import java.awt.SystemColor;
import javax.swing.JLabel;


/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private static final Logger LOG = LogManager.getLogger();
	private JPanel contentPane;
	
	private JMenuBar mainMenuBar;
	private JMenu fileMenu;	
	private JMenu customersMenu;
	private JMenu helpMenu;
	private JMenu booksMenu;	
	private JMenu purchasesMenu;
	
	private JMenu drop;
	private JMenu load;
	private JMenuItem customersCount;	
	private JMenuItem customersList;	
	private JMenuItem booksCount;
	private JMenuItem booksList;		
	private JMenuItem purchasesCount;
	private JMenuItem purchasesTotal;
	private JMenuItem filterById;
	private JMenuItem purchasesList;	
	private JMenuItem bookDrop;
	private JMenuItem purchaseDrop;
	private JMenuItem about;
	private JMenuItem customerDrop;
	private JMenuItem customerLoad;
	private JMenuItem bookLoad;
	private JMenuItem purchaseLoad;
	
	public static JCheckBoxMenuItem byJoinDate;
	public static JCheckBoxMenuItem booksByAuthor;
	public static JCheckBoxMenuItem booksDescending;
	public static JCheckBoxMenuItem purchaseByLastName;
	public static JCheckBoxMenuItem purchaseByTitle;
	public static JCheckBoxMenuItem purchasesDescending;
	
	private JMenuItem exit;
	private JLabel lblNewLabel;
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		buildMenu();
		addEventHandlers();		
	}
	
public void buildMenu() {
		//PARAMETERS MAIN MENU
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 200);
		setLocationRelativeTo(null);		
		setTitle("Assignment 2");
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow][][grow]", "[][]"));
		
		lblNewLabel = new JLabel("Press F1 to open option About");
		lblNewLabel.setForeground(SystemColor.desktop);
		contentPane.add(lblNewLabel, "cell 1 1");
		
		//MAIN MENU
		mainMenuBar = new JMenuBar();
		setJMenuBar(mainMenuBar);
		
		//<--FILE MENU-->>
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		mainMenuBar.add(fileMenu);	
		load = new JMenu("Create");
		drop = new JMenu("Drop");
		customerDrop = new JMenuItem("Customer Table");
		bookDrop = new JMenuItem("Book Table");
		purchaseDrop = new JMenuItem("Purchase Table");
		customerLoad = new JMenuItem("Customer Table");
		bookLoad = new JMenuItem("Book Table");
		purchaseLoad = new JMenuItem("Purchase Table");
		drop.add(customerDrop);
		drop.add(bookDrop);
		drop.add(purchaseDrop);
		load.add(customerLoad);
		load.add(bookLoad);
		load.add(purchaseLoad);
		fileMenu.add(load);
		fileMenu.add(drop);
		drop.setMnemonic('D');
		fileMenu.addSeparator();
		exit = new JMenuItem("Quit",KeyEvent.VK_X);
		fileMenu.add(exit);
		
		//<--BOOKS MENU-->>
		booksMenu = new JMenu("Books");
		booksMenu.setMnemonic('B');
		mainMenuBar.add(booksMenu);
		
		booksCount = new JMenuItem("Count");
		booksMenu.add(booksCount);
		
		booksByAuthor = new JCheckBoxMenuItem("By Author");
		booksMenu.add(booksByAuthor);
		
		booksDescending = new JCheckBoxMenuItem("Descending");
		booksMenu.add(booksDescending);
		
		booksMenu.addSeparator();
		
		booksList = new JMenuItem("List");
		booksMenu.add(booksList);
		
		//<--CUSTOMERS MENU-->
		customersMenu = new JMenu("Customers");
		customersMenu.setMnemonic('C');
		mainMenuBar.add(customersMenu);
		
		customersCount = new JMenuItem("Count");
		customersMenu.add(customersCount);
		
		byJoinDate = new JCheckBoxMenuItem("By Joined Date");
		customersMenu.add(byJoinDate);
		
		customersMenu.addSeparator();
		
		customersList = new JMenuItem("List");
		customersMenu.add(customersList);
		
		//<--PURCHASES MENU-->
		purchasesMenu = new JMenu("Purchases");
		purchasesMenu.setMnemonic('P');
		mainMenuBar.add(purchasesMenu);
		
		purchasesCount = new JMenuItem("Count");
		purchasesMenu.add(purchasesCount);
		
		purchasesTotal = new JMenuItem("Total");
		purchasesMenu.add(purchasesTotal);
		
		purchaseByLastName = new JCheckBoxMenuItem("By Last Name");
		purchasesMenu.add(purchaseByLastName);
		
		purchaseByTitle = new JCheckBoxMenuItem("By Title");
		purchasesMenu.add(purchaseByTitle);
		
		purchasesDescending = new JCheckBoxMenuItem("Descending");
		purchasesMenu.add(purchasesDescending);
		
		filterById = new JMenuItem("Filter By ID");
		purchasesMenu.add(filterById);
		
		purchasesMenu.addSeparator();
		
		purchasesList = new JMenuItem("List");
		purchasesMenu.add(purchasesList);
		
		//<--ABOUT MENU-->>
		about = new JMenuItem("About");
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		helpMenu = new JMenu("Help");
		helpMenu.setMnemonic('H');
		mainMenuBar.add(helpMenu);
		helpMenu.add(about);
	}

	private void addEventHandlers() {
		LOG.debug("MainFrame received EventHandlers");
		new UIController(this);
		//customers menu
		customersCount.addActionListener(new UIController.CustomerCount());
		customersList.addActionListener(new UIController.ListCustomers());
		//books menu
		booksCount.addActionListener(new UIController.BooksCount());		
		booksList.addActionListener(new UIController.BooksList());
		//purchase menu
		purchasesTotal.addActionListener(new UIController.TotalPurchase());
		purchasesCount.addActionListener(new UIController.PurchaseCount());
		purchasesList.addActionListener(new UIController.PurchaseList());
		filterById.addActionListener(new UIController.FilterByID());
		//file menu
		customerDrop.addActionListener(new UIController.DropCustomerTable());
		bookDrop.addActionListener(new UIController.DropBookTable());
		purchaseDrop.addActionListener(new UIController.DropPurchaseTable());
		customerLoad.addActionListener(new UIController.LoadCustomerTable());
		bookLoad.addActionListener(new UIController.LoadBookTable());
		purchaseLoad.addActionListener(new UIController.LoadPurchaseTable());
		exit.addActionListener(new UIController.Exit());
		//about menu
		about.addActionListener(new UIController.About());
	}

}
