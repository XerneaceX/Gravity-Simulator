package main.objectHandling.object;

import main.Position;

/**
 * Abstract class for making hitboxes.
 */
public abstract class HitBox {
    int size;
    Position position;
    double equation;

    /**
     * Constructs a hitbox of the desired size and takes the position of the object
     * @param size is the size of the object
     * @param position is the center of the hitbox.
     */
    protected HitBox(int size, Position position) {
        this.size = size;
        this.position = position;
    }

    int getSize() {
        return size;
    }

    /**
     * @return an array of the extremities of the hitbox in the X axis
     */
    int[] getRangeX() {
        return new int[]{
                (int) (position.getX() - (double) getSize() / 2),
                (int) (position.getX() + (double) getSize() / 2)
        };
    }

    /**
     * @return an array of the extremities of the hitbox in the Y axis
     */
    int[] getRangeY() {
        return new int[]{
                (int) (position.getY() - (double) getSize() / 2),
                (int) (position.getY() + (double) getSize() / 2)
        };
    }

    /**
     * Checks if two hitboxes collide
     * @param hitBox is the hitBox to check if the "this" collided with
     * @return if the hitboxes collided
     */
    abstract boolean hitBoxCollided(HitBox hitBox);

    /**
     * Checks for collisions
     * @param hitBox
     * @return
     */
    public boolean checkForCollision(HitBox hitBox) {
        return hitBoxCollided(hitBox);
    }

    @Override
    public String toString() {
        return "HitBox{" +
                "size=" + size +
                ", position=" + position +
                ", equation=" + equation +
                '}';
    }
}
