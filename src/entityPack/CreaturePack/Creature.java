package entityPack.CreaturePack;

import core.GameMap;
import core.PathFinder;
import core.Point;
import entityPack.Entity;
import entityPack.Grass;

import java.lang.classfile.instruction.ThrowInstruction;
import java.util.Arrays;
import java.util.LinkedList;

public abstract class Creature extends Entity {
    protected int speed;
    protected int hp;
    private int hungryDamage;

    public Creature(String type) {
        super(type);

        GameMap.addEntityToMap(this);
    }

    public void dieCreature() {
        GameMap.delEntityFromMap(this);
    }

    public int getHP() {
        return this.hp;
    }

    public void setHP(int hp) {
        this.hp = hp;
    }

    public void makeMove() {
        if (hp <= 0) {
            dieCreature();
            return;
        }


        Point target = PathFinder.findNearestEntity(this);
        Point nextStep = null;

        if (target == null) {
            return;
        }

        Point start = new Point(this.getCoordinate()[0], this.getCoordinate()[1]);
        LinkedList<Point> road = PathFinder.BFS(start, target);
        if (road != null && road.size() > 1) {
            nextStep = road.get(1);
        }

        if (road.size() == 2) {
            attackTarget(target, nextStep);
        } else {
            this.setCoordinate(nextStep.getCoordinate());
        }
    }

    public void attackTarget(Point target, Point nextStep) {
        if (this instanceof Predator) {
            for (Herbivore e : GameMap.getAllHerbivore()) {
                if (Arrays.equals(nextStep.getCoordinate(), e.getCoordinate())) {
                    e.setHP(e.getHP() - Predator.getDamage());
                }
            }
        }
        if (this instanceof Herbivore) {

        for (Grass e : GameMap.getAllGrass()) {
            if (Arrays.equals(nextStep.getCoordinate(), e.getCoordinate())) {
                this.setHP(getHP() + Grass.getHealth());
                delEntityFromMap(e);
                break;
            }
        }

        }
    }

    public void setHungryDamage(int hungryDamage) {
        this.hungryDamage = hungryDamage;
    }

    public void hungryDamage() {
        this.hp -= hungryDamage;
    }

}
