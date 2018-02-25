package shopping.dao;

import shopping.model.ShirtOrder;

import java.util.List;

public interface IShirtOrderDAO {
	
	List<ShirtOrder> getAllShirtOrders();
	ShirtOrder getShirtOrderById(Integer shirtOrderId);
	ShirtOrder saveOrUpdateShirtOrder(ShirtOrder shirtOrder);
//	ShirtOrder delete(ShirtOrder shirtOrder);
}
