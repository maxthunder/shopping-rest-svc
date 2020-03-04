package shopping.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.model.Customer;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findAll();
    Optional<Customer> findByCustomerId(Integer id);
    Optional<Customer> findByCustomerName(String shirtName);
    Optional<Customer> findByCustomerIdAndCustomerName(Integer customerId, String shirtName);
}
