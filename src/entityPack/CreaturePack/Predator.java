package entityPack.CreaturePack;

public class Predator extends Creature{
    private static int damage;

    public Predator(int damage, int hp, int speed) {
        super("\uD83D\uDD34");

        this.hp = hp;
        this.speed = speed;
        this.damage = damage;
    }

    public static int getDamage() {
        return damage;
    }
    public void attackTarget() {
        this.hp += this.damage;
    }
}
