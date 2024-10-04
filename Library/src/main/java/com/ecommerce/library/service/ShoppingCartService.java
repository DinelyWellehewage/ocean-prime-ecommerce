package com.ecommerce.library.service;

import com.ecommerce.library.model.Customer;
import com.ecommerce.library.model.Product;
import com.ecommerce.library.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart addItemCart(Product product, int quantity, Customer customer);

    ShoppingCart updateItemCart(Product product,int quantity, Customer customer);

    ShoppingCart deleteItemFromCart(Product product,Customer customer);
}
