package uet.oop.bomberman.entities.fixed;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class StillEntity extends Entity {
    public StillEntity(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
