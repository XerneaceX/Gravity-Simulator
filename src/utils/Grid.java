package utils;

import main.objectHandling.object.Object;

import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
    private ArrayList<main.objectHandling.object.Object>[][] grid = new ArrayList[GRID_SIZE_VERT][GRID_SIZE_HORIZ];
    public static final int GRID_SIZE_VERT = 2000;
    public static final int GRID_SIZE_HORIZ = 1000;
    public static final int SQUARE_SIZE = 100;

    public Grid() {
        System.out.println("Grid initialized: " + grid.length + "x" + grid[0].length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                this.grid[i][j] = new ArrayList<>();
            }
        }
    }

    public void addObject(Object object) {
        updateGridPos(object);
        int[] gridPos = new int[]{(int) object.getPositionInGrid().getX(), (int) object.getPositionInGrid().getY()};
        this.grid[gridPos[1]][gridPos[0]].add(object);
        object.setPositionInGrid(new PositionVector(gridPos[0], gridPos[1]));
    }

    public void removeObject(Object object) {
        int[] gridPos = new int[]{(int) object.getPositionInGrid().getX(), (int) object.getPositionInGrid().getY()};
        System.out.println(gridPos[0] + " " + gridPos[1]);
        this.grid[gridPos[1]][gridPos[0]].remove(object);
    }

    public void print() {
        System.out.println(Arrays.deepToString(grid));
    }

    public boolean updateGridPos(Object object) {
        PositionVector oldPos = object.getPositionInGrid();
        int gridPosX = (int) object.getPosition().getX();
        int gridPosY = (int) object.getPosition().getY();
        gridPosX /= SQUARE_SIZE;
        gridPosY /= SQUARE_SIZE;
        PositionVector newPos = new PositionVector(gridPosX, gridPosY);
        if (oldPos.equals(newPos)) return false;
        else object.setPositionInGrid(newPos);
        if (newPos.getY() <= 0) object.onFloor = true;
        return true;
    }

    public Object[] getObjectsAtGridPos(PositionVector position) {
        return this.grid[(int) position.getY()][(int) position.getX()].toArray(new Object[0]);
    }

    public ArrayList<Object>[][] getAdjacentObjects(Object object) {
        ArrayList<Object>[][] objects = new ArrayList[3][3];
        int gridPosY = (int) object.getPositionInGrid().getY();
        int gridPosX = (int) object.getPositionInGrid().getX();
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[0].length; j++) {
                if (gridPosX+i-1 >= 0 && gridPosY+j-1 >= 0) {
                    objects[i][j] = grid[gridPosY+j-1][gridPosX+i-1];
                }
            }
        }
        if (objects[1][1] != null) objects[1][1].remove(object);
        return objects;
    }

    public void moveInGrid(Object object) {
        if (object.getPositionInGrid().getY() > 0) {
            removeObject(object);
            addObject(object);
        }
    }

    public static Object[] asSimpleArray(ArrayList<Object>[][] objects) {
        ArrayList<Object> simpleArray = new ArrayList<>();
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < objects[0].length; j++) {
                if (objects[i][j] != null) {
                    simpleArray.addAll(objects[i][j]);
                }
            }
        }
        return simpleArray.toArray(new Object[0]);
    }
}
