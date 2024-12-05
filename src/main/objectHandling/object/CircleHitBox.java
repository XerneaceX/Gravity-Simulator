package main.objectHandling.object;

import utils.PositionVector;

/**
 * Not yet implemented
 */
public class CircleHitBox extends HitBox {
    private double radius;
    public CircleHitBox(double size, PositionVector positionVector) {
        super(size, positionVector);
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