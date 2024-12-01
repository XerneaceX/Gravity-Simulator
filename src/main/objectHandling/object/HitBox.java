package main.objectHandling.object;

import main.Position;

public class HitBox {
    private Shape shape;
    private int size;
    private Position position;


    protected HitBox(int size, Shape shape, Position position) {
        this.size = size;
        this.shape = shape;
        this.position = position;
    }

    private int getSize() {
        return size;
    }

    private int[] getRangeX() {
        return new int[]{
                (int) (position.getX() - (double) getSize() / 2),
                (int) (position.getX() + (double) getSize() / 2)
        };
    }

    private int[] getRangeY() {
        return new int[]{
                (int) (position.getY() - (double) getSize() / 2),
                (int) (position.getY() + (double) getSize() / 2)
        };
    }

    private boolean hitBoxCollided(HitBox hitBox) {
        return position.getY() > hitBox.getRangeY()[0] && position.getY() < hitBox.getRangeY()[1] && position.getX() > hitBox.getRangeX()[0] && position.getX() < hitBox.getRangeX()[1];
    }

    public boolean checkForCollision(HitBox hitBox) {
        return hitBoxCollided(hitBox);
    }
}
