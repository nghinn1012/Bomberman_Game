package uet.oop.bomberman.entities.powerUps;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Block;

public abstract class Items extends Block {
    public Items(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1);
    }
}
