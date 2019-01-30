package shopping.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import shopping.model.ShirtRef;

import java.util.List;

@Repository
public interface ShirtRefRepository extends CrudRepository<ShirtRef, Long> {
    List<ShirtRef> findAll();
    ShirtRef findByShirtRefId(Integer id);
    ShirtRef findByShirtName(String shirtName);
}
