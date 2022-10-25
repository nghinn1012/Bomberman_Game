package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;


public abstract class AnimatedEntity extends Entity {
    protected int animate = 0;
    protected final int MAX_ANIMATE = 7500; //save the animation status and dont let this get too big
    protected int desX = x;
    protected int desY = y;
    protected int speed;
    protected int left = 0;
    protected int right = 0;
    protected int up = 0;
    protected int down = 0;

    public AnimatedEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        alive = true;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void goLeft() {
        desX = x - speed;
    }

    public void goRight() {
        desX = x + speed;
    }
    public void goUp() {
        desY = y - speed;
    }

    public void goDown() {
        desY = y + speed;
    }

    public void move() {
        x = desX;
        y = desY;
    }

    public void stay() {
        desX = x;
        desY = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(desX, desY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    protected void animate() {
        if(animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0; //reset animation
        }
    }

}
