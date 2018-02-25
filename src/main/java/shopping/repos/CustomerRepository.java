package shopping.repos;

import org.springframework.data.repository.CrudRepository;
import shopping.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
