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
import a01164474.data.Purchase;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */

public class PurchaseReader {
	
	public static final String PURCHASE_FILENAME = "purchases.csv";
	private static final Logger lOG = LogManager.getLogger();

	public Map<Long, Purchase> read() throws ApplicationException {		

		File file = new File(PURCHASE_FILENAME);
		FileReader in;
		Iterable<CSVRecord> records;
		try {
			in = new FileReader(file);
			records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
		} catch (IOException e) {
			throw new ApplicationException(e);
		}

		Map<Long, Purchase> purchaseList = new HashMap<>();
		Purchase newPurchase;
		
		lOG.debug("Reading " + file.getAbsolutePath());
		
		for (CSVRecord record : records) {
			long id = Long.parseLong(record.get("id"));
			long customerID = Long.parseLong(record.get("customer_id"));
			long bookID = Long.parseLong(record.get("book_id"));
			double price = Double.parseDouble(record.get("price"));
			
			newPurchase = new Purchase.Builder(id, price)
						.setCustomerID(customerID)
						.setBookID(bookID).build();
				
			purchaseList.put(Long.valueOf(newPurchase.getPurchaseID()), newPurchase);
			}
			return purchaseList;
		}
}
