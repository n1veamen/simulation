package entityPack.CreaturePack;

import entityPack.Entity;

public abstract class Creature extends Entity {
    private int speed;
    private int hp;

    public Creature(String type) {
        super(type);
    }

    public static void makeMove() {
        int moveY;
        int moveX;
        //this.coordinate[0] += moveX;
        //this.coordinate[1] += moveY;
    }
}
