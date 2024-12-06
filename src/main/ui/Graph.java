package main.ui;

import utils.PositionVector;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class Graph extends JPanel {
    public static final int POSITION_X = 300;
    public static final int POSITION_Y = 0;
    public static final int WIDTH = 900;
    public static final int HEIGHT = 800;
    public static final int PADDING = 75;
    private PositionVector[][] positionVectors;
    private int simTime;
    private DecimalFormat format = new DecimalFormat("#.##");

    protected enum GraphType {
        PositionTime, HeightTime
    }

    public Graph(int simTime) {
        this.simTime = simTime;
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setBounds(POSITION_X, POSITION_Y, WIDTH, HEIGHT);
        this.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
        repaint();
    }

    public void setPositionVectors(PositionVector[][] positionVectors) {
        this.positionVectors = positionVectors;
    }

    public void updateGraph(GraphType type) {
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);


        if (positionVectors != null) {
            int p10 = positionVectors.length / simTime;
            int deltaX = positionVectors.length / (WIDTH - PADDING * 2);
            // y axis
            for (int i = 0; i <= HEIGHT; i += 100) {
                g.drawString(String.valueOf((positionVectors[0][0].getY() / 2 + i)), 10, (int) (HEIGHT - i));
            }

            for (int i = 0; i <= simTime; i++) {
                g.drawString(format.format((long) i *p10/(positionVectors.length/simTime)), i*((WIDTH-PADDING*2)/(simTime))+PADDING, (HEIGHT - 100));
            }

            for (int i = 0; i < positionVectors.length; i++) {
                for (int j = 0; j < positionVectors[0].length; j++) {
                    PositionVector current = positionVectors[i][j];
                    g.fillRect(i / deltaX + PADDING, (int) (HEIGHT - current.getY() / 2), 2, 2);
                }
            }
        }
    }
}
