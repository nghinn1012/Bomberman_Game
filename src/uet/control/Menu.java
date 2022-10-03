package uet.control;

import static uet.Game.*;

import java.time.Duration;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uet.graphics.Sprite;
import uet.levels.*;
import uet.utility.SoundManager;

public class Menu {
    private static ImageView statusGameButton, newGameButton, helpGameButton;
    public static Text level, bomb, time;
    public static int bombNumber = 20, timeNumber = 120;
    public static boolean isHidden;
    public static GraphicsContext gcx;
    public static Canvas cv;

    public static void createMenu(Group root) {
        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(416);
        level.setY(20);
        bomb = new Text("Bombs: 20");
        bomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bomb.setFill(Color.WHITE);
        bomb.setX(512);
        bomb.setY(20);
        time = new Text("Times: 120");
        time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        time.setFill(Color.WHITE);
        time.setX(608);
        time.setY(20);

        Image newGame = new Image("images/newGame.png");
        newGameButton = new ImageView(newGame);
        newGameButton.setX(250);
        newGameButton.setY(50);
        newGameButton.setScaleX(0.5);
        newGameButton.setScaleY(0.5);

        Image statusGame = new Image("images/newGame.png");
        statusGameButton = new ImageView(statusGame);
        statusGameButton.setX(-75);
        statusGameButton.setY(-10);
        statusGameButton.setScaleX(0.5);
        statusGameButton.setScaleY(0.5);
        statusGameButton.setVisible(false);

        Image helpGame = new Image("images/playGame.png");
        helpGameButton = new ImageView(statusGame);
        helpGameButton.setX(400);
        helpGameButton.setY(50);
        helpGameButton.setScaleX(0.5);
        helpGameButton.setScaleY(0.5);
        isHidden = true;

        Pane pane = new Pane();
        pane.getChildren().addAll(level, bomb, time, statusGameButton);
        pane.setMinSize(800, 32);
        pane.setMaxSize(800, 480);
        pane.setStyle("-fx-background-color: #353535");
        root.getChildren().add(pane);
        root.getChildren().add(newGameButton);
        newGameButton.setOnMouseClicked(event -> {
            statusGameButton.setVisible(true);
            newGameButton.setVisible(false);
            if (!player.isLife()) {
                new Level1();
                running = true;
            }
        });

        statusGameButton.setOnMouseClicked(event -> {
            if (running) {
                running = !running;
                SoundManager.title_screen.stop();
            } else {
                running = true;
                SoundManager.title_screen.start();
            }
            updateMenu();
        });

        helpGameButton.setOnMouseClicked(event -> {
                
        });
    }

    public static void updateMenu() {
        level.setText("Level: " + _level);
        bomb.setText("Bombs: " + bombNumber);

        if (running) {
            Image pauseGame = new Image("images/pauseGame.png");
            statusGameButton.setImage(pauseGame);
        } else {
            Image playGame = new Image("images/playGame.png");
            statusGameButton.setImage(playGame);
        }
    }
}
