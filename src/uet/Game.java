package uet;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import uet.control.Menu;
import uet.control.Move;
import uet.entities.Entity;
import uet.entities.animal.Animal;
import uet.entities.animal.Bomber;
import uet.entities.block.Bomb;
import uet.entities.block.Portal;
import uet.graphics.Sprite;

import static uet.control.Menu.*;
import static uet.entities.block.Portal.*;
import static uet.levels.NextLevel.*;
import static uet.utility.SoundManager.updateSound;

import java.util.ArrayList;
import java.util.List;

public class Game extends Application {

    /**
     * The default size of the window
     * H: 480px W: 800px
     */
    public static final int WIDTH = 25;
    public static final int HEIGHT = 15;
    public static int _width = 0;
    public static int _height = 0;
    public static int _level = 1;

    public static final List<Entity> block = new ArrayList<>(); //Contains fixed entities
    public static List<Animal> enemy = new ArrayList<>();       //Contains enemy entities
    public static int[][] idObjects;    //Two-dimensional array is used to test paths
    public static int[][] listKill;     //Array containing dead positions
    public static Animal player;
    public static boolean running;
    public static ImageView authorView;

    private GraphicsContext gc;
    private Canvas canvas;

    private int frame = 1;
    private long lastTime;

    public static Stage mainStage = null;


    @Override
    public void start(Stage stage) {
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        canvas.setTranslateY(32);
        gc = canvas.getGraphicsContext2D();
        Image author = new Image("images/bg.png");
        authorView = new ImageView(author);
        authorView.setX(-400);
        authorView.setY(-208);
        authorView.setScaleX(0.5);
        authorView.setScaleY(0.5);
        Group root = new Group();
        root.getChildren().add(authorView);
        root.getChildren().add(canvas);
        Menu.createMenu(root);

        Scene scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            if (player.isLife())
                switch (event.getCode()) {
                    case UP:
                        Move.up(player);
                        break;
                    case DOWN:
                        Move.down(player);
                        break;
                    case LEFT:
                        Move.left(player);
                        break;
                    case RIGHT:
                        Move.right(player);
                        break;
                    case SPACE:
                        Bomb.putBomb();
                        break;
                }
        });

        stage.setScene(scene);
        stage.setTitle("Bomberman");
        Image icon = new Image("images/ttsalpha4.0@0.5x.png");
        stage.getIcons().add(icon);
        mainStage = stage;
        mainStage.show();

        lastTime = System.currentTimeMillis();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (running) {
                    render();
                    update();
                    time();
                    updateMenu();
                }
            }
        };
        timer.start();

        player = new Bomber(1, 1, Sprite.player_right_2.getFxImage());
        player.setLife(false);
    }

    public void update() {
        block.forEach(Entity::update);
        enemy.forEach(Entity::update);
        player.update();

        player.setCountToRun(player.getCountToRun() + 1);
        if (player.getCountToRun() == 4) {
            Move.checkRun(player);
            player.setCountToRun(0);
        }

        for (Animal a : enemy) {
            a.setCountToRun(a.getCountToRun() + 1);
            if (a.getCountToRun() == 8) {
                Move.checkRun(a);
                a.setCountToRun(0);
            }
        }

        if (enemy.size() == 0 && !isPortal && !wait) {
            Entity portal = new Portal(_width - 2, _height - 2, Sprite.portal.getFxImage());
            block.add(portal);
            if (player.getX() / 32 == portal.getX() / 32 && player.getY() / 32 == portal.getY() / 32) {
                wait = true;
                waitingTime = System.currentTimeMillis();
            }
        }
        waitToLevelUp();
        updateSound();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        block.forEach(g -> g.render(gc));
        enemy.forEach(g -> g.render(gc));
        player.render(gc);
    }

    public void time() {
        frame++;

        long now = System.currentTimeMillis();
        if (now - lastTime > 1000) {
            lastTime = System.currentTimeMillis();
            mainStage.setTitle("Bomberman Game | " + frame + " frame");
            frame = 0;

            time.setText("Time: " + timeNumber);
            timeNumber--;
            if (timeNumber < 0)
                player.setLife(false);
        }
    }
}
