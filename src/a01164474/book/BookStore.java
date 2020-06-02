package a01164474.book;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import a01164474.data.CustomerData;
import a01164474.ui.MainFrame;
/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */
public class BookStore {
	
	private static final String LOG4J_CONFIG_FILENAME = "log4j2.xml";
	static {
		configureLogging();
	}
	
	private static final Logger lOG = LogManager.getLogger();
	/**
	 * @throws Exception 
	 * 
	 */
	public BookStore() throws Exception {
		
	}

	/**
	 * @param args
	 * @throws Exception 
	 * @throws SQLException 
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws SQLException, Exception {
		Instant startTime = Instant.now();
		lOG.info(startTime);
		try {
			lOG.debug("run()");
			CustomerData.retrieveData();
			init();
			new BookStore().createUI();
		} catch (Exception e) {
			lOG.error(e.getMessage());
		} finally {
			Instant endTime = Instant.now();
			lOG.info("\n" + endTime);
			lOG.info(String.format("Duration: %d ms", Duration.between(startTime, endTime).toMillis()));
		}
	}

	/**
	 * Configures log4j2 from the external configuration file specified in LOG4J_CONFIG_FILENAME.
	 * If the configuration file isn't found then log4j2's DefaultConfiguration is used.
	 */
	private static void configureLogging() {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(new FileInputStream(LOG4J_CONFIG_FILENAME));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			System.out.println(String.format("WARNING! Can't find the log4j logging configuration file %s; using DefaultConfiguration for logging.",
					LOG4J_CONFIG_FILENAME));
			Configurator.initialize(new DefaultConfiguration());
		}
	}
	
	public static void createUI() {
		   EventQueue.invokeLater(new Runnable() {
		     
			   public void run() {		      
				   try {
			         MainFrame mainFrame = new MainFrame();
			         mainFrame.setVisible(true);
			       } catch (Exception e) {
			         e.printStackTrace();
			       }
			   }
		   });
	}
	
	public static void init() {
		Controller.initCustomer();
		Controller.initPurchase();
		Controller.initBook();
		Controller.initBuyer();
	}
}
