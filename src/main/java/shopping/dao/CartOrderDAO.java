package shopping.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shopping.model.CartOrder;

@Repository
@Transactional
public class CartOrderDAO implements ICartOrderDAO {

    private final IBaseDAO baseDAO;

    @Autowired
    public CartOrderDAO(IBaseDAO baseDAO) {
        this.baseDAO = baseDAO;
    }

    @Override
    public CartOrder saveCartOrder(CartOrder CartOrder) {
        return (CartOrder) baseDAO.save(CartOrder);
    }
}
