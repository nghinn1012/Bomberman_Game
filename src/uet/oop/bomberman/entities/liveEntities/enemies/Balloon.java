package uet.oop.bomberman.entities.liveEntities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.audio.MyAudioPlayer;
import uet.oop.bomberman.entities.liveEntities.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Balloon extends Enemy {
    private int direction;

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(1);
        generateDirection();
        alive = true;
    }

    @Override
    public void update() {
        if(isAlive()) {
            if (direction == 0) goLeft();
            if (direction == 1) goRight();
            if (direction == 2) goUp();
            if (direction == 3) goDown();
        } else if(animated < 30){
            animated ++;
            img = Sprite.balloom_dead.getFxImage();


        }else
            BombermanGame.enemies.remove(this);
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, left++, 18).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, right++, 18).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, up++, 18).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, right++, 18).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        generateDirection();
    }

    @Override
    public void generateDirection() {
        Random random = new Random();
        direction = random.nextInt(4);
    }

    @Override
    public void restartEnemy() {

    }
}
