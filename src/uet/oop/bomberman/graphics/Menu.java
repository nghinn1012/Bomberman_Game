package uet.oop.bomberman.graphics;


import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static uet.oop.bomberman.BombermanGame.*;

public class Menu {
    private static ImageView statusGame;
    public static Text _level, bomb, time;
    public static int bomb_number = 20, time_number = 120; // the number of bomb is 20 and the time limit is 120 seconds
    public static Image pauseGame, playGame;
    public static Pane pane;

    public static void createMenu(Group root) { // Create a menu
        // _level = new Text("Level: 1");
        // _level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        // _level.setFill(Color.WHITE);
        // _level.setX(416);
        // _level.setY(20);
        // bomb = new Text("Bombs: 20");
        // bomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        // bomb.setFill(Color.WHITE);
        // bomb.setX(512);
        // bomb.setY(20);
        // time = new Text("Times: 120");
        // time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        // time.setFill(Color.WHITE);
        // time.setX(608);
        // time.setY(20);

        Image newGame = new Image("images/startButton.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(-75);
        statusGame.setY(-10);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);

        pane = new Pane();
        pane.getChildren().addAll(statusGame);
        pane.setMaxSize(1000, 32);
        pane.setMinSize(1000, 32);

        root.getChildren().add(pane);

        statusGame.setOnMouseClicked(event -> { 
                                                
               level = 5;
               load(level);
               running = true;
        });

    }

    public static void updateMenu() { // Update menu
        // _level.setText("Level: " + BombermanGame.level);
        // bomb.setText("Bombs: " + bomb_number);
}
}