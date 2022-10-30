package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.Entity;

import java.util.Comparator;

public class Layer implements Comparator<Entity> {
        @Override
        public int compare(Entity obj1, Entity obj2) {
                return Integer.compare(obj2.getLayer(), obj1.getLayer());
        }
}
