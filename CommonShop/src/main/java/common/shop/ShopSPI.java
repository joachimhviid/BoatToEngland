package common.shop;

import shopsystem.Item;

public interface ShopSPI {
    void addItemToShop(String itemName, int price);
    void removeItemFromShop(Item item);
    void openShop();

}
