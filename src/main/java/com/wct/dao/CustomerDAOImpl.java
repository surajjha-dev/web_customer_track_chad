package com.wct.dao;

import java.util.List;

import com.wct.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	@Qualifier("customerSessionFactory")
	private SessionFactory sessionFactory;

	@Override
	@Transactional("customerTransactionManager")
	public List<Customer> getCustomers() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Customer> theQuery =
				currentSession.createQuery("from Customer", Customer.class);

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the results		
		return customers;
	}

	@Override
	@Transactional("customerTransactionManager")
	public Customer getCustomerById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Customer customer = session.get(Customer.class, id);

		return customer;
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(customer);
	}

	@Override
	public void deleteCustomer(int customerId) {
			Session currentSession = sessionFactory.getCurrentSession();
			currentSession.createQuery("delete from Customer where id = :customerId")
					.setParameter("customerId", customerId).executeUpdate();
	}

	@Override
	@Transactional
	public List<Customer> searchCustomers(String search) {
		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = null;
		//
		// only search by name if theSearchName is not empty
		//
		if (search != null && search.trim().length() > 0) {
			// search for firstName or lastName ... case insensitive
			theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
			theQuery.setParameter("theName", "%" + search.toLowerCase() + "%");
		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =currentSession.createQuery("from Customer", Customer.class);
		}

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the results
		return customers;
	}


}






