package shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shopping.util.ResourceNotFoundException;
import shopping.util.BadRequestException;
import shopping.dao.ICustomerDAO;
import shopping.model.Customer;

import java.util.List;

@Service
public class CustomerService {

	@Autowired
	private ICustomerDAO customerDAO;

	public List<Customer> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}

	public Customer getCustomer(Integer customerId) {
		Customer customer = customerDAO.getCustomerById(customerId);
		if (customer == null) {
			throw new ResourceNotFoundException("Customer at ID", customerId);
		}
		return customer;
	}

	public Customer saveCustomer(String customerName) {
		if (customerDAO.getCustomerByName(customerName) != null) {
			throw new BadRequestException("Customer at name <"+customerName+"> already exists in database.");
		}
		return customerDAO.saveOrUpdateCustomer(new Customer(customerName));
	}

	public Customer updateCustomer(Integer customerId, String customerName) {
		Customer loadedCustomer = customerDAO.getCustomerById(customerId);

		Customer customer = null;
		if (loadedCustomer == null) {
			customer = new Customer(customerId, customerName);
		} else if (!loadedCustomer.getCustomerName().equals(customerName)) {
			customer = new Customer(loadedCustomer.getCustomerId(), customerName);
		}

		return customer != null ? customerDAO.saveOrUpdateCustomer(customer) : loadedCustomer;
	}

	// Both customer ID and name are used to validate customer before deletion
	// to avoid 'fat-fingering' mishaps. // REVIEW: Add customerStatus==DELETABLE to add further protection from accidental customer deletions
	public Customer deleteCustomer(Integer customerId, String customerName) {
		Customer customer = customerDAO.getCustomerByIdAndName(new Customer(customerId, customerName));
		if (customer == null) {
			throw new ResourceNotFoundException("Customer at <ID, Name> :", customerId+", "+customerName);
		}
		return customerDAO.delete(customer);
	}
}
