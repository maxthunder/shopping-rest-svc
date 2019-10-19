package shopping.dao;

import shopping.model.CartOrder;

public interface ICartOrderDAO {
    CartOrder saveCartOrder(CartOrder CartOrder);
}
