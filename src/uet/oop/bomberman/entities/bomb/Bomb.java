package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.audio.Music;
import uet.oop.bomberman.entities.AnimatedEntity;
import uet.oop.bomberman.entities.liveEntities.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public class Bomb extends AnimatedEntity {
    private int time_count = 0;
    int radius;
    
    // constructor bomb bình thường
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(2);
        this.radius = 1;
    }

    // bomb lúc nhận thêm vật phẩm
    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        setLayer(2);
        this.radius = radius;
    }

    @Override
    public void update() {
        if (time_count == 120) {
            explodeUpgrade();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, time_count, 60).getFxImage();
    }

    // hiệu ứng flame sau khi bùm
    public void explode() {
        Flame flame = new Flame(x, y);
        flame.render_explosion();
        Music explodesound = new Music(Music.EXPLOSION);
        explodesound.play();
        alive = false;
    }

    // hiệu ứng flame sau khi bùm
    public void explodeUpgrade() {
        Flame flame = new Flame(x, y);
        flame.setRadius(radius);
        flame.render_explosion();
        //âm thanh bom nổ
        Music explodesound = new Music(Music.EXPLOSION);
        explodesound.play();
        alive = false;
    }


    // check xem có nổ qua được
    public boolean canPass(AnimatedEntity entity) {
        Rectangle r1, r2;
        r1 = getBounds();
        if (entity instanceof Bomber) {
            Bomber bomber = (Bomber) entity;
            r2 = new Rectangle(bomber.getX() + 4, bomber.getY() + 4, Sprite.SCALED_SIZE * 3 / 4, Sprite.SCALED_SIZE * 3 / 4);
        } else {
            r2 = new Rectangle(entity.getX(), entity.getY(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        }
        return r1.intersects(r2);
    }
}