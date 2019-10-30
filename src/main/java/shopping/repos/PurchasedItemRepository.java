package shopping.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.model.PurchasedItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchasedItemRepository extends JpaRepository<PurchasedItem, Long> {
    List<PurchasedItem> findAll();
    Optional<PurchasedItem> findByPurchasedItemId(Integer id);
}
