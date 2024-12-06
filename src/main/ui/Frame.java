package main.ui;

import main.objectHandling.ObjectHandler;
import main.objectHandling.object.Object;
import main.objectHandling.object.StaticObject;
import utils.PositionVector;
import utils.Vector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Frame extends javax.swing.JFrame implements Runnable, ActionListener {
    JPanel userControls = new JPanel();
    ArrayList<Rectangle> objects = new ArrayList<>();
    int HZ = 1000;
    int simTime = 10; //s
    Graph displayPanel = new Graph(simTime);
    PositionVector[][] positionLog;

    public Frame() {
        ObjectHandler env = new ObjectHandler(new main.objectHandling.object.Object[]{new Object(100, 0.47, 10, new Vector(0, 0), new PositionVector(600, 1000))}, simTime, HZ);
        env.addStaticObject(new StaticObject(new PositionVector(600, 900), 10));

        objects.add(new Rectangle());

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(1250, 850);
        this.setVisible(true);


        // user controls
        Label controlsLabel = new Label("Controls");
        controlsLabel.setForeground(Color.black);
        controlsLabel.setBounds(125, 10, 100, 20);
        Button button = new Button("Start Simulation");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (positionLog == null) {
                    env.startSimulation();
                    positionLog = env.getPositionLog();
                    displayPanel.setPositionVectors(positionLog);
                    displayPanel.updateGraph(Graph.GraphType.HeightTime);
                }
            }
        });
        button.setBounds(50, 50, 200, 50);
        userControls.setBackground(Color.lightGray);
        userControls.setBounds(new Rectangle(0, 0, 300, 800));
        userControls.setLayout(null);
        userControls.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        userControls.add(controlsLabel);
        userControls.add(button);
        this.add(userControls);
        this.add(displayPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void run() {
    }
}
