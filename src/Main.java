import objectHandling.Object;
import objectHandling.ObjectHandler;

public class Main {
    public static void main(String[] args) {
        ObjectHandler env = new ObjectHandler(new Object[]{new Object(20, 0.47, 0.47, new double[]{0,10})}, 10, 1000);
        env.startSimulation();
        env.printResults();
    }
}
