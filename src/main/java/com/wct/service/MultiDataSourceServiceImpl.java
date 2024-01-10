package com.wct.service;

import java.util.List;

import com.wct.dao.CustomerDAO;
import com.wct.dao.EmployeeDAO;
import com.wct.entity.Customer;
import com.wct.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiDataSourceServiceImpl implements MultiDataSourceService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Override
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	public List<Employee> getEmployees() {
		return employeeDAO.getEmployees();
	}

}
