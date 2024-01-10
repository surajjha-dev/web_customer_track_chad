package com.wct.service;

import com.wct.entity.Customer;
import com.wct.entity.Employee;

import java.util.List;


public interface MultiDataSourceService {

	public List<Customer> getCustomers();
	
	public List<Employee> getEmployees();
	
}
