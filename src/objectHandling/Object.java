package objectHandling;

import java.text.DecimalFormat;

public class Object {
    private static final double DEFAULT_DRAG_COEFFICIENT = 0.0;
    private static final double DEFAULT_SURFACE_AREA = 1;
    private static final double[] DEFAULT_INITIAL_VELOCITY = new double[] {0,0};
    private static final double DEFAULT_MASS = 69;

    private double mass;
    private double dragCoefficient;
    private double[] velocity;
    private double[] deltaX = new double[2];
    private double surfaceArea;
    private String groundHitInfo;

    public Object(double mass, double dragCoefficient, double surfaceArea, double[] initialVelocity) {
        setSurfaceArea(surfaceArea);
        setMass(mass);
        setDragCoefficient(dragCoefficient);
        setVelocity(initialVelocity);
    }

    public Object(double mass, double dragCoefficient, double surfaceArea) {
        this(mass, dragCoefficient, surfaceArea, DEFAULT_INITIAL_VELOCITY);
    }

    public Object() {
        this(DEFAULT_MASS, DEFAULT_DRAG_COEFFICIENT, DEFAULT_SURFACE_AREA, DEFAULT_INITIAL_VELOCITY);
    }

    public double getMass() {
        return mass;
    }

    public double getHeight() {
        return deltaX[1];
    }

    public String getGroundHitInfo() {
        return groundHitInfo;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getDragCoefficient() {
        return dragCoefficient;
    }

    public void setDragCoefficient(double dragCoefficient) {
        this.dragCoefficient = dragCoefficient;
    }

    public double[] getVelocity() {
        return velocity;
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public void accelerate(double[] acceleration) {
        this.velocity[0] += acceleration[0];
        this.velocity[1] += acceleration[1];
    }

    public void updateDeltaX(int HZ) {
        this.deltaX[0] += getVelocity()[0]/HZ;
        this.deltaX[1] += getVelocity()[1]/HZ;

        if (this.deltaX[1] <= 0 && this.groundHitInfo == null) {
            this.groundHitInfo = "Ground hit at distance: " + deltaX[0];
        }
    }

    public void applyDrag(int HZ) {
        double[] drag = new double[2];
        drag[0] = ((0.5*ObjectHandler.AIR_DENSITY*Math.pow(this.getVelocity()[0],2)*this.getDragCoefficient()*this.getSurfaceArea())/HZ)/this.getMass();
        drag[1] = ((0.5*ObjectHandler.AIR_DENSITY*Math.pow(this.getVelocity()[1],2)*this.getDragCoefficient()*this.getSurfaceArea())/HZ)/this.getMass();
        this.accelerate(drag);
    }

    public double getResultingVelocity() {
        return (Math.sqrt(Math.pow(velocity[0], 2) + Math.pow(velocity[1], 2)));
    }

    public String getObjectStats() {
        DecimalFormat df = new DecimalFormat("#.##");
        return "Velocity: \t\t" + df.format(getResultingVelocity()) + " m/s" + "\nHeight: \t\t" + df.format(deltaX[1]) + " m" + "\ndistance: \t\t" + df.format(deltaX[0]) + " m";
    }
}
