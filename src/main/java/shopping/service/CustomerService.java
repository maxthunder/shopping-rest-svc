package shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shopping.dao.IBaseDAO;
import shopping.util.ResourceNotFoundException;
import shopping.util.BadRequestException;
import shopping.dao.ICustomerDAO;
import shopping.model.Customer;

import java.util.List;

@Service
public class CustomerService {

	private final IBaseDAO baseDAO;
	private final ICustomerDAO customerDAO;

	@Autowired
	public CustomerService(IBaseDAO baseDAO, ICustomerDAO customerDAO) {
		this.baseDAO = baseDAO;
		this.customerDAO = customerDAO;
	}

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
		// Attempt to load by customer ID, null indicates a new entry needed.
		Customer loadedCustomer = customerDAO.getCustomerById(customerId);

		// Make sure customer name is new or already existing with customer ID
		if (loadedCustomer == null && customerDAO.getCustomerByName(customerName) != null) {
			throw new BadRequestException("Customer at name <"+customerName+"> already exists in database.");
		}

		// Build Customer pojo
		Customer customer = null;
		if (loadedCustomer == null) {// new - build for save
			customer = new Customer(customerId, customerName);

		} else if (!loadedCustomer.getCustomerName().equals(customerName)) {// existing with changed field - build for update
			customer = new Customer(loadedCustomer.getCustomerId(), customerName);
		}

		// saveOrUpdate if any fields have changed (customer was built)
		return customer != null ? customerDAO.saveOrUpdateCustomer(customer) : loadedCustomer;
	}

	// Both customer ID and name are used to validate customer before deletion to avoid 'fat-fingering' mishaps.
	// REVIEW: Add customerStatus==DELETABLE to add further protection from accidental customer deletions
	public Customer deleteCustomer(Integer customerId, String customerName) {
		Customer customer = customerDAO.getCustomerByIdAndName(new Customer(customerId, customerName));
		if (customer == null) {
			throw new ResourceNotFoundException("Customer at <ID, Name> :", customerId+", "+customerName);
		}
		baseDAO.delete(customer);
		return customer;
	}
}
