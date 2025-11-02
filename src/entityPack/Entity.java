package entityPack;
import core.GameMap;
import java.util.Random;

public abstract class Entity {
    protected int[] coordinate;
    protected String type = "e";
    Random random = new Random();

    public void setCoordinate(int[] coordinate) {
        this.coordinate = coordinate;
    }

    public int[] getCoordinate() {
        return coordinate;
    }

    public void delEntityFromMap(Entity e) {
        GameMap.delEntityFromMap(e);
    }

    public String getType() {
        return type;
    }

    public Entity(String type) {
        this.type = type;
        while (true) {
            //выбор стартовых координат для сущности
            int x = random.nextInt(GameMap.getMapSize()[0]);
            int y = random.nextInt(GameMap.getMapSize()[1]);

            //проверка на занятость координат
            if (GameMap.isFree(new int[]{x, y})) {
                this.coordinate = new int[]{x, y};
                GameMap.placeEntity(coordinate, type);
                System.out.println("ентити создан, размещен");
                break;
            } else {
                System.out.println("неудалось разместить исфри не сработал");
            }
        }

        //добавление обьекта в нужный массив смотря какого класса обьект попадает в этот метод.
        GameMap.addEntityToMap(this);
    }
}
