package a01164474.data;

import java.util.Comparator;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */
public class ComparePurchaseByTitle implements Comparator<Buyer> {
	@Override
	public int compare(Buyer buyer1, Buyer buyer2) {
	      return buyer1.getTitle().compareToIgnoreCase(buyer2.getTitle());
	    }
}
