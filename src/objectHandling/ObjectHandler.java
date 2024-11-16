package objectHandling;

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
    private final ArrayList<objectHandling.Object> objects = new ArrayList<>();
    private boolean floor = false;

    /**
     * Constructs the object handler.
     * @param objects are the objects that will be handled by the simulation
     * @param simTime is for how long the simulation will be (in seconds)
     * @param HZ the times/s to do the calculations. Greater number result in more precise results but take up more performance
     */
    public ObjectHandler(Object[] objects, int simTime, int HZ) {
        addObject(objects);
        setSimTime(simTime);
        setHZ(HZ);
    }

    /**
     * Adds objects to the environnement.
     * @param objects are the objects to add
     */
    public void addObject(objectHandling.Object[] objects) {
        Collections.addAll(this.objects, objects);
    }

    /**
     * If floor is set to true, when printing the results; it will also print the distance at which the object touched
     * the floor
     */
    public void addFloor() {
        this.floor = true;
    }

    public void setHZ(int HZ) {
        this.HZ = HZ;
    }

    public void setSimTime(int simTime) {
        this.simTime = simTime;
    }

    /**
     * Starts all the calculation necessary for the simulation
     * Time is calculated in ticks. At every tick, a new calculation happens
     */
    public void startSimulation() {
        for (int ticks = 0; ticks < this.simTime * this.HZ; ticks++) {
            updateObjects();
        }
    }

    /**
     * Applies all natural forces on all objects
     */
    private void updateObjects() {
        for (Object object : this.objects) {
            object.applyDrag(this.HZ);
            object.accelerate(new double[]{0, (-GRAVITATIONAL_CONSTANT) / this.HZ});
            object.updateDeltaX(this.HZ);
        }

    }

    /**
     * Prints where the objects are and their speed at the end of the simulation
     */
    public void printResults() {
        for (int i = 0; i < this.objects.size(); i++) {
            Object object = this.objects.get(i);
            System.out.println("-----------------------------------");
            System.out.println("OBJECT: \t\t#" + i);
            System.out.println(object.getObjectStats());
            if (this.floor) System.out.println(object.getGroundHitInfo());
            System.out.println("-----------------------------------");

        }
    }
}
