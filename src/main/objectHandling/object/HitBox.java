package main.objectHandling.object;

import utils.PositionVector;
import utils.Vector;

/**
 * Abstract class for making hitboxes.
 */
public abstract class HitBox {
    PositionVector positionVector;
    Vector.Direction direction;
    double size;
    double equation;

    /**
     * Constructs a hitbox of the desired size and takes the position of the object
     * @param size is the size of the object
     * @param positionVector is the center of the hitbox.
     */
    protected HitBox(double size, PositionVector positionVector) {
        this.size = size;
        this.positionVector = positionVector;
    }

    double getSize() {
        return size;
    }

    /**
     * @return an array of the extremities of the hitbox in the X axis
     */
    double getRangeX(int x) {
        return switch (x) {
            case 0 -> (positionVector.getX() - getSize() / 2);
            case 1 -> (positionVector.getX() + getSize() / 2);
            default -> throw new IllegalStateException("Unexpected value: " + x);
        };
    }

    /**
     * @return an array of the extremities of the hitbox in the Y axis
     */
    double getRangeY(int x) {
        return switch (x) {
            case 0 -> (positionVector.getY() - getSize() / 2);
            case 1 -> (positionVector.getY() + getSize() / 2);
            default -> throw new IllegalStateException("Unexpected value: " + x);
        };
    }

    /**
     * Checks if two hitboxes collide
     * @param hitBox is the hitBox to check if the "this" collided with
     * @return if the hitboxes collided
     */
    abstract boolean hitBoxCollided(HitBox hitBox);

    @Override
    public String toString() {
        return "HitBox{" +
                "size=" + size +
                ", position=" + positionVector +
                ", equation=" + equation +
                '}';
    }
}
