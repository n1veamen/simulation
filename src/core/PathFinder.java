package core;

import entityPack.Entity;

import java.util.*;

public class PathFinder {

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
