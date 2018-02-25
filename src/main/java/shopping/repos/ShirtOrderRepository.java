package shopping.repos;

import org.springframework.data.repository.CrudRepository;
import shopping.model.ShirtOrder;

public interface ShirtOrderRepository extends CrudRepository<ShirtOrder, Long> {
}
