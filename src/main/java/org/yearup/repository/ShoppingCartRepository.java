package org.yearup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yearup.models.CartItem;
import org.yearup.models.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends JpaRepository<CartItem, Integer>
{
    ShoppingCart findByUserId(int userId);

    CartItem findByUserIdAndProductId(int userId, int productId);

    void deleteByUserId(int userId);
}
