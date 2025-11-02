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
    private static ArrayList<Creature> creatureOnMap = new ArrayList();

    public static void addEntityToMap(Entity e) {
        entityOnMap.add(e);

        if (e instanceof Grass) {
            grassOnMap.add((Grass) e);
        } else if (e instanceof Predator) {
            predatorsOnMap.add((Predator) e);
        } else if (e instanceof Herbivore) {
            herbivoresOnMap.add((Herbivore) e);
        } else if (e instanceof Creature) {
            creatureOnMap.add((Creature) e);
        }
    }

    public static void delEntityFromMap(Entity e) {
        entityOnMap.remove(e);

        if (e instanceof Grass) {
            grassOnMap.remove((Grass) e);
        } else if (e instanceof Predator) {
            predatorsOnMap.remove((Predator) e);
        } else if (e instanceof Herbivore) {
            herbivoresOnMap.remove((Herbivore) e);
        } else if (e instanceof Creature) {
            creatureOnMap.remove((Creature) e);
        }
    }

    public static ArrayList<Herbivore> getAllHerbivore() {
        return herbivoresOnMap;
    }
    public static ArrayList<Predator> getAllPredator() {
        return predatorsOnMap;
    }
    public static ArrayList<Entity> getAllEntity() {
        return entityOnMap;
    }
    public static ArrayList<Grass> getAllGrass() {
        return grassOnMap;
    }
    public static ArrayList<Creature> getAllCreature() {
        return creatureOnMap;
    }

    public GameMap(int[] mapSize) {
        map = new String[mapSize[0]][mapSize[1]];
        System.out.println("конструктор мапы вызван");
    }

    public static void clearMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = null;
            }
        }
        System.out.println("✅ Карта полностью очищена!");
    }

    public static int[] getMapSize() {
        int width = map[0].length;
        int height = map.length;
        return new int[]{width, height};
    }

    public static void placeEntity(int[] coordinate, String entity) {
        map[coordinate[1]][coordinate[0]] = entity;

        int x = coordinate[0];
        int y = coordinate[1];

        // 🔒 защита от выхода за границы
        if (x < 0 || y < 0 || x >= getMapSize()[0] || y >= getMapSize()[1]) {
            System.out.println("⚠️ Ошибка: попытка разместить за границами карты (" + x + "," + y + ")");
            return;
        }
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
        System.out.println("карта запринтилась");
    }

    public static boolean isFree(int[] coordinate) {
        for (int i = 0; i < entityOnMap.size(); i++) {
            if (Arrays.equals(coordinate, entityOnMap.get(i).getCoordinate())) {
                return false;
            }
        }
        return true;
    }
}
