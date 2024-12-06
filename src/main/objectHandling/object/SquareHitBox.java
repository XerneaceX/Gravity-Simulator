package main.objectHandling.object;

import utils.PositionVector;

public class SquareHitBox extends HitBox {
    public SquareHitBox(double size, PositionVector positionVector) {
        super(size, positionVector);
    }

    @Override
    protected boolean hitBoxCollided(HitBox hitBox) {
        return (this.getRangeY(0) < hitBox.getRangeY(1) &&
                this.getRangeY(1) > hitBox.getRangeY(0)) &&
                this.getRangeX(1) > hitBox.getRangeX(0) &&
                this.getRangeX(0) < hitBox.getRangeX(1);
    }
}
