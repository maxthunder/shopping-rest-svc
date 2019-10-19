package shopping.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shopping.model.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findAll();
    Optional<Customer> findByCustomerId(Integer id);
    Optional<Customer> findByCustomerName(String shirtName);
}
