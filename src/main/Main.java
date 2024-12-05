package main;

import main.objectHandling.object.Object;
import main.objectHandling.ObjectHandler;
import main.objectHandling.object.StaticObject;
import utils.PositionVector;

public class Main {
    public static void main(String[] args) {
        ObjectHandler env = new ObjectHandler(new Object[]{new Object(10,0.47,10)}, 100, 2000);
        env.addStaticObject(new StaticObject(new PositionVector(0,-100), 10));
        env.startSimulation();
        env.printResults();
    }
}
