package uet.oop.bomberman.entities.liveEntities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

import uet.oop.bomberman.audio.Music;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends AnimatedEntity {
    private int radius;
    private KeyCode direction = null;
    private int timeAf = 0;
    // số bomb còn lại
    private int bombO;
    private boolean placeBomb = false;
    private final List<Bomb> bombs = new ArrayList<>();
    private int power;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        layer = 1;
        speed = 2;
        bombO = 1;
        power = 1;
        radius = 1;
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
        if (placeBomb) {
            placeBomb();
            //âm thanh đặt bom
            Music placeSound = new Music(Music.placebomb);
            placeSound.play();
        }
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (!bomb.isAlive()) {
                bombs.remove(bomb);
                bombO++;
            }
        }
        //animate();
        if(!isAlive()) {
            timeAf++;
            die();
        }
    }

    public void handleKeyPressedEvent(KeyCode keyCode) {

        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT
                || keyCode == KeyCode.UP || keyCode == KeyCode.DOWN) {
            this.direction = keyCode;
        }
        if (keyCode == KeyCode.SPACE) {
            placeBomb = true;
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
            placeBomb = false;
        }
    }

    
    // hiện hiệu ứng die
    public void die() {
        if(timeAf <= 40) {
            img = Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2,
                    Sprite.player_dead3, timeAf, 20).getFxImage();
        }

    }

    // dịch chuyển 4 phía
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

    public void placeBomb() {
        if (bombO > 0) {
            int xBom = (int) Math.round((x + 6) / (double) Sprite.SCALED_SIZE);
            int yBom = (int) Math.round((y + 6) / (double) Sprite.SCALED_SIZE);
            for (Bomb bomb : bombs) {
                if (xBom * Sprite.SCALED_SIZE == bomb.getX() && yBom * Sprite.SCALED_SIZE == bomb.getY()) return;
            }
            Bomb check = new Bomb(xBom, yBom, Sprite.bomb.getFxImage(), radius);
            bombs.add(check);
            bombO--;
        }
    }

    public int getbombO() {
        return bombO;
    }

    public void setbombO(int bombO) {
        this.bombO = bombO;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Rectangle getBounds() {
        return new Rectangle(desX + 6, desY + 6, Sprite.SCALED_SIZE * 2/3, Sprite.SCALED_SIZE * 2/3);
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
