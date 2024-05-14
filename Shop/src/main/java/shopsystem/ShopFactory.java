package shopsystem;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import common.shop.ShopSPI;

public class ShopFactory implements EntityFactory, ShopSPI {
   /* @Spawns("shop")
    public Entity newShop(SpawnData data) {

        return FXGL.entityBuilder(data)

    }*/

    @Override
    public void addItemToShop(String itemName, int price) {

    }

    @Override
    public void removeItemFromShop(Item item) {

    }

    @Override
    public void openShop() {

    }
}
