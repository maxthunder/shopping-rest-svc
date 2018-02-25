package shopping.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import shopping.util.BadRequestException;
import shopping.model.ShirtOrder;
import shopping.repos.ShirtOrderRepository;
import shopping.util.ResourceNotFoundException;

@Service
public class ShirtOrderService {
	private static final Set<Integer> tempCustomerIdStore = new HashSet<>();

	static {
		tempCustomerIdStore.addAll(Arrays.asList(1, 2, 3));
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private ShirtOrderRepository shirtOrderRepository;

	public Iterable<ShirtOrder> getAllShirtOrders() {
		return this.shirtOrderRepository.findAll();
	}

	public ShirtOrder getShirtOrder(Integer shirtOrderId) {
		return null;
	}

	public ShirtOrder createShirtOrder(String shirtName, String size, Integer customerId) {
		if (!tempCustomerIdStore.contains(customerId)) {
			throw new ResourceNotFoundException("Customer at ID", customerId);
		} else {
			ShirtOrder pojo = new ShirtOrder();
			pojo.setShirtName(shirtName);
			pojo.setSize(size);
			pojo.setCustomerId(customerId);
			this.shirtOrderRepository.save(pojo);
			return pojo;
		}
	}

	public ShirtOrder updateShirtOrder(Integer customerId) {
		return null;
	}
	public ShirtOrder deleteShirtOrder(Integer customerId) {
		return null;
	}
}
