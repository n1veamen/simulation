package core;

import java.util.ArrayList;
import java.util.Random;

import entityPack.CreaturePack.Creature;
import entityPack.CreaturePack.Herbivore;
import entityPack.CreaturePack.Predator;
import entityPack.Entity;
import entityPack.Grass;
import entityPack.Rock;
import entityPack.Tree;

import static core.GameMap.getAllCreature;
import static core.GameMap.getMapSize;

public class Simulation {
    static Random random = new Random();

    public static void main(String[] args) {
        initActions(20, 20, 10);
        GameMap.printMap();
    }

    public static void startSimulation() {
        while (true) {
            turnActions();
        }
    }

    public static void initActions(int a, int b, int fullnessPercent) {
        int height = b;
        int width = a;
        double fullness = fullnessPercent / 100.0;
        new GameMap(new int[]{height, width});
        for (int i = 0; i < (height * width * fullness) / 5; i++) {
            new Grass();
            GameMap.addEntityToMap(new Herbivore(50, 2));
            GameMap.addEntityToMap(new Predator(10,100, 1));
        }
        int i = 0;
        while (i < 100) {
            for (Entity c : new ArrayList<>(GameMap.getAllEntity())) {
                if (c instanceof Creature) {
                    ((Creature) c).makeMove();
                }
                GameMap.placeEntity(c.getCoordinate(), c.getType());
            }
            GameMap.printMap();
            i++;
            GameMap.clearMap();
        }
    }

    public static void turnActions() {
        for (Herbivore a : GameMap.getAllHerbivore()) {
            a.makeMove();
        }
        GameMap.printMap();
    }
}
