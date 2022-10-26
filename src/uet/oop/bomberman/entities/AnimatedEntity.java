package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import javafx.scene.image.Image;

/**
 * entity có hiệu ứng hoạt hình
 */

public abstract class AnimatedEntity extends Entity {
    protected int animate = 0;
    protected final int MAX_VAL = 7500; // max của trạng thái load ảnh
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

    // tạo ra một cái vùng hình chữ nhật, đỉnh trên cùng bên trái là toạ độ đang đứng
    public Rectangle getBounds() {
        return new Rectangle(desX, desY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    // tạo hiệu ứng hoạt hình về 4 hướng
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
        desX = x + x;
        desY = y + y;
    }

    // ngăn chặn việc load hiệu ứng cao quá -> bị giật
    protected void animate() {
        if (animate < MAX_VAL) {
            animate++;
        } else {
            animate = 0; // lớn hơn max thì reset
        }
    }

}
