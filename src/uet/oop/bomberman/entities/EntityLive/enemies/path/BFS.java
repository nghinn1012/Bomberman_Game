package uet.oop.bomberman.entities.EntityLive.enemies.path;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BFS {

    private static final boolean DEBUG = false;

    public static Point[] findPath(final int[][] map,
            final Point position,
            final Point destination) {
                boolean check = notInMap(map, position.x, position.y)
                || notInMap(map, destination.x, destination.y)
                || cantMove(map, position.x, position.y)
                || cantMove(map, destination.x, destination.y);
        if (check) {
            return null;
        }

        Queue<Point> queue1 = new ArrayDeque<>();
        Queue<Point> queue2 = new ArrayDeque<>();

        queue1.add(position);

        map[position.y][position.x] = -1;

        for (int i = 2; !queue1.isEmpty(); i++) {
            if (queue1.size() >= map.length * map[0].length) {
                throw new IllegalStateException("Map overload");
            }

            for (Point point : queue1) {
                if (point.x == destination.x && point.y == destination.y) {
                    return arrived(map, i - 1, point);
                }

                final Queue<Point> finalQueue = queue2;
                final int finalStepCount = i;

                lookAround(map, point, (x, y) -> {
                    if (cantMove(map, x, y)) {
                        return;
                    }

                    Point e = new Point(x, y);

                    finalQueue.add(e);

                    map[e.y][e.x] = -finalStepCount;
                });
            }

            if (DEBUG) {
                printMap(map);
            }

            queue1 = queue2;
            queue2 = new ArrayDeque<>();
        }

        resetMap(map);
        return null;
    }

    private static boolean notInMap(final int[][] map,
            final int x,
            final int y) {
        return x < 0 || y < 0 || map.length <= y || map[0].length <= x;
    }

    private static boolean cantMove(final int[][] map, final int x, final int y) {
        final int i = map[y][x];
        return i < 0 || i == 1;
    }

    private static Point[] arrived(final int[][] map, final int size, final Point p) {
        final Point[] resPath = new Point[size];

        computeSolution(map, p.x, p.y, size, resPath);

        resetMap(map);

        return resPath;
    }

    private static void resetMap(final int[][] map) {
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                if (map[y][x] < 0) {
                    map[y][x] = 0;
                }
            }
        }
    }

    private static void printMap(final int[][] map) {
        for (final int[] r : map) {
            for (final int i : r) {
                System.out.print(i + "\t");
            }

            System.out.println();
        }

        System.out.println("****************************************");
    }

    private static void computeSolution(final int[][] map,
            final int x,
            final int y,
            final int stepCount,
            final Point[] resPath) {
        if (notInMap(map, x, y)
                || map[y][x] == 0
                || map[y][x] != -stepCount) {
            return;
        }

        final Point p = new Point(x, y);

        resPath[stepCount - 1] = p;

        lookAround(map, p, (x1, y1) -> computeSolution(map, x1, y1, stepCount - 1, resPath));
    }

    private static void lookAround(final int[][] map,
            final Point p,
            final Callback callback) {
        callback.look(map, p.x, p.y + 1);
        callback.look(map, p.x, p.y - 1);
        callback.look(map, p.x + 1, p.y);
        callback.look(map, p.x - 1, p.y);

    }

    private interface Callback {
        default void look(final int[][] map, final int x, final int y) {
            if (notInMap(map, x, y)) {
                return;
            }
            onLook(x, y);
        }

        void onLook(int x, int y);
    }

    // public static void main(String[] args) {
    // int[][] myMap =
    // {
    // {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    // {1,0,0,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    // {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    // {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
    // {1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    // {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    // {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    // {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    // {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
    // {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
    // {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    // {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    // {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    // };
    // // {
    // // {0, 0, 0, 0, 0, 0, 0, 0, 0},
    // // {0, 0, 0, 1, 0, 1, 1, 1, 1},
    // // {0, 0, 0, 1, 0, 0, 0, 0, 0},
    // // {0, 0, 0, 0, 0, 0, 1, 1, 0},
    // // {0, 0, 0, 0, 0, 1, 0, 0, 0},
    // // };

    // Point[] path = new BFS().findPath(myMap, new Point(8, 0), new Point(8, 2));
    // for (Point point : path) {
    // System.out.println(point.x + ", " + point.y);
    // }
    // }
}