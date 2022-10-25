package uet.oop.bomberman.entities.fixed;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.fixed.StillEntity;

public class Portal extends StillEntity {
    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
    }

    @Override
    public void update() {

    }
}
