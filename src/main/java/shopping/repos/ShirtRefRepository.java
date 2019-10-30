package shopping.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopping.model.ShirtRef;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShirtRefRepository extends JpaRepository<ShirtRef, Long> {
    List<ShirtRef> findAll();
    Optional<ShirtRef> findByShirtRefId(Integer id);
    Optional<ShirtRef> findByShirtName(String shirtName);
    Optional<ShirtRef> findByShirtRefIdAndAndShirtName(Integer id, String shirtName);
}
