package shopping.dao;

import shopping.model.ShirtOrder;
import shopping.model.ShirtOrderInfo;

import java.util.List;

public interface IShirtOrderDAO {
	
	List<ShirtOrderInfo> getAllShirtOrders();
	ShirtOrder getShirtOrderById(Integer shirtOrderId);
	ShirtOrder saveOrUpdateShirtOrder(ShirtOrder shirtOrder);
//	ShirtOrder delete(ShirtOrder shirtOrder);
}
