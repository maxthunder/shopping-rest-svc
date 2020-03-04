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
import java.util.stream.Collectors;

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

    public List<CartOrderExport> getAllCartOrders(Integer customerId) {
        Customer customer = null;
        if (customerId != null) {
            customer = customerRepository.getOne(customerId);
            if (customer == null)
                throw new ResourceNotFoundException("Customer at ID", customerId);
        }
        List<CartOrder> cartOrders;
        if (customer != null)
            cartOrders = cartOrderRepository.findAllByCustomerId(customerId);
        else
            cartOrders = cartOrderRepository.findAll();

        return cartOrders.stream()
                .map(cartOrder -> {
                    List<PurchasedItem> items = purchasedItemRepository.findAllByCartOrderId(cartOrder.getCartOrderId());
                    return new CartOrderExport(cartOrder, items);
                })
                .collect(Collectors.toList());
    }

    public CartOrderExport saveCartOrder(CartOrderInfo pojo) {
        if (!customerRepository.findByCustomerId(pojo.getCustomerId()).isPresent())
            throw new ResourceNotFoundException("Customer with ID", pojo.getCustomerId());

        CartOrder model = new CartOrder(pojo.getAddress(), pojo.getCustomerId());
        CartOrder cartOrder = cartOrderRepository.saveAndFlush(model);

        List<PurchasedItem> purchasedItems = getPurchasedItems(cartOrder.getCartOrderId(), pojo.getProductIds());
        return new CartOrderExport(cartOrder, purchasedItems);
    }

    private List<PurchasedItem> getPurchasedItems(Integer cartOrderId, List<Integer> productIds) {
        List<PurchasedItem> purchasedItems = new ArrayList<>();
        productIds.forEach(id -> {
            Optional<ProductRef> opt = productRepository.findByProductRefId(id);
            if (!opt.isPresent())
                throw new ResourceNotFoundException("Phone with ID", id);
            PurchasedItem map = new PurchasedItem(cartOrderId, opt.get().getProductRefId());
            purchasedItems.add(purchasedItemRepository.saveAndFlush(map));
        });

        return purchasedItems;
    }

}
