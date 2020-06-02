package a01164474.data;

import java.util.Comparator;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */
public class ComparePurchaseByTitleDescending implements Comparator<Buyer> {
	@Override
	public int compare(Buyer buyer1, Buyer buyer2) {
	      return buyer2.getTitle().compareToIgnoreCase(buyer1.getTitle());
	    }
}
