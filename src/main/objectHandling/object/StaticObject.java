package main.objectHandling.object;

import main.Position;

public class StaticObject extends main.objectHandling.object.Object {
    public StaticObject(Position position, int size) {
        super();
        super.setSize(size);
        super.setPosition(position);
        super.setHitBox(new HitBox(size, super.getShape(), position));
    }
}
