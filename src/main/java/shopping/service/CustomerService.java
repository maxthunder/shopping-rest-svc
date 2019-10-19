package shopping.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shopping.dao.IBaseDAO;
import shopping.repos.CustomerRepository;
import shopping.util.ResourceNotFoundException;
import shopping.util.BadRequestException;
import shopping.dao.ICustomerDAO;
import shopping.model.Customer;

import java.util.List;

@Service
public class CustomerService {

	private final IBaseDAO baseDAO;
	private final ICustomerDAO customerDAO;
	private final CustomerRepository customerRepository;


	@Autowired
	public CustomerService(IBaseDAO baseDAO, ICustomerDAO customerDAO,
						   CustomerRepository customerRepository) {
		this.baseDAO = baseDAO;
		this.customerDAO = customerDAO;
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAllCustomers() {
		return customerDAO.getAllCustomers();
	}

	public Customer getCustomerById(Integer customerId) {
		Customer customer = customerDAO.getCustomerById(customerId);
		if (customer == null) {
			throw new ResourceNotFoundException("Customer with ID", customerId);
		}
		return customer;
	}

	public Customer getCustomerByName(String customerName) {
		Customer customer = customerDAO.getCustomerByName(customerName);
		if (customer == null) {
			throw new ResourceNotFoundException("Customer with Name", customerName);
		}
		return customer;
	}

	public Customer saveCustomer(String customerName) {
		if (customerDAO.getCustomerByName(customerName) != null) {
			throw new BadRequestException("Customer at name <"+customerName+"> already exists in database.");
		}
		return customerRepository.save(new Customer(customerName));
	}

	public Customer updateCustomer(Integer customerId, String customerName) {
		// Attempt to load by customer ID, null indicates a new entry needed.
		final Customer loadedCustomer = customerDAO.getCustomerById(customerId);
		if (loadedCustomer == null) {
			throw new ResourceNotFoundException("Customer with ID", customerId);
		}

		// Make sure customer name is new or already existing with customer ID
		boolean inputMatchesExistingCustomerName = loadedCustomer.getCustomerName().equals(customerName);
		boolean customerNameAlreadyExists = customerDAO.getCustomerByName(customerName) != null;

		if (!inputMatchesExistingCustomerName && customerNameAlreadyExists) {
			throw new BadRequestException("Customer at name <"+customerName+"> already exists in database.");
		}

		// saveOrUpdate if any fields have changed (customer was built)
		return customerDAO.saveOrUpdateCustomer(customerId, customerName);
	}

	// Both customer ID and name are used to validate customer before deletion to avoid 'fat-fingering' mishaps.
	// REVIEW: Add customerStatus==DELETABLE to add further protection from accidental customer deletions
	public Customer deleteCustomer(Integer customerId, String customerName) {
		Customer customer = customerDAO.getCustomerByIdAndName(customerId, customerName);
		if (customer == null) {
			throw new ResourceNotFoundException("Customer with <ID, Name> :", customerId+","+customerName);
		}
		baseDAO.delete(customer);
		return customer;
	}
}
