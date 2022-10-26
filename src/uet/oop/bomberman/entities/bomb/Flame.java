package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.audio.Music;
import uet.oop.bomberman.entities.CheckCollision;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.block.Brick;
import uet.oop.bomberman.entities.block.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Flame extends Entity implements CheckCollision {
    public int radius;
    private int direction;
    private int time = 0;
    private int left;
    private int right;
    private int top;
    private int down;

    public Flame(int x, int y, Image image, int direction) {
        super(x, y);
        this.img = image;
        this.direction = direction;
    }

    public Flame(int x, int y, Image image) {
        super(x, y);
        this.img = image;
        this.radius = 2;
    }

    public Flame(int x, int y) {
        super(x, y);
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    // update sau khi xong 20s
    @Override
    public void update() {
        if (time < 20) {
            time++;
            setImg();
        } else
            BombermanGame.flameList.remove(this);

    }

    // render flame ra bốn phía
    public void render_explosion() {
        Top();
        Down();
        Left();
        Right();
        create_explosion();

    }

    // xử lý về bên phải, trái, trên, dưới
    private void Right() {
        for (int i = 0; i < radius; i++) {
            // tạo biên hình cn
            Rectangle ex_right = new Rectangle(x + Sprite.SCALED_SIZE * (i + 1), y, Sprite.SCALED_SIZE,
                    Sprite.SCALED_SIZE);
            // nếu va chạm với wall thì dừng, nếu là brick thì +1 (phá) xong dừng
            if (collisionType(ex_right) instanceof Wall) {
                right = i;
                return;
            } else if (collisionType(ex_right) instanceof Brick) {
                right = i + 1;
                return;
            }
            right = i + 1;
        }
    }

    private void Left() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_left = new Rectangle(x - Sprite.SCALED_SIZE * (i + 1), y, Sprite.SCALED_SIZE,
                    Sprite.SCALED_SIZE);
            if (collisionType(ex_left) instanceof Wall) {
                left = i;
                return;
            } else if (collisionType(ex_left) instanceof Brick) {
                left = i + 1;
                return;
            }
            left = i + 1;
        }
    }

    private void Top() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_top = new Rectangle(x, y - Sprite.SCALED_SIZE * (i + 1), Sprite.SCALED_SIZE,
                    Sprite.SCALED_SIZE);
            if (collisionType(ex_top) instanceof Wall) {
                top = i;
                return;
            } else if (collisionType(ex_top) instanceof Brick) {
                top = i + 1;
                return;
            }
            top = i + 1;
        }
    }

    private void Down() {
        for (int i = 0; i < radius; i++) {
            Rectangle ex_down = new Rectangle(x, y + Sprite.SCALED_SIZE * (i + 1), Sprite.SCALED_SIZE,
                    Sprite.SCALED_SIZE);
            if (collisionType(ex_down) instanceof Wall) {
                down = i;
                return;
            } else if (collisionType(ex_down) instanceof Brick) {
                down = i + 1;
                return;
            }
            down = i + 1;
        }
    }

    @Override
    // kiểm tra xem có giao biên không
    public Object collisionType(Rectangle rec) {
        for (Entity entity : BombermanGame.stillObjects) {
            Rectangle rec2 = entity.getBounds();
            if (rec.intersects(rec2)) {
                return entity;
            }
        }
        return rec;
    }

     // xử lý nổ
     private void create_explosion() {
        Flame f = new Flame(x, y, Sprite.bomb_exploded.getFxImage(), 0);
        BombermanGame.flameList.add(f);
        for (int i = 0; i < left; i++) {
            Flame temp = new Flame(x - Sprite.SCALED_SIZE * (i + 1), y);
            // là cái cuối thì render last
            if (i == left - 1) {
                temp.img = Sprite.explosion_horizontal_left_last.getFxImage();
                temp.direction = 3;
            } else {
                temp.img = Sprite.explosion_horizontal.getFxImage();
                temp.direction = 1;
            }
            BombermanGame.flameList.add(temp);
        }

        for (int i = 0; i < right; i++) {
            Flame temp = new Flame(x + Sprite.SCALED_SIZE * (i + 1), y);
            // là cái cuối thì render last
            if (i == right - 1) {
                temp.img = Sprite.explosion_horizontal_right_last.getFxImage();
                temp.direction = 2;
            } else {
                temp.img = Sprite.explosion_horizontal.getFxImage();
                temp.direction = 1;
            }
            BombermanGame.flameList.add(temp);
        }

        for (int i = 0; i < top; i++) {
            Flame temp = new Flame(x, y - Sprite.SCALED_SIZE * (i + 1));
            // là cái cuối thì render last
            if (i == top - 1) {
                temp.img = Sprite.explosion_vertical_top_last.getFxImage();
                temp.direction = 5;
            } else {
                temp.img = Sprite.explosion_vertical.getFxImage();
                temp.direction = 4;
            }
            BombermanGame.flameList.add(temp);
        }

        for (int i = 0; i < down; i++) {
            Flame temp = new Flame(x, y + Sprite.SCALED_SIZE * (i + 1));
            // là cái cuối thì render last
            if (i == right - 1) {
                temp.img = Sprite.explosion_vertical_down_last.getFxImage();
                temp.direction = 6;
            } else {
                temp.img = Sprite.explosion_vertical.getFxImage();
                temp.direction = 4;
            }
            BombermanGame.flameList.add(temp);
        }
    }


    // xử lý hiệu ứng cho từng phần
    private void setImg() {
        switch (direction) {
            case 0:
                img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                        Sprite.bomb_exploded2, time, 20).getFxImage();
                break;
            case 1:
                img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1,
                        Sprite.explosion_horizontal2, time, 20).getFxImage();
                break;
            case 2:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last,
                        Sprite.explosion_horizontal_right_last1, Sprite.explosion_horizontal_right_last2, time, 20)
                        .getFxImage();
                break;
            case 3:
                img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2, time, 20).getFxImage();
                break;
            case 4:
                img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1,
                        Sprite.explosion_vertical2, time, 20).getFxImage();
                break;
            case 5:
                img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,
                        Sprite.explosion_vertical_top_last2, time, 20).getFxImage();
                break;
            case 6:
                img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1,
                        Sprite.explosion_vertical_down_last2, time, 20).getFxImage();
                break;
        }
    }

}
