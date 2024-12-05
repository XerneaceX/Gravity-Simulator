package main.objectHandling.object;

import utils.PositionVector;
import utils.Vector;
import main.objectHandling.ObjectHandler;

import java.text.DecimalFormat;

import static main.objectHandling.ObjectHandler.GRAVITATIONAL_CONSTANT;

public class Object {
    public static final PositionVector DEFAULT_POSITION_VECTOR = new PositionVector(0, 0);
    public static final double[] DEFAULT_INITIAL_VELOCITY = new double[]{0, 0};
    public static final double DEFAULT_DRAG_COEFFICIENT = 0.0;
    public static final Shape DEFAULT_SHAPE = Shape.SQUARE;
    public static final double DEFAULT_SURFACE_AREA = 1;
    public static final double DEFAULT_MASS = 69;

    private PositionVector positionVector;

    private boolean onFloor = false;
    private double dragCoefficient;
    private Vector velocity;
    private long tickDone;
    private HitBox hitBox;
    private double mass;
    private double size;
    private Shape shape;


    /**
     * Constructor for an object with AirDrag and initial velocity
     *
     * @param mass            is the mass of the object in KG
     * @param dragCoefficient is the drag coefficient of the object. We assume that the object has the same
     *                        drag coefficient on all sides
     * @param size            is the surface area of the object. We assume that the object has the same surface area
     *                        on all sides.
     * @param initialVelocity is the velocity at which the object starts in m/s Ex: {0,10} would mean that the object
     *                        is going up by 10 m/s at the start of the simulation
     */
    public Object(double mass, double dragCoefficient, double size, Vector initialVelocity, PositionVector positionVector) {
        setSize(size);
        setMass(mass);
        setDragCoefficient(dragCoefficient);
        setVelocity(initialVelocity);
        setShape(DEFAULT_SHAPE);
        setPosition(positionVector);
        makeHitBox();
    }

    /**
     * Constructor for an object with AirDrag
     *
     * @param mass            is the mass of the object in KG
     * @param dragCoefficient is the drag coefficient of the object. We assume that the object has the same
     *                        drag coefficient on all sides
     * @param size            is the surface area of the object. We assume that the object has the same surface area
     *                        on all sides.
     */
    public Object(double mass, double dragCoefficient, double size) {
        this(mass, dragCoefficient, size, new Vector(DEFAULT_INITIAL_VELOCITY), DEFAULT_POSITION_VECTOR);
    }

    public Object(double size, PositionVector positionVector) {
        this(DEFAULT_MASS, DEFAULT_DRAG_COEFFICIENT, size, new Vector(DEFAULT_INITIAL_VELOCITY), positionVector);
    }

    /**
     * Simple constructor for an object for which there's no AirDrag
     */
    public Object() {
        this(DEFAULT_MASS, DEFAULT_DRAG_COEFFICIENT, DEFAULT_SURFACE_AREA, new Vector(DEFAULT_INITIAL_VELOCITY), DEFAULT_POSITION_VECTOR);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setPosition(PositionVector positionVector) {
        this.positionVector = positionVector;
    }

    public PositionVector getPosition() {
        return positionVector;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public void makeHitBox() {
        switch (shape) {
            case SQUARE -> this.hitBox = new SquareHitBox(getSize(), getPosition());
            case CIRCLE -> this.hitBox = new CircleHitBox(getSize(), getPosition());
        }
    }

    public double getMass() {
        return this.mass;
    }

    public double getHeight() {
        return this.positionVector.getY();
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getDragCoefficient() {
        return this.dragCoefficient;
    }

    public void setDragCoefficient(double dragCoefficient) {
        this.dragCoefficient = dragCoefficient;
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public double getSize() {
        return this.size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public long getTick() {
        return tickDone;
    }

    public void incrementTick() {
        this.tickDone++;
    }

    public void accelerate(Vector acceleration) {
        velocity.incrementX(acceleration.getX());
        velocity.incrementY(acceleration.getY());
    }

    public void updatePosition(int HZ) {
        positionVector.incrementX(velocity.getX() / HZ);
        positionVector.incrementY(velocity.getY() / HZ);
    }

    /**
     * Applies drag force to itself
     *
     * @param HZ is How many times to calculate it by second
     */
    public void applyDrag(int HZ) {
        Vector drag;
        drag = new Vector(
                ((0.5 * ObjectHandler.AIR_DENSITY * Math.pow(velocity.getX(), 2) * this.getDragCoefficient() * this.getSize()) / HZ) / this.getMass(),
                ((0.5 * ObjectHandler.AIR_DENSITY * Math.pow(velocity.getY(), 2) * this.getDragCoefficient() * this.getSize()) / HZ) / this.getMass()
        );
        this.accelerate(drag);
    }

    /**
     * @return the norm of the resulting velocity vector
     */
    public double getResultingVelocity() {
        return (Math.sqrt(Math.pow(velocity.getX(), 2) + Math.pow(velocity.getY(), 2)));
    }

    public void doPhysicsTick(int HZ, StaticObject[] staticObjects) {
        moveAndSlide(HZ, staticObjects);
        incrementTick();
    }

    public void moveAndSlide(int HZ, StaticObject[] staticObjects) {
        if (!onFloor && tickDone % 10 == 0) {
            for (StaticObject staticObject : staticObjects) {
                if (hitBox.hitBoxCollided(staticObject.getHitBox())) {
                    doCollision();
                }
            }
        }

        if (!onFloor) {
            // applies gravity
            accelerate(new Vector(0, (-GRAVITATIONAL_CONSTANT) / HZ));
        }
        applyDrag(HZ);
        updatePosition(HZ);
    }

    private void doCollision() {
        velocity.reset();
        this.onFloor = true;
        System.out.println("collision detected at : " + getPosition());
    }

    /**
     * @return the resulting velocity, the deltaHeight and the deltaDistance of the object in a String format.
     */
    public String getObjectStats() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "Velocity: \t\t" + df.format(getResultingVelocity()) + " m/s" + "\nHeight: \t\t"
                + df.format(getHeight()) + " m" + "\ndistance: \t\t" + df.format(getPosition().getX()) + " m";
    }

    @Override
    public String toString() {
        return "Object{" +
                "position=" + positionVector +
                ", size=" + size +
                ", shape=" + shape +
                '}';
    }
}
