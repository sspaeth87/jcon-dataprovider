package de.xdevsoftware.storage;

import de.xdevsoftware.domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class DataRoot {

    private final List<Customer> customers = new ArrayList<>();

	public List<Customer> getCustomers() {
		return customers;
	}
    
    
}
