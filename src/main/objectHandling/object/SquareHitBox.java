package main.objectHandling.object;

import main.Position;

public class SquareHitBox extends HitBox {
    public SquareHitBox(int size, Position position) {
        super(size, position);
    }

    @Override
    protected boolean hitBoxCollided(HitBox hitBox) {
        return position.getY() > hitBox.getRangeY()[0] && position.getY() < hitBox.getRangeY()[1] && position.getX() > hitBox.getRangeX()[0] && position.getX() < hitBox.getRangeX()[1];
    }
}
