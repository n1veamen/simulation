package core;
import entityPack.CreaturePack.Creature;
import entityPack.CreaturePack.Herbivore;
import entityPack.CreaturePack.Predator;
import entityPack.Entity;
import entityPack.Grass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameMap {

    private static String[][] map;

    private static ArrayList<Herbivore> herbivoresOnMap = new ArrayList();
    private static ArrayList<Predator> predatorsOnMap= new ArrayList();
    private static ArrayList<Grass> grassOnMap = new ArrayList();
    private static ArrayList<Entity> entityOnMap = new ArrayList();

    public static void addEntityToMap(Entity a) {
        entityOnMap.add(a);
    }
    public static void addEntityToMap(Grass a) {
        grassOnMap.add(a);
    }
    public static void addEntityToMap(Predator a) {
        predatorsOnMap.add(a);
    }
    public static void addEntityToMap(Herbivore a) {
        herbivoresOnMap.add(a);
    }

    public static ArrayList<Herbivore> getAllHerbivore() {
        return new ArrayList<>(herbivoresOnMap);
    }
    public static ArrayList<Predator> getAllPredator() {
        return new ArrayList<>(predatorsOnMap);
    }
    public static ArrayList<Entity> getAllEntity() {
        return new ArrayList<>(entityOnMap);
    }
    public static ArrayList<Grass> getAllGrass() {
        return new ArrayList<>(grassOnMap);
    }

    public GameMap(int[] mapSize) {
        map = new String[mapSize[0]][mapSize[1]];
        System.out.println("конструктор мапы вызван");
    }

    public static int[] getMapSize() {
        int width = map[0].length;
        int height = map.length;
        return new int[]{width, height};
    }

    public static void placeEntity(int[] coordinate, String entity) {
        map[coordinate[1]][coordinate[0]] = entity;
    }

    public static void printMap() {
        for (int i = 0; i < map.length; i++) {
            for (int k = 0; k < map[0].length; k++) {
                if (map[i][k] == null) {
                    System.out.print("⬛");
                    System.out.print(" ");
                } else {
                    System.out.print(map[i][k]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    private static List<int[]> entitiesOnMap = new ArrayList<>();

    public static boolean isFree(int[] coordinate) {
        for (int i = 0; i < entitiesOnMap.size(); i++) {
            if (Arrays.equals(coordinate, entitiesOnMap.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void addCoordinate(int[] coordinate) {
        entitiesOnMap.add(coordinate);
    }
}
