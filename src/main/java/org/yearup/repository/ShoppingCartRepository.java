package org.yearup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.yearup.models.CartItem;
import org.yearup.models.ShoppingCart;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<CartItem, Integer>
{
    //// changed line 14 to List
    List<CartItem> findByUserId(int userId);

    CartItem findByUserIdAndProductId(int userId, int productId);
/// added @Trasactional
    @Transactional
    void deleteByUserId(int userId);

    @Transactional
    void deleteByUserIdAndProductId(int userId, int productId);
}
