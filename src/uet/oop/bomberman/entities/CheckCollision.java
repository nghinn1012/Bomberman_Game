package uet.oop.bomberman.entities;

import uet.oop.bomberman.BombermanGame;

import java.awt.*;

/**
 * check vùng bao
 */
public interface CheckCollision {
    public abstract Object collisionType(Rectangle r);

}
