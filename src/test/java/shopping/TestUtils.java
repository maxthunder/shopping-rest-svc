package shopping;

import shopping.model.*;

public class TestUtils {
	public TestUtils() {
	}

	// dao pojos

	public static ShirtOrder getShirtOrder(int id) {
		ShirtOrder shirtOrder = new ShirtOrder();
		shirtOrder.setShirtOrderId(id);
		shirtOrder.setCustomerId(id + 1);
		return shirtOrder;
	}

	public static Customer getCustomer(int id) {
		Customer customer = new Customer();
		customer.setCustomerId(id);
		customer.setCustomerName("customer" + id);
		return customer;
	}

	public static ShirtRef getShirtRef(Integer id) {
		return new ShirtRefBuilder()
				.id(id)
				.shirtName("name" + id)
				.size("size" + id)
				.style("style" + id)
				.build();
	}

	// output pojos

	public static ShirtOrderInfo getShirtOrderInfo(int id) {
		ShirtOrderInfo shirtOrderInfo = new ShirtOrderInfo();
		shirtOrderInfo.setShirtOrderId(id);
		shirtOrderInfo.setShirtRefInfo(new ShirtRefInfo(id, "shirtName" + id, "size" + id, "style" + id));
		shirtOrderInfo.setCustomer(getCustomerInfo(id));
		return shirtOrderInfo;
	}

	public static CustomerInfo getCustomerInfo(int id) {
		CustomerInfo customer = new CustomerInfo(id, "customer" + id);
		return customer;
	}


}
