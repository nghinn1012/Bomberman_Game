package uet.oop.bomberman.entities.liveEntities.enemies.algorithm;

import java.util.ArrayDeque;
import java.util.Queue;

public class BFS {

    private static final boolean DEBUG = false;

    // trả về một mảng point thể hiện đường đi trên map từ pos đến des
    public static Point[] findPath(final int[][] map,
                            final Point position,
                            final Point destination) {
        boolean check = isOutOfMap(map, position.x, position.y)
        || isOutOfMap(map, destination.x, destination.y)
        || isBlocked(map, position.x, position.y)
        || isBlocked(map, destination.x, destination.y);
        if (check) {
            return null;
        }

        Queue<Point> queue1 = new ArrayDeque<>();
        Queue<Point> queue2 = new ArrayDeque<>();

        map[position.y][position.x] = -1;
        queue1.add(position);

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
                    if (isBlocked(map, x, y)) {
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

    // check xem những toạ độ đó có hợp lệ k
    private static boolean isOutOfMap(final int[][] map,
            final int x,
            final int y) {
        return x < 0 || y < 0 || y >= map[0].length || x >= map[0].length;
    }

    // check xem vị trí đó có chứa giá trị gì
    private static boolean isBlocked(final int[][] map, final int x, final int y) {
        final int i = map[y][x];
        return i < 0 || i == 1;
    }

    private static Point[] arrived(final int[][] map, final int size, final Point p) {
        final Point[] optimalPath = new Point[size];

        computeSolution(map, p.x, p.y, size, optimalPath);

        resetMap(map);

        return optimalPath;
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
            final Point[] optimalPath) {
        if (isOutOfMap(map, x, y)
                || map[y][x] == 0
                || map[y][x] != -stepCount) {
            return;
        }

        final Point p = new Point(x, y);

        optimalPath[stepCount - 1] = p;

        lookAround(map, p, (x1, y1) -> computeSolution(map, x1, y1, stepCount - 1, optimalPath));
    }

    private static void lookAround(final int[][] map,final Point p, final Callback callback) {
        // callback.look(map, p.x, p.y + 1);
        // callback.look(map, p.x, p.y + 1);
        // callback.look(map, p.x, p.y - 1);
        // callback.look(map, p.x, p.y - 1);
    // kiểm tra bốn point xung quanh
        callback.look(map, p.x + 1, p.y);
        callback.look(map, p.x - 1, p.y);
        callback.look(map, p.x, p.y + 1);
        callback.look(map, p.x, p.y - 1);
    }

    private interface Callback {
        default void look(final int[][] map, final int x, final int y) {
            if (isOutOfMap(map, x, y)) {
                return;
            }
            onLook(x, y);
        }

        void onLook(int x, int y);
    }
}