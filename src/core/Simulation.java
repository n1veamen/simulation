package core;

import java.util.Random;

import entityPack.CreaturePack.Herbivore;
import entityPack.CreaturePack.Predator;
import entityPack.Grass;
import entityPack.Rock;
import entityPack.Tree;

public class Simulation {
    static Random random = new Random();

    public static void main(String[] args) {
        initActions(10, 10, 20);
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
            new Rock();
            new Tree();

            GameMap.addEntityToMap(new Herbivore());
            GameMap.addEntityToMap(new Predator());
        }
    }

    public static void turnActions() {
        for (Herbivore a : GameMap.getAllHerbivore()) {
            a.makeMove();
        }
        GameMap.printMap();
    }
}
