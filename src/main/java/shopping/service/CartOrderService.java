package shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.dao.ICustomerDAO;
import shopping.model.*;
import shopping.repos.CartOrderRepository;
import shopping.repos.PurchasedItemRepository;
import shopping.repos.ProductRepository;
import shopping.util.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartOrderService {

    private final ICustomerDAO customerDAO;
    private final CartOrderRepository cartOrderRepository;
    private final ProductRepository productRepository;
    private final PurchasedItemRepository purchasedItemRepository;

    @Autowired
    public CartOrderService(ICustomerDAO customerDAO, CartOrderRepository cartOrderRepository,
                            ProductRepository productRepository, PurchasedItemRepository purchasedItemRepository) {
        this.customerDAO = customerDAO;
        this.cartOrderRepository = cartOrderRepository;
        this.productRepository = productRepository;
        this.purchasedItemRepository = purchasedItemRepository;
    }

    public CartOrderExport saveCartOrder(CartOrderInfo pojo) {
        if (customerDAO.getCustomerById(pojo.getCustomerId()) == null)
            throw new ResourceNotFoundException("Customer with ID", pojo.getCustomerId());

        CartOrder model = new CartOrder(pojo.getAddress(), pojo.getCustomerId());
        CartOrder cartOrder = cartOrderRepository.saveAndFlush(model);

        List<PurchasedItem> purchasedItems = new ArrayList<>();
        pojo.getProductIds().forEach(id -> {
            Optional<ProductRef> opt = productRepository.findByProductRefId(id);
            if (!opt.isPresent())
                throw new ResourceNotFoundException("Phone with ID", id);
            PurchasedItem map = new PurchasedItem(cartOrder.getCartOrderId(), opt.get().getProductRefId());
            purchasedItems.add(purchasedItemRepository.saveAndFlush(map));
        });

        return new CartOrderExport(cartOrder, purchasedItems);
    }

}
