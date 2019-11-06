package shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.model.*;
import shopping.repos.CartOrderRepository;
import shopping.repos.CustomerRepository;
import shopping.repos.PurchasedItemRepository;
import shopping.repos.ProductRepository;
import shopping.util.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartOrderService {

    private final CustomerRepository customerRepository;
    private final CartOrderRepository cartOrderRepository;
    private final ProductRepository productRepository;
    private final PurchasedItemRepository purchasedItemRepository;

    @Autowired
    public CartOrderService(CustomerRepository customerRepository, CartOrderRepository cartOrderRepository,
                            ProductRepository productRepository, PurchasedItemRepository purchasedItemRepository) {
        this.customerRepository = customerRepository;
        this.cartOrderRepository = cartOrderRepository;
        this.productRepository = productRepository;
        this.purchasedItemRepository = purchasedItemRepository;
    }

    public CartOrderExport saveCartOrder(CartOrderInfo pojo) {
        if (!customerRepository.findByCustomerId(pojo.getCustomerId()).isPresent())
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
