package uet.oop.bomberman;

import uet.oop.bomberman.entities.Entity;

import java.util.Comparator;

public class Layer implements Comparator<Entity> {
        @Override
        public int compare(Entity o1, Entity o2) {
                return Integer.compare(o2.getLayer(), o1.getLayer());
        }
}
