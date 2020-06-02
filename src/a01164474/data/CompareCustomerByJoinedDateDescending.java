package a01164474.data;

import java.util.Comparator;

/**
 * Project: Assignment 2
 * @author Nathan Souza, A01164474
 */
public class CompareCustomerByJoinedDateDescending implements Comparator<Customer> {
	@Override
	public int compare(Customer customer1, Customer customer2) {
		return customer2.getJoinedDate().compareTo(customer1.getJoinedDate());
	}
}
