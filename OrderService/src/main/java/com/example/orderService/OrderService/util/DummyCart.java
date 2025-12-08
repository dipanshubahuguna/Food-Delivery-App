package com.example.orderService.OrderService.util;

import java.util.Arrays;

public class DummyCart {
    public static Cart createDummyCart() {
        CartItem cartItem1 = new CartItem();
        cartItem1.setCartItemId(1l);
        cartItem1.setMenuItemId(1l);
        cartItem1.setRestaurantId(1l);
        cartItem1.setItemName("Idli");
        cartItem1.setItemPrice(20d);
        cartItem1.setItemQuantity(10l);

        CartItem cartItem2 = new CartItem();
        cartItem2.setCartItemId(1l);
        cartItem2.setMenuItemId(2l);
        cartItem2.setRestaurantId(1l);
        cartItem2.setItemName("Dosa");
        cartItem2.setItemPrice(40d);
        cartItem2.setItemQuantity(5l);


        return new Cart(1l,1l,400d, Arrays.asList(cartItem1,cartItem2));
    }
}
