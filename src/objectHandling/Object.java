package objectHandling;

import java.text.DecimalFormat;

public class Object {
    private static final double DEFAULT_DRAG_COEFFICIENT = 0.0;
    private static final double DEFAULT_SURFACE_AREA = 1;
    private static final double[] DEFAULT_INITIAL_VELOCITY = new double[]{0, 0};
    private static final double DEFAULT_MASS = 69;

    private double mass;
    private double dragCoefficient;
    private double[] velocity;
    private double[] deltaX = new double[2];
    private double surfaceArea;
    private String groundHitInfo;


    /**
     * Constructor for an object with AirDrag and initial velocity
     *
     * @param mass            is the mass of the object in KG
     * @param dragCoefficient is the drag coefficient of the object. We assume that the object has the same
     *                        drag coefficient on all sides
     * @param surfaceArea     is the surface area of the object. We assume that the object has the same surface area
     *                        on all sides.
     * @param initialVelocity is the velocity at which the object starts in m/s Ex: {0,10} would mean that the object
     *                        is going up by 10 m/s at the start of the simulation
     */
    public Object(double mass, double dragCoefficient, double surfaceArea, double[] initialVelocity) {
        setSurfaceArea(surfaceArea);
        setMass(mass);
        setDragCoefficient(dragCoefficient);
        setVelocity(initialVelocity);
    }

    /**
     * Constructor for an object with AirDrag
     *
     * @param mass            is the mass of the object in KG
     * @param dragCoefficient is the drag coefficient of the object. We assume that the object has the same
     *                        drag coefficient on all sides
     * @param surfaceArea     is the surface area of the object. We assume that the object has the same surface area
     *                        on all sides.
     */
    public Object(double mass, double dragCoefficient, double surfaceArea) {
        this(mass, dragCoefficient, surfaceArea, DEFAULT_INITIAL_VELOCITY);
    }

    /**
     * Simple constructor for an object for which there's no AirDrag
     */
    public Object() {
        this(DEFAULT_MASS, DEFAULT_DRAG_COEFFICIENT, DEFAULT_SURFACE_AREA, DEFAULT_INITIAL_VELOCITY);
    }

    public double getMass() {
        return this.mass;
    }

    public double getHeight() {
        return this.deltaX[1];
    }

    public String getGroundHitInfo() {
        return this.groundHitInfo;
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

    public double getSurfaceArea() {
        return this.surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public void accelerate(double[] acceleration) {
        this.velocity[0] += acceleration[0];
        this.velocity[1] += acceleration[1];
    }

    public void updateDeltaX(int HZ) {
        this.deltaX[0] += getVelocity()[0] / HZ;
        this.deltaX[1] += getVelocity()[1] / HZ;

        updateGroundHitInfo(ObjectHandler.floorHeight);
    }

    public void updateGroundHitInfo(int floor) {
        if (this.deltaX[1] <= floor && this.groundHitInfo == null) {
            this.groundHitInfo = "Ground hit at distance: " + deltaX[0];
        }
    }

    /**
     * Applies drag force to itself
     * @param HZ is How many times to calculate it by second
     */
    public void applyDrag(int HZ) {
        double[] drag = new double[2];
        drag[0] = ((0.5 * ObjectHandler.AIR_DENSITY * Math.pow(this.getVelocity()[0], 2) * this.getDragCoefficient() * this.getSurfaceArea()) / HZ) / this.getMass();
        drag[1] = ((0.5 * ObjectHandler.AIR_DENSITY * Math.pow(this.getVelocity()[1], 2) * this.getDragCoefficient() * this.getSurfaceArea()) / HZ) / this.getMass();
        this.accelerate(drag);
    }

    /**
     * @return the norm of the resulting velocity vector
     */
    public double getResultingVelocity() {
        return (Math.sqrt(Math.pow(this.velocity[0], 2) + Math.pow(this.velocity[1], 2)));
    }

    /**
     * @return the resulting velocity, the deltaHeight and the deltaDistance of the object in a String format.
     */
    public String getObjectStats() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "Velocity: \t\t" + df.format(getResultingVelocity()) + " m/s" + "\nHeight: \t\t" + df.format(deltaX[1]) + " m" + "\ndistance: \t\t" + df.format(deltaX[0]) + " m";
    }
}
