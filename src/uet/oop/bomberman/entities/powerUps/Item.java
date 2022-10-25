package uet.oop.bomberman.entities.powerUps;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.fixed.StillEntity;

public abstract class Item extends StillEntity {
    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
    }
}
