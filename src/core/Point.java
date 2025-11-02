package core;

import java.util.Objects;

public class Point {
    private int x;
    private int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getCoordinate() {
        return new int[]{x, y};
    }

    public void setCoordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point up() {
        return new Point(x - 1, y);
    }

    public Point down() {
        return new Point(x + 1, y);
    }

    public Point left() {
        return new Point(x, y - 1);
    }

    public Point right() {
        return new Point(x, y + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

}
