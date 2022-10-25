package uet.oop.bomberman.entities.liveEntities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.CollisionTypeCheck;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.liveEntities.enemies.algorithm.BFS;
import uet.oop.bomberman.entities.liveEntities.enemies.algorithm.Point;
import uet.oop.bomberman.graphics.Sprite;
import static uet.oop.bomberman.BombermanGame.map;
import static uet.oop.bomberman.BombermanGame.myBomber;

import java.awt.*;

public class Minvo extends Enemy implements CollisionTypeCheck {
    //phục vụ việc lưu tọa độ tránh lặp lại quá nhiều lần tìm BFS gây lag
    private int prevBombX = 0;
    private int prevBombY = 0;
    private Point[] path;
    private boolean changed = false;
    //lưu lại biến BFS cũ
    private int prevI = 0;
    private int direction;
    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
        setSpeed(1);
        path = BFS.findPath(BombermanGame.map, new Point(this.x / Sprite.SCALED_SIZE,
                        this.y / Sprite.SCALED_SIZE),
                new Point(BombermanGame.myBomber.getX() / Sprite.SCALED_SIZE,
                        BombermanGame.myBomber.getX() / Sprite.SCALED_SIZE));
        generateDirection();
    }

    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, left ++, 18).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, right ++, 18).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, up ++, 18).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, down ++, 18).getFxImage();
    }

    //interface sau này cải tiến
    @Override
    public Object collisionType(Rectangle r){
        for(Entity e : BombermanGame.stillObjects){
            Rectangle r2 = e.getBounds();
            if(r.intersects(r2)){
                return e;
            }
        }
        return r;
    }

    @Override
    public void generateDirection() {
        Point player = new Point(BombermanGame.myBomber.getX() / Sprite.SCALED_SIZE,
                BombermanGame.myBomber.getY() / Sprite.SCALED_SIZE);
        Point minvo = new Point(this.x / Sprite.SCALED_SIZE,
                this.y / Sprite.SCALED_SIZE);
        if( !(BombermanGame.myBomber.getX() / Sprite.SCALED_SIZE == prevBombX &&
                BombermanGame.myBomber.getY() / Sprite.SCALED_SIZE == prevBombY)) {
            path = BFS.findPath(BombermanGame.map, minvo, player);
//            DEBUG: System.out.println(player.x + " " + player.y + "lala" + minvo.x + " " + minvo.y);
//            for(int i = 0; i < 13; i ++) {
//                for(int j = 0; j < 31; j ++) {
//                    System.out.print(map[i][j] + ",");
//                }
//                System.out.println();
//            }

            if(path == null) {
//                DEBUG: System.out.println("loi null");
            } else {
                //DEBUG:
//                for (Point point : path) {
//                    System.out.println(point.x + ", " + point.y);
//                }
//                DEBUG: System.out.println("end");
            }
            prevBombX = BombermanGame.myBomber.getX() / Sprite.SCALED_SIZE;
            prevBombY = BombermanGame.myBomber.getY() / Sprite.SCALED_SIZE;
            changed = true;
            direction();
        } else {
            changed = false;
            direction();
        }
    }

    public void direction () {
        double xConverted = (double) Math.round(((double) this.x / Sprite.SCALED_SIZE) * 100) / 100;
        double yConverted = (double) Math.round(((double) this.y / Sprite.SCALED_SIZE) * 100) / 100;
        if(BombermanGame.myBomber.isAlive()) {
            if(changed) {
                prevI = 0;
            } else {
                if (path == null) {
                    super.stay();
                    direction = 4;
                } else if (prevI == path.length) {
                    if ((double) BombermanGame.myBomber.getX() / Sprite.SCALED_SIZE - (double) x / Sprite.SCALED_SIZE < 0)
                        direction = 0;
                    if ((double) BombermanGame.myBomber.getX() / Sprite.SCALED_SIZE - (double) x / Sprite.SCALED_SIZE > 0)
                        direction = 1;
                    if ((double) BombermanGame.myBomber.getY() / Sprite.SCALED_SIZE - (double) y / Sprite.SCALED_SIZE < 0)
                        direction = 2;
                    if ((double) BombermanGame.myBomber.getY() / Sprite.SCALED_SIZE - (double) y / Sprite.SCALED_SIZE > 0)
                        direction = 3;
                    // lỗi không đi được trọn vẹn vào ô kill nhân vật
                } else if (path != null) {
                    double xPath = (double) Math.round((double) path[prevI].x * 100) / 100;
                    double yPath = (double) Math.round((double) path[prevI].y * 100) / 100;
                    //                System.out.println(path[prevI].x + " " + path[prevI].y);
                    //                System.out.println(BombermanGame.myBomber.getX() / Sprite.SCALED_SIZE + " " +
                    //                        BombermanGame.myBomber.getY() / Sprite.SCALED_SIZE + "lala");
                    //                System.out.println((double) this.getX() / Sprite.SCALED_SIZE + " " +
                    //                        this.getY() / Sprite.SCALED_SIZE + "lala");
//                    System.out.println((double) (this.x / Sprite.SCALED_SIZE) + " " +
//                            yConverted + "lala");
                    //                System.out.println("prevI" + prevI);

                    //                if (path[prevI].x - xConverted == 0 && path[prevI].y - yConverted == 1) {
                    //                    direction = 3;
                    //                    System.out.println("one");
                    //                } else if (path[prevI].x - xConverted == 0 && path[prevI].y - yConverted == -1) {
                    //                    direction = 2;
                    //                    System.out.println("two");
                    //                } else if (path[prevI].x - xConverted == -1 && path[prevI].y - yConverted == 0) {
                    //                    direction = 0;
                    //                    System.out.println("three");
                    //                } else if (path[prevI].x - xConverted == 1 && path[prevI].y - yConverted == 0) {
                    //                    direction = 1;
                    //                    System.out.println("four");
                    //                } else if (path[prevI].x - xConverted == 0 && path[prevI].y - yConverted == 0) {
                    //                    prevI ++;
                    //                    System.out.println("five");
                    //                }

                    if (xPath - xConverted == 0 && yPath - yConverted > 0) {
                        direction = 3;
//                        System.out.println("one");
                    } else if (xPath - xConverted == 0 && yPath - yConverted < 0) {
                        direction = 2;
//                        System.out.println("two");
                    } else if (xPath - xConverted < 0 && yPath - yConverted == 0) {
                        direction = 0;
//                        System.out.println("three");
                    } else if (xPath - xConverted > 0 && yPath - yConverted == 0) {
                        direction = 1;
//                        System.out.println("four");
                    } else if (xPath - xConverted == 0 && yPath - yConverted == 0) {
                        direction = 4;
                        prevI++;
//                        System.out.println("five");

                    }
                }
            }
        } else {
            restartEnemy();
        }
    }

    @Override
    public void update() {
        generateDirection();
        if(! BombermanGame.myBomber.isAlive()) {
            restartEnemy();
//            System.out.println(this.x / Sprite.SCALED_SIZE + " " + this.y / Sprite.SCALED_SIZE);
        } else {
//        direction = 0;
            //System.out.println(direction);
            if (direction == 0) goLeft();
            if (direction == 1) goRight();
            if (direction == 2) goUp();
            if (direction == 3) goDown();
            if (direction == 4) super.stay();

            if (isAlive()) {

            } else if (animated ++ < 30) {
                super.stay();
                img = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2,
                        Sprite.mob_dead3, animate, 15).getFxImage();
            } else
                BombermanGame.enemies.remove(this);
        }
    }

    @Override
    public void restartEnemy() {
        super.stay();
        this.x = startX * Sprite.SCALED_SIZE;
        this.y = startY * Sprite.SCALED_SIZE;
    }

}
