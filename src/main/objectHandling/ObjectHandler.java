package main.objectHandling;

import main.Position;
import main.objectHandling.object.Object;
import main.objectHandling.object.StaticObject;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Object handler is the environnement in which the objects will be located in.
 * The user may change constants to achieve the desired results
 */
public class ObjectHandler {
    public static final double GRAVITATIONAL_CONSTANT = 9.81; // N/kg
    public static final double AIR_DENSITY = 1.204; // m3/kg

    private int HZ;
    private int simTime;
    public boolean simIsON = false;
    private final ArrayList<main.objectHandling.object.Object> fallingObjects = new ArrayList<>();
    private final ArrayList<main.objectHandling.object.StaticObject> staticObjects = new ArrayList<>();

    /**
     * Constructs the object handler.
     * @param fallingObjects are the objects that will be handled by the simulation
     * @param simTime is for how long the simulation will be (in seconds)
     * @param HZ the times/s to do the calculations. Greater number result in more precise results but take up more performance
     */
    public ObjectHandler(main.objectHandling.object.Object[] fallingObjects, int simTime, int HZ) {
        addFallingObject(fallingObjects);
        setSimTime(simTime);
        setHZ(HZ);
    }

    public StaticObject[] getStaticObjects() {
        return staticObjects.toArray(new StaticObject[0]);
    }

    public Object[] getAllObjects() {
        Object[] allObjects = new Object[fallingObjects.size()+staticObjects.size()];
        for (int i = 0; i < fallingObjects.size(); i++) {
            allObjects[i] = fallingObjects.get(i);
        }
        for (int i = fallingObjects.size(); i < staticObjects.size(); i++) {
            allObjects[i] = staticObjects.get(i);
        }
        return allObjects;
    }

    /**
     * Adds objects to the environnement.
     * @param objects are the objects to add
     */
    public void addFallingObject(main.objectHandling.object.Object[] objects) {
        Collections.addAll(this.fallingObjects, objects);
    }

    public void addStaticObject(main.objectHandling.object.StaticObject staticObject) {
        Collections.addAll(this.staticObjects, staticObject);
    }

    public void setHZ(int HZ) {
        this.HZ = HZ;
    }

    public void setSimTime(int simTime) {
        this.simTime = simTime;
    }

    /**
     * Starts all the calculation necessary for the simulation
     * Time is calculated in ticks. At every tick, a new calculation happens.
     * The simulation will stop when the simTime (seconds) is over.
     */
    public void startSimulation() {
        simIsON = true;
        for (int ticks = 0; ticks < this.simTime * this.HZ; ticks++) {
            updateObjects();
        }
        simIsON = false;
    }

    /**
     * Is supposed to be accessed in thread
     * @return the position of all objects
     */
    public Position[] getAllPositions() {
        Position[] positions = new Position[this.fallingObjects.size()];
        for (int i = 0; i < fallingObjects.size(); i++) {
            positions[i] = fallingObjects.get(i).getPosition();
        }
        return positions;
    }

    public Object[] getFallingObjects() {
        return fallingObjects.toArray(new Object[0]);
    }

    /**
     * Applies all natural forces on all objects
     */
    private void updateObjects() {
        StaticObject[] staticObjects = new StaticObject[this.staticObjects.size()];
        staticObjects = this.staticObjects.toArray(staticObjects);
        for (main.objectHandling.object.Object object : this.fallingObjects) {
            object.doPhysicsTick(this.HZ, staticObjects);
        }
    }

    /**
     * Prints where the objects are and their speed at the end of the simulation
     */
    public void printResults() {
        for (int i = 0; i < this.fallingObjects.size(); i++) {
            Object object = this.fallingObjects.get(i);
            System.out.println("-----------------------------------");
            System.out.println("OBJECT: \t\t#" + i);
            System.out.println(object.getObjectStats());
            System.out.println("-----------------------------------");

        }
    }
}
