package shopping.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shopping.util.ResourceNotFoundException;
import shopping.model.Customer;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CustomerDAO implements ICustomerDAO {

	private final String GET_CUSTOMER_QUERY
			= "SELECT customer_id, customer_name FROM shopping.customer\n";

	private final IBaseDAO baseDAO;

	@Autowired
	public CustomerDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		for (Object[] objects : (List<Object[]>) baseDAO.getListFromNativeQuery(GET_CUSTOMER_QUERY)) {
			customers.add(new Customer((Integer) objects[0], (String) objects[1]));
		}

		return customers;
	}

	@Override
	public Customer getCustomerById(Integer customerId) {
		String query = GET_CUSTOMER_QUERY + "WHERE customer_id = :customerId\n";

		Map<String, Object> queryParameters = new HashMap<>();
		queryParameters.put("customerId", customerId);

		Object[] results = baseDAO.getObjectArrayFromNativeQuery(query, queryParameters);

		return buildCustomer(results);
	}

	@Override
	public Customer getCustomerByName(String customerName) {
		String query = GET_CUSTOMER_QUERY + "WHERE customer_name = :customerName\n";

		Map<String, Object> queryParameters = new HashMap<>();
		queryParameters.put("customerName", customerName);

		Object[] results = baseDAO.getObjectArrayFromNativeQuery(query, queryParameters);
		if (results == null) {
			throw new ResourceNotFoundException("Customer at name", customerName);
		}

		return buildCustomer(results);
	}

	@Override
	public Customer getCustomerByIdAndName(Customer customer) {
		String query = GET_CUSTOMER_QUERY + "WHERE customer_id = :customerId AND customer_name = :customerName\n";

		Map<String, Object> queryParameters = new HashMap<>();
		queryParameters.put("customerId", customer.getCustomerId());
		queryParameters.put("customerName", customer.getCustomerName());

		Object[] results = baseDAO.getObjectArrayFromNativeQuery(query, queryParameters);

		return buildCustomer(results);
	}

	@Override
	public Customer saveOrUpdateCustomer(Customer customerToPersist) {
		Customer customer = new Customer();

		if (customerToPersist.getCustomerId() == null) {// new
			customer.setCustomerId((Integer) baseDAO.getCurrentSession().save(customerToPersist));
			customer.setCustomerName(customerToPersist.getCustomerName());

		} else {// existing
			baseDAO.getCurrentSession().saveOrUpdate(customerToPersist);
			customer = customerToPersist;
		}
		return customer;
	}

	@Override
	public Customer delete(Customer customer) {
		baseDAO.getCurrentSession().delete(customer);
		return customer;
	}

	// objects[]: customerId, customerName
	private Customer buildCustomer(Object[] objects) {
		Customer customer = null;

		// only load if required fields are populated.
		if (objects != null) {
			Integer customerId = (objects[0] == null) ? null : (Integer) objects[0];
			String customerName = (objects[1] == null) ? null : (String) objects[1];
			if (customerId != null && customerName != null) {
				customer = new Customer(customerId, customerName);
			}
		}
		return customer;
	}
}
