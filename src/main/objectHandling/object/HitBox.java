package main.objectHandling.object;

import main.Position;
import main.Vector;

/**
 * Abstract class for making hitboxes.
 */
public abstract class HitBox {
    Position position;
    Vector.Direction direction;
    double size;
    double equation;

    /**
     * Constructs a hitbox of the desired size and takes the position of the object
     * @param size is the size of the object
     * @param position is the center of the hitbox.
     */
    protected HitBox(double size, Position position) {
        this.size = size;
        this.position = position;
    }

    double getSize() {
        return size;
    }

    /**
     * @return an array of the extremities of the hitbox in the X axis
     */
    double getRangeX(int x) {
        return switch (x) {
            case 0 -> (position.getX() - getSize() / 2);
            case 1 -> (position.getX() + getSize() / 2);
            default -> throw new IllegalStateException("Unexpected value: " + x);
        };
    }

    /**
     * @return an array of the extremities of the hitbox in the Y axis
     */
    double getRangeY(int x) {
        return switch (x) {
            case 0 -> (position.getY() - getSize() / 2);
            case 1 -> (position.getY() + getSize() / 2);
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
                ", position=" + position +
                ", equation=" + equation +
                '}';
    }
}
