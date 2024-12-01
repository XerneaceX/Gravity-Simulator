package main.objectHandling.object;

import main.Position;

/**
 * Not yet implemented
 */
public class CircleHitBox extends HitBox {
    private final int radius = size/2;
    public CircleHitBox(int size, Position position) {
        super(size, position);
    }

    @Override
    boolean hitBoxCollided(HitBox hitBox) {
        return false;
    }
}
