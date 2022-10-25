package uet.oop.bomberman.entities.liveEntities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Bomber extends AnimatedEntity {
    private int bombRemain;
    private boolean placeBombCommand = false;
    private final List<Bomb> bombs = new ArrayList<>();
    private int radius;
    private KeyCode direction = null;
    private int timeAfterDie = 0;

    private int power;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
        setLayer(1);
        setSpeed(2);
        setBombRemain(1);
        setPower(1);
        setRadius(1);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void update() {

        if (direction == KeyCode.LEFT) {
            goLeft();
        }
        if (direction == KeyCode.RIGHT) {
            goRight();
        }
        if (direction == KeyCode.UP) {
            goUp();
        }
        if (direction == KeyCode.DOWN) {
            goDown();
        }
        if (placeBombCommand) {
            placeBomb();
            //âm thanh đặt bom
            MyAudioPlayer placeSound = new MyAudioPlayer(MyAudioPlayer.PLACE_BOMB);
            placeSound.play();
        }
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
                bombRemain++;
            }
        }
        //animate();
        if(!isAlive()) {
            timeAfterDie ++;
            die();
        }
    }

    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
            this.direction = keyCode;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBombCommand = true;
        }
    }

    public void handleKeyReleasedEvent(KeyCode keyCode) {
        if (direction == keyCode) {
            if (direction == KeyCode.LEFT) {
                img = Sprite.player_left.getFxImage();
            }
            if (direction == KeyCode.RIGHT) {
                img = Sprite.player_right.getFxImage();
            }
            if (direction == KeyCode.UP) {
                img = Sprite.player_up.getFxImage();
            }
            if (direction == KeyCode.DOWN) {
                img = Sprite.player_down.getFxImage();
            }
            direction = null;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBombCommand = false;
        }
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, left++, 20).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, right++, 20).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, up++, 20).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, down++, 20).getFxImage();
    }
    
//    public void placeBomb() {
//        if (bombRemain > 0) {
//            int xB = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);
//            int yB = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);
//            for (Bomb bomb : bombs) {
//                if (xB * Sprite.SCALED_SIZE == bomb.getX() && yB * Sprite.SCALED_SIZE == bomb.getY()) return;
//            }
//            bombs.add(new Bomb(xB, yB, Sprite.bomb.getFxImage()));
//            bombRemain--;
//        }
//    }

    public void placeBomb() {
        if (bombRemain > 0) {
            int xB = (int) Math.round((x + 4) / (double) Sprite.SCALED_SIZE);
            int yB = (int) Math.round((y + 4) / (double) Sprite.SCALED_SIZE);
            for (Bomb bomb : bombs) {
                if (xB * Sprite.SCALED_SIZE == bomb.getX() && yB * Sprite.SCALED_SIZE == bomb.getY()) return;
            }
            bombs.add(new Bomb(xB, yB, Sprite.bomb.getFxImage(), radius));
            bombRemain--;
        }
    }

    public int getBombRemain() {
        return bombRemain;
    }

    public void setBombRemain(int bombRemain) {
        this.bombRemain = bombRemain;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        if(timeAfterDie <= 45) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, timeAfterDie, 20).getFxImage();
        }

    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Rectangle getBounds() {
        return new Rectangle(desX + 4, desY + 4, Sprite.SCALED_SIZE - 12, Sprite.SCALED_SIZE * 3 / 4);
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
