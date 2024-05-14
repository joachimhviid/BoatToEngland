package shopsystem;

import com.almasb.fxgl.entity.components.ViewComponent;
import com.almasb.fxgl.ui.InGamePanel;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import common.shop.ShopSPI;

public class Shop implements ShopSPI {
    private VBox shopUI;
    private InGamePanel inGamePanel;

    public Shop(VBox shopUI) {
        shopUI = new VBox(10);
        shopUI.setAlignment(Pos.CENTER);
        shopUI.setTranslateX(100);
        shopUI.setTranslateY(100);
        Rectangle rect = new Rectangle(100, 100, Color.RED);
        //add some items to the shop with addItemToShop method
    }


    @Override
    public void addItemToShop(String itemName, int price) {
        Rectangle item = new Rectangle(100, 50, Color.RED);
        Text itemText = new Text(itemName + " - $" + price);
        ViewComponent viewComponent = new ViewComponent();
        viewComponent.addChild(item);
        HBox hBox = new HBox(10);
        viewComponent.addChild(hBox);


        //TODO: implement using the currency component to subract price from
        /*
        item.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY && playerCoins >= price) {
                // Deduct coins and add item to inventory
                playerCoins -= price;
                System.out.println("Purchased " + itemName);
            }
        });
         */
    }

    @Override
    public void removeItemFromShop(Item item) {

    }

    @Override
    public void openShop() {


    }


}
