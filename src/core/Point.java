package core;

public class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getCoordinate() {
        return new int[]{x, y};
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
        if (this == o) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
