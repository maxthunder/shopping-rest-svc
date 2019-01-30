package shopping.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shopping.model.Customer;
import shopping.model.CustomerInfo;
import shopping.model.ShirtOrder;
import shopping.model.ShirtOrderInfo;
import shopping.model.ShirtRefInfo;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ShirtOrderDAO implements IShirtOrderDAO {

	private final String GET_SHIRT_ORDER_QUERY =
					"SELECT shirt_order_id, so.customer_id, customer_name, so.shirt_ref_id, shirt_name, size, style\n" +
					"FROM shopping.shirt_order so\n" +
					"JOIN shopping.customer USING(customer_id)\n" +
					"JOIN shopping.shirt_ref USING(shirt_ref_id)\n";

	private final IBaseDAO baseDAO;

	@Autowired
	public ShirtOrderDAO(IBaseDAO baseDAO) {
		this.baseDAO = baseDAO;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ShirtOrderInfo> getAllShirtOrders() {
		List<ShirtOrderInfo> shirtOrders = new ArrayList<>();
		for (Object[] objects : (List<Object[]>) baseDAO.getListFromNativeQuery(GET_SHIRT_ORDER_QUERY)) {
			Integer shirtOrderId = (Integer) objects[0];
			Integer customerId = (Integer) objects[1];
			String customerName = (String) objects[2];
			Integer shirtRefId = (Integer) objects[3];
			String shirtRefName = (String) objects[4];
			String size = null;
			String style = null;
			if (objects[5] != null) {
				size = (String) objects[5];
			}
			if (objects[6] != null) {
				style = (String) objects[6];
			}
			shirtOrders.add(
					new ShirtOrderInfo(shirtOrderId,
							new CustomerInfo(customerId, customerName),
							new ShirtRefInfo(shirtRefId, shirtRefName, size, style))
			);
		}
		return shirtOrders;
	}

	@Override
	public ShirtOrder getShirtOrderById(Integer shirtOrderId) {
		String query = GET_SHIRT_ORDER_QUERY + "WHERE shirt_order_id = :shirtOrderId\n";

		Map<String, Object> queryParameters = new HashMap<>();
		queryParameters.put("shirtOrderId", shirtOrderId);

		Object[] results = baseDAO.getObjectArrayFromNativeQuery(query, queryParameters);

		return buildShirtOrder(results);
	}

	@Override
	public ShirtOrder saveOrUpdateShirtOrder(ShirtOrder shirtOrderToPersist) {
		ShirtOrder shirtOrder = new ShirtOrder();

		if (shirtOrderToPersist.getShirtOrderId() == null) {// new
			shirtOrder.setShirtOrderId((Integer) baseDAO.getCurrentSession().save(shirtOrderToPersist));
			shirtOrder.setCustomerId(shirtOrderToPersist.getCustomerId());
			shirtOrder.setShirtRefId(shirtOrderToPersist.getShirtRefId());

		} else {// existing
			baseDAO.saveOrUpdate(shirtOrderToPersist);
			shirtOrder = shirtOrderToPersist;
		}
		return shirtOrder;
	}

//	@Override
//	public ShirtOrder delete(ShirtOrder shirtOrder) {
//		baseDAO.getCurrentSession().delete(shirtOrder);
//		return shirtOrder;
//	}

	// objects[]: shirtOrderId, customerId, shirtRefId
	private ShirtOrder buildShirtOrder(Object[] objects) {
		ShirtOrder shirtOrder = null;

		// only load if required fields are populated.
		if (objects != null) {
			Integer shirtOrderId = (objects[0] == null) ? null : (Integer) objects[0];
			Integer customerId = (objects[1] == null) ? null : (Integer) objects[1];
			Integer shirtRefId = (objects[2] == null) ? null : (Integer) objects[2];

			if (shirtOrderId != null && customerId != null && shirtRefId != null) {
				shirtOrder = new ShirtOrder(shirtOrderId, customerId, shirtRefId);
			}
		}
		return shirtOrder;
	}

}
