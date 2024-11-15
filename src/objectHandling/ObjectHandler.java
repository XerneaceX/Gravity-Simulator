package objectHandling;

import java.util.ArrayList;
import java.util.Collections;

public class ObjectHandler {
    public static final double GRAVITATIONAL_CONSTANT = 9.81; // N/kg
    public static final double AIR_DENSITY = 1.204; // m3/kg

    private int HZ = 20;
    private int simTime;
    private ArrayList<objectHandling.Object> objects = new ArrayList<>();
    private boolean floor = false;

    public ObjectHandler(Object[] objects, int simTime, int HZ) {
        addObject(objects);
        setSimTime(simTime);
        setHZ(HZ);
    }

    public void addObject(objectHandling.Object[] objects) {
        Collections.addAll(this.objects, objects);
    }

    public void addFloor() {
        this.floor = true;
    }

    public void setHZ(int HZ) {
        this.HZ = HZ;
    }

    public void setSimTime(int simTime) {
        this.simTime = simTime;
    }

    public void startSimulation() {
        for (int ticks = 0; ticks < this.simTime * this.HZ; ticks++) {
            updateObjects();
        }
    }

    private void updateObjects() {
        for (Object object : this.objects) {
            object.applyDrag(this.HZ);
            object.accelerate(new double[]{0, (-GRAVITATIONAL_CONSTANT) / this.HZ});
            object.updateDeltaX(this.HZ);
        }

    }

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
