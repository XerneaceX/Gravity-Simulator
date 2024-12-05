package main.objectHandling.object;

import main.Position;

/**
 * Not yet implemented
 */
public class CircleHitBox extends HitBox {
    private double radius;
    public CircleHitBox(double size, Position position) {
        super(size, position);
        setRadius(getSize()/2);
    }

    double getRadius() {
        return radius;
    }

    private void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    boolean hitBoxCollided(HitBox hitBox) {
        return false;
    }
}