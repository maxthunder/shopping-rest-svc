package shopping;

import shopping.model.Customer;
import shopping.model.ShirtOrder;

public class TestUtils {
	public TestUtils() {
	}

	public static ShirtOrder getShirtOrder(int id) {
		ShirtOrder shirtOrder = new ShirtOrder();
		shirtOrder.setShirtOrderId(id);
		shirtOrder.setShirtName("shirtName" + id);
		shirtOrder.setSize("size" + id);
		shirtOrder.setCustomerId(id + 1);
		return shirtOrder;
	}

	public static Customer getCustomer(int id) {
		Customer customer = new Customer();
		customer.setCustomerId(id);
		customer.setCustomerName("customer" + id);
		return customer;
	}
}
