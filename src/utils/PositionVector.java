package utils;

public class PositionVector {
    private double x;
    private double y;

    public PositionVector(int x, int y) {
        setX(x);
        setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void incrementX(double increment) {
        this.x += increment;
    }

    public void incrementY(double increment) {
        this.y += increment;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
