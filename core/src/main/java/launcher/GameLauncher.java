package launcher;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class GameLauncher extends GameApplication {
    public static void main(String[] args) {
      System.out.println("Hello World!");
      launch(args);
    }

  @Override
  protected void initSettings(GameSettings gameSettings) {
    gameSettings.setWidth(800);
    gameSettings.setHeight(600);
    gameSettings.setTitle("Boat to England");
    gameSettings.setVersion("0.1");
  }
}
