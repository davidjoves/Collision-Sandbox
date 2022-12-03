import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.util.ArrayList;

public class CollisionFrame extends JFrame
{

    private JPanel editorPanel; //edit 
    private JButton addObject; 
    private JButton editObject; 
    private JButton playCollision; 

    private JPanel resetPanel; //reset
    private JButton reset; 

    private JPanel collisionsPanel; 
    private JLabel collisions; 

    private Simulator simulatorPanel; 
    



    public CollisionFrame()
    {
        super("Collision Sandbox");
        simulatorPanel = new Simulator();
        this.add(simulatorPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        // createEditorPanel();
        // createResetPanel();
        // createCollisionPanel();Í

        // add(simulatorPanel, BorderLayout.WEST);
        // add(editorPanel, BorderLayout.CENTER);
        // add(collisionsPanel, BorderLayout.EAST);
        // add(resetPanel, BorderLayout.NORTH);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



        

    }


    // public void createEditorPanel()
    // {
    //     //edit object properties
    // }
    
    // public void createResetPanel()
    // {
    //     //panel to reset
    // }

    // public void createCollisionPanel()
    // {
    //     //panel to read amount of collisions 
    // }

    // public static void main(String[] args)
    // {

    //     CollisionFrame sandbox = new CollisionFrame();

    // }

    public static void main(String[] args)
    {
        new CollisionFrame();
    }
}
