package shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.dao.ICustomerDAO;
import shopping.dao.ICartOrderDAO;
import shopping.dao.IPhoneRefDAO;
import shopping.model.Customer;
import shopping.model.CartOrder;
import shopping.model.CartOrderInfo;
import shopping.model.PhoneRef;
import shopping.repos.PhoneRefRepository;
import shopping.util.ResourceNotFoundException;

@Service
public class CartOrderService {

    private final ICartOrderDAO CartOrderDAO;
    private final ICustomerDAO customerDAO;
    private final IPhoneRefDAO phoneRefDAO;
//    private final PhoneRefRepository phoneRefRepository;

    @Autowired
    public CartOrderService(ICartOrderDAO CartOrderDAO, ICustomerDAO customerDAO,
                                 IPhoneRefDAO phoneRefDAO) {
        this.CartOrderDAO = CartOrderDAO;
        this.customerDAO = customerDAO;
        this.phoneRefDAO = phoneRefDAO;
    }

    public CartOrder saveCartOrder(CartOrderInfo input) {
        return CartOrderDAO.saveCartOrder(mapInputToModel(input));
    }

    private CartOrder mapInputToModel(CartOrderInfo input) {
        CartOrder model = new CartOrder();

        Customer customer = customerDAO.getCustomerById(input.getCustomerId());
        if (customer == null)
            throw new ResourceNotFoundException("Customer with ID", input.getCustomerId());
        model.setCustomerId(customer.getCustomerId());

//        input.getProductIds().forEach(id -> {
//            PhoneRef phoneRef = phoneRefDAO.getPhoneRefByName(null);
//            if (phoneRef == null)
//                throw new ResourceNotFoundException("Phone with Name", null);
//        });

        model.setAddress(input.getAddress());
        return model;
    }
}
