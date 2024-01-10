package com.wct.dao;

import com.wct.entity.Customer;

import java.util.List;


public interface CustomerDAO {

	public List<Customer> getCustomers();

	public Customer getCustomerById(int id);

	public void saveCustomer(Customer customer);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomers(String search);
	
}
