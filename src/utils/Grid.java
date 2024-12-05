package utils;

import main.objectHandling.object.Object;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    private ArrayList<main.objectHandling.object.Object>[][] grid = new ArrayList[GRID_SIZE_VERT][GRID_SIZE_HORIZ];
    public static final int GRID_SIZE_VERT = 2000;
    public static final int GRID_SIZE_HORIZ = 1000;
    public static final int SQUARE_SIZE = 500;

    public Grid() {
        System.out.println("Grid initialized: " + grid.length + "x" + grid[0].length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                this.grid[i][j] = new ArrayList<>();
            }
        }
    }

    public void addObject(Object object) {
        int[] gridPos = getGridPos(object);
        this.grid[gridPos[1]][gridPos[0]].add(object);
    }

    public void print() {
        System.out.println(Arrays.deepToString(grid));
    }

    public int[] getGridPos(Object object) {
        int gridPosX = (int) object.getPosition().getX();
        int gridPosY = (int) object.getPosition().getY();
        System.out.println(gridPosX + " " + gridPosY);
        gridPosX /= SQUARE_SIZE;
        gridPosY /= SQUARE_SIZE;
        return new int[]{gridPosX, gridPosY};
    }

    public Object[] getObjectsAtPos(PositionVector position) {
        return this.grid[(int) position.getY()][(int) position.getX()].toArray(new Object[0]);
    }

    public ArrayList<Object>[][] getAdjacentObjects(Object object) {
        ArrayList<Object>[][] objects = new ArrayList[3][3];
        int gridPosY = getGridPos(object)[1];
        int gridPosX = getGridPos(object)[0];

        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[0].length; j++) {
                objects[i][j] = grid[gridPosY+j-1][gridPosX+i-1];
            }
        }

        return objects;
    }

    public static Object[] asSimpleArray(ArrayList<Object>[][] objects) {
        ArrayList<Object> simpleArray = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[0].length; j++) {
                simpleArray.addAll(objects[i][j]);
            }
        }
        return simpleArray.toArray(new Object[0]);
    }

    public static void main(String[] args) {
        Grid grid = new Grid();
        Object specialObject =  new Object(1, new PositionVector(500,500));
        grid.addObject(specialObject);
        grid.addObject(new Object());
        System.out.println(Arrays.toString(asSimpleArray(grid.getAdjacentObjects(specialObject))));
    }
}
