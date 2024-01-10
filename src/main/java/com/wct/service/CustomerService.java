package com.wct.service;

import com.wct.entity.Customer;
import java.util.List;

public interface CustomerService {

	public List<Customer> getCustomers();

	public Customer getCustomerById(int customerId);

	public void saveCustomer(Customer customer);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomers(String search);
	
}
