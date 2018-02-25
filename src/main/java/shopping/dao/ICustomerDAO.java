package shopping.dao;

import shopping.model.Customer;

import java.util.List;

public interface ICustomerDAO {

	List<Customer> getAllCustomers();
	Customer getCustomerById(Integer customerId);
	Customer getCustomerByName(String customerName);
	Customer getCustomerByIdAndName(Customer customer);
	Customer saveOrUpdateCustomer(Customer customer);

}
