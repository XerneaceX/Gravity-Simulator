package utils;

public class Vector {
    private double x;
    private double y;
    private final Direction[] direction = new Direction[2];

    public enum Direction {
        UP, DOWN, LEFT, RIGHT, NONE
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
        setDirection();
    }

    public Vector(double[] xy) {
        this(xy[0], xy[1]);
    }

    public void reverseX() {
        x = -x;
        setDirection();
    }

    public void reverseY() {
        y = -y;
        setDirection();
    }

    public void reverse() {
        x = -x;
        y = -y;
    }

    private void setDirection() {
        if (x == 0) {
            this.direction[0] = Direction.NONE;
        } else if (x > 0) {
            this.direction[0] = Direction.UP;
        } else {
            this.direction[0] = Direction.DOWN;
        }

        if (y == 0) {
            this.direction[1] = Direction.NONE;
        } else if (y > 0) {
            this.direction[1] = Direction.UP;
        } else {
            this.direction[1] = Direction.DOWN;
        }
    }

    public Direction[] getDirection() {
        return direction;
    }

    private Direction getXDirection() {
        return direction[0];
    }

    private Direction getYDirection() {
        return direction[1];
    }

    public void incrementX(double delta) {
        this.x += delta;
    }

    public void incrementY(double delta) {
        this.y += delta;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void reset() {
        this.x = 0;
        this.y = 0;
    }
}
