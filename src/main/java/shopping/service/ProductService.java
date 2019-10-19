package shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shopping.model.Product;
import shopping.repos.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        // TODO: 2019-10-18: temp - replace with repository call
        List<Product> products = new ArrayList<>();
        products.add(buildProduct(
                1,
                "Phone XL",
                799.0,
                "A large phone with one of the best screens"
        ));
        products.add(buildProduct(
                2,
                "Phone Mini",
                699.0,
                "A great phone with one of the best cameras"
        ));
        products.add(buildProduct(
                3,
                "Phone Standard",
                299.0,
                null
        ));
        return products;
//        return productRepository.findAll();
    }

    // TODO: 2019-10-18: temp - replace with repository call
    private Product buildProduct(Integer id, String name, Double price, String description) {
        Product product = new Product(name, price, description);
        product.setProductId(id);
        return product;
    }

}
