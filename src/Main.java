import objectHandling.Object;
import objectHandling.ObjectHandler;

public class Main {
    public static void main(String[] args) {
        ObjectHandler env = new ObjectHandler(new Object[]{new Object(200,0.47,10, new double[]{8,0})}, 10, 1000);
        env.addFloor(10);
        env.startSimulation();
        env.printResults();
    }
}
