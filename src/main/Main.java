package main;

import main.objectHandling.object.Object;
import main.objectHandling.ObjectHandler;
import main.objectHandling.object.StaticObject;
import utils.PositionVector;
import utils.Vector;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ObjectHandler env = new ObjectHandler(new Object[]{new Object(100, 0.2,1, new Vector(0,0), new PositionVector(1000,1000))}, 10, 100);
        env.addStaticObject(new StaticObject(new PositionVector(1000,900), 10));
        env.startSimulation();
        env.printResults();
    }
}