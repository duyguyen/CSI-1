package Assigment_8_Fractal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
* Program name: Fractal - Drawing Lo Feather Fractal in two ways 1. Recursive 2.Iterative
* Program name: Line - create a line object.
* Author: Thanh D. Nguyen.
* Date: 11/15/2018.
* Idea source: From the textbook (p773). I did some small modify on that example to come up with my own version.
* Discuss between recursive and iterative: Self similarity and scaling is much easier than the other because it just repeatedly
*   call itself until the ending of the condition. With iterative, we have to create multiple temporary variables to store
*   necessary values from the current loop in order to reuse them for the next loop.
*
* */

public class Fractal extends JPanel {

    // == constants ==
    private ArrayList<Line> lines = new ArrayList<Line>(); // list of lines after each level
    private int level;
    private int type;

    // == driver ==
    public static void main(String[] args) {
        Fractal fractal = new Fractal();
    }

    // == constructor ==
    public Fractal() {
        setBackground(Color.WHITE);
        userInput(); // set level and type for the program from user input.
        buildJframe(); //  a J-frame, which will contain the Lo Feather Fractal.
    }

    // ==  public methods ==
    @Override
    public void paintComponent(Graphics gg) {

        super.paintComponent(gg);
        Graphics2D g = (Graphics2D) gg; // convert from graphics to graphics-2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        draw(g); // draw the fractal
    }

    public void buildJframe() { // set up the J-Frame for the program
        JFrame application = new JFrame();
        application.add(this, BorderLayout.CENTER);
        setBackground(new Color(242, 243, 244));
        application.setPreferredSize(new Dimension(440, 440));
        application.pack();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setLocationRelativeTo(null);
        application.setTitle("FRACTAL");
        application.setVisible(true);
    }

    // == private methods ==

    private void draw(Graphics g) {
        if (type == 1) { // Lo Feather type 1
            drawFractal(level, 50, 100, 290, 200, g);
            g.drawString("RECURSIVE VERSION", 50, 50);
        } else if (type == 2) { // Lo Feather type 2
            drawFractalIterative(level, 50, 100, 290, 200, g);
            g.drawString("ITERATIVE VERSION", 50, 50);
        } else { // report type not found if user input is out of values.
            throw new IllegalArgumentException("Type doesn't exist!");
        }
    }

    private void userInput() { // use Scanner class to retrieve user input.
        System.out.printf("%s%s%s", "*Enter Type ", "(1 For Recursive - ", " 2 For Iterative): ");

        Scanner scanner = new Scanner(System.in);
        type = scanner.nextInt();

        System.out.printf("%s", "*Enter Level: ");
        level = scanner.nextInt();
    }

    private ArrayList<Line> buildLine(Line line) { // create three lines when pass in 1 line.
        ArrayList<Line> lines = new ArrayList<Line>();

        // build the center point
        int centerX = (line.getStartX() + line.getEndX()) / 2;
        int centerY = (line.getStartY() + line.getEndY()) / 2;

        // build the right angle
        int rightAngleX = line.getStartX() + (centerX - line.getStartX()) / 2 - (centerY - line.getStartY()) / 2;
        int rightAngleY = line.getStartY() + (centerY - line.getStartY()) / 2 + (centerX - line.getStartX()) / 2;

        // create lines from these points.
        Line l1 = new Line(line.getStartX(), line.getStartY(), rightAngleX, rightAngleY);
        Line l2 = new Line(line.getEndX(), line.getEndY(), rightAngleX, rightAngleY);
        Line l3 = new Line(centerX, centerY, rightAngleX, rightAngleY);

        // add every line to the lines array.
        lines.add(l1);
        lines.add(l2);
        lines.add(l3);

        return lines;
    }


    private void iterative(int level, int xA, int yA, int xB, int yB) { // create lines in the fractal by looping.

        ArrayList<Line> tempLines = new ArrayList<Line>(); // a temporary array to store new lines.
        Line line = new Line(xA, yA, xB, yB); // the base line.

        // clear lines array in case the array is not empty.
        if (lines.size() > 1) {
            lines.clear();
        }

        lines.add(line); // add the base line to the lines array.
        while (level > 0) {
            for (Line l : lines) {
                tempLines.addAll(buildLine(l)); // build new lines from each line in the array, and add them to the temporary array.
            }

            lines.clear(); // clean all lines in the lines array.
            lines.addAll(tempLines); // add all lines in temporary array to lines array to prepare for the next loop.
            tempLines.clear(); // clean temporary array to prepare for the next loop.
            level--;
        }

    }

    private void drawFractalIterative(int level, int xA, int yA, int xB, int yB, Graphics g) { // draw all line in the lines array.
        g.setColor(new Color(238, 156, 74));
        iterative(level, xA, yA, xB, yB);

        for (Line l : lines) {
            g.drawLine(l.getStartX(), l.getStartY(), l.getEndX(), l.getEndY());
        }
    }


    private void drawFractal(int level, int xA, int yA, int xB, int yB, Graphics g) { // Lo Feather Fractal by recursive.
        // base case: draw a line connecting two given points
        g.setColor(new Color(156, 235, 67)); // set color for the line.
        if (level == 0) {
            g.drawLine(xA, yA, xB, yB);
        } else // recursion step: determine new points, draw next level
        {
            // calculate midpoint between (xA, yA) and (xB, yB)
            int xC = (xA + xB) / 2;
            int yC = (yA + yB) / 2;

            // calculate the fourth point (xD, yD) which forms an
            // isosceles right triangle between (xA, yA) and (xC, yC)
            // where the right angle is at (xD, yD)
            int xD = xA + (xC - xA) / 2 - (yC - yA) / 2;
            int yD = yA + (yC - yA) / 2 + (xC - xA) / 2;

            // recursively draw the Fractal
            drawFractal(level - 1, xA, yA, xD, yD, g);
            drawFractal(level - 1, xC, yC, xD, yD, g);
            drawFractal(level - 1, xB, yB, xD, yD, g);
        } // end else
    } // end method drawFractal 
}

class Line { // use to built a line object.

    // == constants ==
    private int startX, startY, endX, endY;
    // == constructor ==
    public Line(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    // == public methods ==
    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}
