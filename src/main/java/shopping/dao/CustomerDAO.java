package shopping.dao;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shopping.util.ResourceNotFoundException;
import shopping.model.Customer;

import org.springframework.transaction.annotation.Transactional;
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
		List<Object[]> result = (List<Object[]>) baseDAO.getListFromNativeQuery(GET_CUSTOMER_QUERY + " ORDER BY customer_name");
		if (result != null && !result.isEmpty()) {
			for (Object[] objects : result) {
				customers.add(new Customer((Integer) objects[0], (String) objects[1]));
			}
		} else {
			throw new ResourceNotFoundException("No customers were returned from database within persistence layer.");
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

		return buildCustomer(results);
	}

	@Override
	public Customer getCustomerByIdAndName(Integer customerId, String customerName) {
		String query = GET_CUSTOMER_QUERY + "WHERE customer_id = :customerId AND customer_name = :customerName\n";

		Map<String, Object> queryParameters = new HashMap<>();
		queryParameters.put("customerId", customerId);
		queryParameters.put("customerName", customerName);

		Object[] results = baseDAO.getObjectArrayFromNativeQuery(query, queryParameters);

		return buildCustomer(results);
	}

	@Override
	public Customer saveOrUpdateCustomer(String customerName) {
		return saveOrUpdateCustomer(null, customerName);
	}

	@Override
	public Customer saveOrUpdateCustomer(Integer customerId, String customerName) {
		Customer customer = new Customer(customerId, customerName);

//		if (customerId == null) {// new
//			Integer id = (Integer) baseDAO.save(customer);
//			customer.setCustomerId(id);
//		} else {// existing
			baseDAO.saveOrUpdate(customer);
//		}


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
