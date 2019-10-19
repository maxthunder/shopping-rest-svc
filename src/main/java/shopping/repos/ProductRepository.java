package shopping.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shopping.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();
//    Optional<Customer> findByCustomerId(Integer id);
//    Optional<Customer> findByCustomerName(String shirtName);
}
