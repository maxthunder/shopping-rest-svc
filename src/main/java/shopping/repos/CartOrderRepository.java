package shopping.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.model.CartOrder;

import java.util.List;

@Repository
public interface CartOrderRepository extends JpaRepository<CartOrder, Long> {
    List<CartOrder> findAll();
}
