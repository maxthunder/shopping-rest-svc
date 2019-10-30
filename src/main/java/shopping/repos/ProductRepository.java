package shopping.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.model.ProductRef;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductRef, Integer> {
    List<ProductRef> findAll();
    Optional<ProductRef> findByProductRefId(Integer id);
//    Optional<Customer> findByCustomerId(Integer id);
//    Optional<Customer> findByCustomerName(String shirtName);
}
