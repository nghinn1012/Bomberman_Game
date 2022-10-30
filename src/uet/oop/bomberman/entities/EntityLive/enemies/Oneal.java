package uet.oop.bomberman.entities.EntityLive.enemies;

import javafx.scene.image.Image;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.EntityLive.Bomber;
import uet.oop.bomberman.entities.EntityLive.enemies.Enemy;
import uet.oop.bomberman.graphics.Sprite;


import java.awt.*;
import java.util.Random;

public class Oneal extends Enemy {
    private int direction;
    Random nR = new Random();

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        layer = 1;
        generateDirection();
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, left++, 18).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, right++, 28).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, up++, 18).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, down++, 18).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        generateDirection();
    }

    @Override
    public void update() {
        generateDirection();
        speed = nR.nextInt(3);
        if (direction == 0) goLeft();
        if (direction == 1) goRight();
        if (direction == 2) goUp();
        if (direction == 3) goDown();
        if(!BombermanGame.myBomber.isAlive()) {
            restartEnemy();
        }

        if(isAlive()){

        } else if(animated < 30) {
            super.stay();
            animated++;
            img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                   Sprite.mob_dead3, animate, 10).getFxImage();
        } else
            BombermanGame.enemies.remove(this);
    }

    @Override
    public void generateDirection() {
        Bomber bomber = BombermanGame.myBomber;
        if (this.y % Sprite.SCALED_SIZE == 0 && this.x % Sprite.SCALED_SIZE == 0) {
           if (bomber.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE < 0) direction = 0;
           if (bomber.getX() / Sprite.SCALED_SIZE - x / Sprite.SCALED_SIZE > 0) direction = 1;
           if (bomber.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE < 0) direction = 2;
           if (bomber.getY() / Sprite.SCALED_SIZE - y / Sprite.SCALED_SIZE > 0) direction = 3;
        }
    }

    @Override
    public void restartEnemy() {
        super.stay();
        this.x = startX * Sprite.SCALED_SIZE;
        this.y = startY * Sprite.SCALED_SIZE;
    }
}
