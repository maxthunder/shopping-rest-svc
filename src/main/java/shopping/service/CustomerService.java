package shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.dao.IBaseDAO;
import shopping.repos.CustomerRepository;
import shopping.util.ResourceNotFoundException;
import shopping.util.BadRequestException;
import shopping.model.Customer;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

	private final IBaseDAO baseDAO;
	private final CustomerRepository customerRepository;


	@Autowired
	public CustomerService(IBaseDAO baseDAO, CustomerRepository customerRepository) {
		this.baseDAO = baseDAO;
		this.customerRepository = customerRepository;
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public Customer getCustomerById(Integer customerId) {
		Optional<Customer> opt = customerRepository.findByCustomerId(customerId);
		if (!opt.isPresent()) {
			throw new ResourceNotFoundException("Customer with ID", customerId);
		}
		return opt.get();
	}

	public Customer getCustomerByName(String customerName) {
		Optional<Customer> opt = customerRepository.findByCustomerName(customerName);
		if (!opt.isPresent()) {
			throw new ResourceNotFoundException("Customer with Name", customerName);
		}
		return opt.get();
	}

	public Customer saveCustomer(String customerName) {
		if (customerRepository.findByCustomerName(customerName).isPresent()) {
			throw new BadRequestException("Customer at name <"+customerName+"> already exists in database.");
		}
		return customerRepository.saveAndFlush(new Customer(customerName));
	}

	public Customer updateCustomer(Integer customerId, String customerName) {
		// Attempt to load by customer ID, null indicates a new entry needed.
		final Customer loadedCustomer = getCustomerById(customerId);

		// Make sure customer name is new or already existing with customer ID
		boolean inputMatchesExistingCustomerName = loadedCustomer.getCustomerName().equals(customerName);
		boolean customerNameAlreadyExists = customerRepository.findByCustomerName(customerName).isPresent();

		if (!inputMatchesExistingCustomerName && customerNameAlreadyExists) {
			throw new BadRequestException("Customer at name <"+customerName+"> already exists in database.");
		}

		// saveOrUpdate if any fields have changed (customer was built)
		return customerRepository.saveAndFlush(new Customer(customerId, customerName));
	}

	// Both customer ID and name are used to validate customer before deletion to avoid 'fat-fingering' mishaps.
	// REVIEW: Add customerStatus==DELETABLE to add further protection from accidental customer deletions
	public Customer deleteCustomer(Integer customerId, String customerName) {
		Optional<Customer> opt = customerRepository.findByCustomerIdAndCustomerName(customerId, customerName);
		if (!opt.isPresent()) {
			throw new ResourceNotFoundException("Customer with <ID, Name> :", customerId+","+customerName);
		}
		Customer customer = opt.get();
		baseDAO.delete(customer);
		return customer;
	}
}
