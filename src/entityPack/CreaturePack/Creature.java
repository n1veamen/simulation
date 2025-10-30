package entityPack.CreaturePack;

import entityPack.Entity;

public abstract class Creature extends Entity {
    private int speed;
    private int hp;

    public Creature(String type) {
        super(type);


    }

    public void makeMove() {
        
    }
}
