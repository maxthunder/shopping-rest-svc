package shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.dao.IBaseDAO;
import shopping.dao.ICustomerDAO;
import shopping.dao.IShirtOrderDAO;
import shopping.model.ShirtOrderInfo;
import shopping.util.BadRequestException;
import shopping.model.ShirtOrder;
import shopping.util.ResourceNotFoundException;

import java.util.List;

@Service
public class ShirtOrderService {

	private final IBaseDAO baseDAO;
	private final ICustomerDAO customerDAO;
	private final IShirtOrderDAO shirtOrderDAO;

	@Autowired
	public ShirtOrderService(IBaseDAO baseDAO, IShirtOrderDAO shirtOrderDAO, ICustomerDAO customerDAO) {
		this.baseDAO = baseDAO;
		this.shirtOrderDAO = shirtOrderDAO;
		this.customerDAO = customerDAO;
	}

	public List<ShirtOrderInfo> getAllShirtOrders() {
		return shirtOrderDAO.getAllShirtOrders();
	}

	public ShirtOrder getShirtOrder(Integer shirtOrderId) {
		ShirtOrder shirtOrder = shirtOrderDAO.getShirtOrderById(shirtOrderId);
		if (shirtOrder == null) {
			throw new ResourceNotFoundException("ShirtOrder at ID", shirtOrderId);
		}
		return shirtOrder;
	}

	public ShirtOrder saveShirtOrder(Integer customerId, Integer shirtRefId) {
		if (customerDAO.getCustomerById(customerId) == null) {
			throw new BadRequestException("Customer at ID");
		}
		// REVIEW: add validation for shirt ref id existence
		return shirtOrderDAO.saveOrUpdateShirtOrder(new ShirtOrder(customerId, shirtRefId));
	}

//	public ShirtOrder updateShirtOrder(Integer shirtOrderId, Integer customerId, Integer shirtRefId) {
//		// Attempt to load by shirtOrder ID, null indicates a new entry needed.
//		ShirtOrder loadedShirtOrder = shirtOrderDAO.getShirtOrderById(shirtOrderId);
//
//		// Make sure shirtOrder name is new or already existing with shirtOrder ID
//		if (loadedShirtOrder == null && shirtOrderDAO.getShirtOrderByName(shirtOrderName) != null) {
//			throw new BadRequestException("ShirtOrder at name <"+shirtOrderName+"> already exists in database.");
//		}
//
//		// Build ShirtOrder pojo
//		ShirtOrder shirtOrder = null;
//		if (loadedShirtOrder == null) {// new - build for save
//			shirtOrder = new ShirtOrder(shirtOrderId, shirtOrderName);
//
//		} else if (!loadedShirtOrder.getShirtOrderName().equals(shirtOrderName)) {// existing with changed field - build for update
//			shirtOrder = new ShirtOrder(loadedShirtOrder.getShirtOrderId(), shirtOrderName);
//		}
//
//		// saveOrUpdate if any fields have changed (shirtOrder was built)
//		return shirtOrder != null ? shirtOrderDAO.saveOrUpdateShirtOrder(shirtOrder) : loadedShirtOrder;
//	}

	// REVIEW: Add shirtOrderStatus==DELETABLE to add further protection from accidental shirtOrder deletions
	public ShirtOrder deleteShirtOrder(Integer shirtOrderId) {
		ShirtOrder shirtOrder = shirtOrderDAO.getShirtOrderById(shirtOrderId);
		if (shirtOrder == null) {
			throw new ResourceNotFoundException("Shirt order at ID", shirtOrderId);
		}
		baseDAO.delete(shirtOrder);
		return shirtOrder;
	}
}
