package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

/**
 * đại diện cho các đối tượng tĩnh, không bị phá
 */

public abstract class Block extends Entity {
    public Block(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
