import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

public class Simulator extends JPanel implements ActionListener {
    private ArrayList<UserObject> objects;
    private final int WIDTH = 500;
    private final int HEIGHT = 1000;
    private Timer timer;

    public Simulator() 
    {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);

        timer = new Timer(1000, this);
        timer.start();

    }

    public double getRadius(UserObject obj) // using density formula we can divide divide mass by pi to get radius of
                                            // some particle
    {
        return (obj.getMass() / Math.PI);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2D = (Graphics2D) g;

        g2D.setPaint(Color.black);

        // draws our objects in the list
        if (objects.size() > 0) {
            for (int i = 0; i < objects.size(); i++) {
                UserObject obj = objects.get(i);
                // Pos - radius = center where we want our oval
                // multiply by two because height and width is diameter
                g2D.fillOval((int) (obj.getXPos() - getRadius(obj)), (int) (obj.getXPos() - getRadius(obj)),
                        (int) getRadius(obj) * 2, (int) getRadius(obj));
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (objects.size() > 0) {
            for (int i = 0; i < objects.size(); i++) {
                UserObject obj = objects.get(i);

                double currX = obj.getXPos();
                double currY = obj.getYPos();

                currX += obj.getXVel();
                currY += obj.getYVel();
                repaint();
            }

        }

    }

}
