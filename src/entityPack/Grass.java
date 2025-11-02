package entityPack;

public class Grass extends Entity {
    private static int health;
    public static void setGrassHealth(int a) {
        health = a;
    }

    public static int getHealth() {
        return health;
    }



    public Grass() {
        super("\uD83C\uDF40");
    }
}
