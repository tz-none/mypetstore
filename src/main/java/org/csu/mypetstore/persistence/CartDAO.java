package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;

import java.util.List;

public interface CartDAO {
    public Cart getCartByUsername(String username);

    public void insertCartItem(CartItem cartItem, Account account);

    public void updateCart(CartItem cartItem, Account account);

    public void removeCartItem(CartItem cartItem, Account account);
}
