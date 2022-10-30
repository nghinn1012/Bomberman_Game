package uet.oop.bomberman.entities.EntityLive.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Doll extends Enemy{
    private int direction;

    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(3);
        generateDirection();
        alive = true;
    }

    @Override
    public void update() {
        if(isAlive()) {
            if (direction == 0) goLeft();
            if (direction == 1) goRight();
        } else if(animated < 30){
            animated ++;
            img = Sprite.doll_dead.getFxImage();


        }else
            BombermanGame.enemies.remove(this);
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, left++, 18).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, right++, 18).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        generateDirection();
    }

    @Override
    public void generateDirection() {
        Random random = new Random();
        direction = random.nextInt(2);
    }

    @Override
    public void restartEnemy() {

    }
}
