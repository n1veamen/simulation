package core;

import entityPack.CreaturePack.Herbivore;
import entityPack.CreaturePack.Predator;
import entityPack.Entity;
import entityPack.Grass;

import java.util.*;

public class PathFinder {

    public static Point findNearestEntity(Entity entityStart) {

        Point target = new Point(0,0);
        int x = 0;
        int y = 0;

        int[] start = entityStart.getCoordinate();
        if (entityStart instanceof Herbivore) {

            if (GameMap.getAllGrass().isEmpty()) {
                return null;
            } else {
                int tempDistanse = 10000;
                ArrayList<Grass> grassArrayList = GameMap.getAllGrass();
                for (Grass n : grassArrayList) {
                    int[] grassCoordinate = n.getCoordinate();
                    int grassDistance = Math.abs(start[0] - grassCoordinate[0]) + Math.abs(start[1] - grassCoordinate[1]);
                    if (tempDistanse > grassDistance) {
                        tempDistanse = grassDistance;
                        x = grassCoordinate[0];
                        y = grassCoordinate[1];
                    }
                }
            }
        }
        if (entityStart instanceof Predator) {
            if (GameMap.getAllGrass().isEmpty()) {
                return null;
            } else {
                int tempDistanse = 10000;
                ArrayList<Herbivore> hebrivoreArrayList = GameMap.getAllHerbivore();
                for (Herbivore n : hebrivoreArrayList) {
                    int[] hebrivoreCoordinate = n.getCoordinate();
                    int hebrivoreDistance = Math.abs(start[0] - hebrivoreCoordinate[0]) + Math.abs(start[1] - hebrivoreCoordinate[1]);
                    if (tempDistanse > hebrivoreDistance) {
                        tempDistanse = hebrivoreDistance;
                        x = hebrivoreCoordinate[0];
                        y = hebrivoreCoordinate[1];
                    }
                }
            }
        }

        target.setCoordinate(x, y);
        return target;
    }

    public static LinkedList<Point> BFS(Point start, Point target) {
        Queue<Point> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        Map<Point, Point> parent = new HashMap<>();
        LinkedList<Point> path = new LinkedList<>();

        queue.offer(start);
        visited.add(start);


        while (!queue.isEmpty()) {
            Point temp = queue.poll();

            if (temp.equals(target)) {
                Point pos = temp;
                path.add(pos);
                while (!pos.equals(start)) {
                    pos = parent.get(pos);
                    path.addFirst(pos);
                }
                return path;
            }

            Point[] neighbors = {temp.up(), temp.down(), temp.left(), temp.right()};
            ArrayList<Entity> notAviable = GameMap.getAllEntity();
            for (Point next : neighbors) {
                boolean available = true;

                for (Entity n : notAviable) {
                    if (Arrays.equals(next.getCoordinate(), n.getCoordinate())
                            && !Arrays.equals(next.getCoordinate(), target.getCoordinate())) {
                        available = false;
                        break;
                    }
                }
                if (available && !visited.contains(next)) {
                    queue.offer(next);
                    visited.add(next);
                    parent.put(next, temp);
                }
            }
        }
        return null;
    }
}
