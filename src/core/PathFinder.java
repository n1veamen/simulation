package core;

import entityPack.CreaturePack.Herbivore;
import entityPack.CreaturePack.Predator;
import entityPack.Entity;
import entityPack.Grass;

import java.util.*;

public class PathFinder {

    public Point findNearestEntity(Entity entity, Entity targetEntity) {
        Point target = new Point(0,0);
        int x = -1;
        int y = -1;

        int[] start = entity.getCoordinate();
        if (targetEntity instanceof Grass) {
            int tempDistanse = 10000;
            ArrayList<Grass> grassArrayList = GameMap.getAllGrass();
            for (Grass n : grassArrayList) {
                int[] grassCoordinate = n.getCoordinate();
                int grassDistance = Math.abs(grassCoordinate[0] + grassCoordinate[1]);
                if (tempDistanse > grassDistance) {
                    tempDistanse = grassDistance;
                    x = grassCoordinate[0];
                    y = grassCoordinate[1];
                }
            }
        }
        if (targetEntity instanceof Herbivore) {
            int tempDistanse = 10000;
            ArrayList<Herbivore> HebrivoreArrayList = GameMap.getAllHerbivore();
            for (Herbivore n : HebrivoreArrayList) {
                int[] grassCoordinate = n.getCoordinate();
                int grassDistance = Math.abs(grassCoordinate[0] + grassCoordinate[1]);
                if (tempDistanse > grassDistance) {
                    tempDistanse = grassDistance;
                    x = grassCoordinate[0];
                    y = grassCoordinate[1];
                }
            }
        }

        target.setCoordinate(x, y);
        return target;
    }

    public LinkedList<Point> BFS(Point start, Point target) {
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
                    if (Arrays.equals(next.getCoordinate(), n.getCoordinate())) {
                        //занято, проверено
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
