package main.objectHandling.object;

import main.Position;

public class SquareHitBox extends HitBox {
    public SquareHitBox(double size, Position position) {
        super(size, position);
    }

    @Override
    protected boolean hitBoxCollided(HitBox hitBox) {
        return (this.getRangeY(0) < hitBox.getRangeY(1) &&
                this.getRangeY(1) > hitBox.getRangeY(1)) &&
                this.getRangeX(1) > hitBox.getRangeX(0) &&
                this.getRangeX(0) < hitBox.getRangeX(1);
    }
}
