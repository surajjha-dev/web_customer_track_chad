package com.wct.service;

import java.util.List;

import com.wct.dao.CustomerDAO;
import com.wct.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject customer dao
	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public Customer getCustomerById(int customerId) {
		return customerDAO.getCustomerById(customerId);
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		customerDAO.saveCustomer(customer);
	}

	@Override
	@Transactional
	public void deleteCustomer(int customerId) {
		customerDAO.deleteCustomer(customerId);
	}

	@Override
	public List<Customer> searchCustomers(String search) {
		return customerDAO.searchCustomers(search);
	}
}





