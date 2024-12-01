package main;

import main.objectHandling.object.Object;
import main.objectHandling.ObjectHandler;
import main.objectHandling.object.StaticObject;

public class Main {
    public static void main(String[] args) {
        ObjectHandler env = new ObjectHandler(new Object[]{new Object(10,0.47,1)}, 100, 1984);
        env.addStaticObject(new StaticObject(new Position(1,-10), 10));
        env.startSimulation();
        env.printResults();
    }
}
