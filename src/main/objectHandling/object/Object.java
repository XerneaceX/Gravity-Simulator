package main.objectHandling.object;

import main.Position;
import main.objectHandling.ObjectHandler;

import java.text.DecimalFormat;

import static main.objectHandling.ObjectHandler.GRAVITATIONAL_CONSTANT;

public class Object {
    private static final double DEFAULT_DRAG_COEFFICIENT = 0.0;
    private static final double DEFAULT_SURFACE_AREA = 1;
    private static final double[] DEFAULT_INITIAL_VELOCITY = new double[]{0, 0};
    private static final double DEFAULT_MASS = 69;

    private Position position;

    private double mass;
    private double dragCoefficient;
    private double[] velocity;
    private double size;
    private Shape shape;
    private HitBox hitBox;
    private boolean hitFloor = false;


    /**
     * Constructor for an object with AirDrag and initial velocity
     *
     * @param mass            is the mass of the object in KG
     * @param dragCoefficient is the drag coefficient of the object. We assume that the object has the same
     *                        drag coefficient on all sides
     * @param size     is the surface area of the object. We assume that the object has the same surface area
     *                        on all sides.
     * @param initialVelocity is the velocity at which the object starts in m/s Ex: {0,10} would mean that the object
     *                        is going up by 10 m/s at the start of the simulation
     */
    public Object(double mass, double dragCoefficient, double size, double[] initialVelocity) {
        setSize(size);
        setMass(mass);
        setDragCoefficient(dragCoefficient);
        setVelocity(initialVelocity);
        setShape(Shape.SQUARE);
        setPosition(new Position(0,0));
        setHitBox(new HitBox((int) getSize(), getShape(), position));

    }

    /**
     * Constructor for an object with AirDrag
     *
     * @param mass            is the mass of the object in KG
     * @param dragCoefficient is the drag coefficient of the object. We assume that the object has the same
     *                        drag coefficient on all sides
     * @param size     is the surface area of the object. We assume that the object has the same surface area
     *                        on all sides.
     */
    public Object(double mass, double dragCoefficient, double size) {
        this(mass, dragCoefficient, size, DEFAULT_INITIAL_VELOCITY);
    }

    /**
     * Simple constructor for an object for which there's no AirDrag
     */
    public Object() {
        this(DEFAULT_MASS, DEFAULT_DRAG_COEFFICIENT, DEFAULT_SURFACE_AREA, DEFAULT_INITIAL_VELOCITY);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public void setHitBox(HitBox hitBox) {
        this.hitBox = hitBox;
    }

    public double getMass() {
        return this.mass;
    }

    public double getHeight() {
        return this.position.getY();
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

    public double[] getVelocity() {
        return this.velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public double getSize() {
        return this.size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void accelerate(double[] acceleration) {
        this.velocity[0] += acceleration[0];
        this.velocity[1] += acceleration[1];
    }

    public void updatePosition(int HZ) {
        this.position.incrementX(getVelocity()[0] / HZ);
        this.position.incrementY(getVelocity()[1] / HZ);
    }

    /**
     * Applies drag force to itself
     * @param HZ is How many times to calculate it by second
     */
    public void applyDrag(int HZ) {
        double[] drag = new double[2];
        drag[0] = ((0.5 * ObjectHandler.AIR_DENSITY * Math.pow(this.getVelocity()[0], 2) * this.getDragCoefficient() * this.getSize()) / HZ) / this.getMass();
        drag[1] = ((0.5 * ObjectHandler.AIR_DENSITY * Math.pow(this.getVelocity()[1], 2) * this.getDragCoefficient() * this.getSize()) / HZ) / this.getMass();
        this.accelerate(drag);
    }

    /**
     * @return the norm of the resulting velocity vector
     */
    public double getResultingVelocity() {
        return (Math.sqrt(Math.pow(this.velocity[0], 2) + Math.pow(this.velocity[1], 2)));
    }

    public void doPhysicsTick(int HZ, StaticObject[] staticObjects) {
        moveAndSlide(HZ, staticObjects);
    }

    public void moveAndSlide(int HZ, StaticObject[] staticObjects) {
        if (!hitFloor) {
            for (StaticObject staticObject : staticObjects) {
                if (hitBox.checkForCollision(staticObject.getHitBox())) {
                    this.velocity[0] = 0;
                    this.velocity[1] = 0;
                    hitFloor = true;
                    System.out.println("collision detected at : " + getPosition());
                }
            }
        }

        if (!hitFloor) {
            // applies gravity
            accelerate(new double[]{0, (-GRAVITATIONAL_CONSTANT) / HZ});
        }

        applyDrag(HZ);
        updatePosition(HZ);
    }

    /**
     * @return the resulting velocity, the deltaHeight and the deltaDistance of the object in a String format.
     */
    public String getObjectStats() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "Velocity: \t\t" + df.format(getResultingVelocity()) + " m/s" + "\nHeight: \t\t"
                + df.format(getHeight()) + " m" + "\ndistance: \t\t" + df.format(getPosition().getX()) + " m";
    }
}