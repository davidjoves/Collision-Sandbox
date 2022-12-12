/**
 * Responsibilities of class: Creates a GUI that is able to draw and repaint simulations of circles in a 2D space. 
 * 
 * @authors David Joves, Junhyeok "Jun" Oh, Juan Paulo "JP" Reyes
 * 
 * 
 * @References 	https://www.youtube.com/watch?v=hlgUwhtI44g (drawing our simulator)
 * 				https://en.wikipedia.org/wiki/Elastic_collision(formula for ball collision)
 * 				https://www.youtube.com/watch?v=PdOTxTIR4Ro (formula to detect collision)
 * 				https://www.youtube.com/watch?v=eED4bSkYCB8 (implementing collision detection)		
 * 				https://youtu.be/Kmgo00avvEw (GUI fundamentals)
 * 				https://www.youtube.com/watch?v=OI-TFbHQhtA(for action listeners)
 * 				https://www.folkstalk.com/tech/using-timer-to-repaint-in-the-fixed-time-then-continuing-calculation-with-example/ (for repainting with timer)
 * 				https://www.youtube.com/watch?v=hlgUwhtI44g (repainting with timer example)
 * 
 * 				https://stackoverflow.com/questions/10866762/use-of-overriding-getpreferredsize-instead-of-using-setpreferredsize-for-fix(for getPreferredSize method in draweverything class)
 * 				
 * 				Morelli, R., &amp; Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving. <br>
 * 				Frankie, T. (2022) POGIL Recursion
 * 				Frankie, T TowersofHanoiGUI.java 
 * 				Frankie, T Discs.java 
 * 
 * @Verson 1.0	
 */	

package src;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class CollisionSandbox extends JFrame
{

	private final static ArrayList<Object> objectList = new ArrayList<Object>();
	private static PaintRectangle border;
	private static int borderWidth = 600;
	private static int borderHeight = 600;
	private static Random rand = new Random();
	private Timer start;
	
	private static JPanel sandboxPanel;
	
	private static JPanel addButtonPanel; 
	private static JButton addButton;
	private static JButton demoButton;
	private static JButton resetButton;
	
	private static JPanel controlPanel;
	private static JButton pause;
	private static JButton play; 
	
	private static JPanel customizePanel;
	private static JSlider xSlider;
	private static JLabel xLable;
	private static JSlider ySlider;
	private static JLabel yLable ;
	private static JSlider speedSlider ;
	private static JLabel speedLable;
	private static JSlider angleSlider;
	private static JLabel angleLable;
	private static JSlider radiusSlider;
	private static JLabel radiusLable;
	private static JButton customButton;	
	
	public CollisionSandbox()
	{
		super("Collision Sandbox");
		setSize(900,800);
		
		createSandboxPanel(borderWidth, borderHeight); //simulator
		createAddButtonPanel(); //add or clear simulator
		createControlPanel(); // pause or play
		createCustomPanel(); //custom own object
		setButtons(); //add each button is a listener
		
		
		
		setLayout(new BorderLayout(20,20));
		add(controlPanel,BorderLayout.NORTH);
		getContentPane().add(sandboxPanel,BorderLayout.CENTER);
		add(addButtonPanel,BorderLayout.EAST);
		add(customizePanel,BorderLayout.WEST);
		
	    setVisible(true);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	
	/**
	 * Purpose: Creates panel for collision simulator
	 * @param height
	 * @param width
	 */
	public void createSandboxPanel(int height, int width)

	{
		sandboxPanel = new JPanel();
		borderWidth = width;
		borderHeight = height;
		
		border = new PaintRectangle(0,0, borderWidth, borderHeight, Color.BLACK); //creates and sets rectangle object
		DrawEverything draw = new DrawEverything();//use class to draw rectangle and object
		
		sandboxPanel.add(draw);	//add all drawings in panel 
		
		
	}
	
	/**
	 * Responsibilities of class: Holds methods to draw everything from other classes
	 * @author David Joves, Junhyeok "Jun" Oh, Juan Paulo "JP" Reyes
	 *
	 */
 	private class DrawEverything extends JPanel
 	{
 		@Override
 		/**
 		 * Purpose: draws rectangle and objects in this class
 		 */
 		public void paintComponent(Graphics g)
 		{
 			border.draw(g);
 			for(Object object : objectList)
 			{
 				//draw each object in list
 				object.drawObject(g);
 			}
 		}
 	
	@Override
	/**
	 * Purpose: Resizes sandbox panel to fit with JFrame
	 *
	 */
		public Dimension getPreferredSize()
		{
			return (new Dimension(borderWidth,borderHeight));
		}

 	}
	
 	/**
 	 * Purpose: Create a panel to add a random ball object, collision demo and a reset option
 	 */
	public void createAddButtonPanel()
	{
		addButtonPanel = new JPanel();
		addButton = new JButton("Add Random Ball");
		demoButton = new JButton("CollisionDemo");
		resetButton = new JButton("Reset");
		
		addButtonPanel.setLayout(new GridLayout(3,0));
		addButtonPanel.add(addButton);
		addButtonPanel.add(demoButton);
		addButtonPanel.add(resetButton);
		
		
	}
	
	/**
	 * Purpose: Create a panel to pause and play our program
	 */
	public void createControlPanel()
	{
		controlPanel = new JPanel();
		
	 	this.start = new Timer(15, new ActionListener() 
	 	{
		@Override
			public void actionPerformed(ActionEvent e)
			{
				collide();
				repaint();	
			};
	 	});
	 	
	 	start.start();
	
	 	pause = new JButton("PAUSE");
	 	play = new JButton("PLAY");

	 	controlPanel.add(play);
	 	controlPanel.add(pause);
//	 	
	}
	
	/**
	 * Purpose: Creates a panel to customize a object using sliders 
	 */
	public void createCustomPanel()
	{
		customizePanel = new JPanel();
		
		xSlider = new JSlider(0,borderWidth);
		xLable = new JLabel("X cord");
		ySlider = new JSlider(0,borderHeight);
		yLable = new JLabel("Y cord");
		speedSlider = new JSlider(-15,15);
		speedLable = new JLabel("Speed:");
		angleSlider = new JSlider(0,360);
		angleLable = new JLabel("angle (degrees)");
		radiusSlider = new JSlider(1,50);
		radiusLable = new JLabel("Radius:");
	
		Dimension sliderSize = new Dimension(100,100);
		
		
		xSlider.setPreferredSize(sliderSize);
		ySlider.setPreferredSize(sliderSize);
		speedSlider.setPreferredSize(sliderSize);
		angleSlider.setPreferredSize(sliderSize);
		radiusSlider.setPreferredSize(sliderSize);
		
		xSlider.setPaintTicks(true);
		ySlider.setPaintTicks(true);
		speedSlider.setPaintTicks(true);
		angleSlider.setPaintTicks(true);
		radiusSlider.setPaintTicks(true);
		
		
		xSlider.setPaintLabels(true);
		ySlider.setPaintLabels(true);
		speedSlider.setPaintLabels(true);
		angleSlider.setPaintLabels(true);
		radiusSlider.setPaintLabels(true);
		
		xSlider.setPaintTrack(true);
		ySlider.setPaintTrack(true);
		speedSlider.setPaintTrack(true);
		angleSlider.setPaintTrack(true);
		radiusSlider.setPaintTrack(true);
		
		
		customButton = new JButton("Add Custom");
		
		customizePanel.setLayout(new GridLayout(11,0));
		
		customizePanel.add(customButton);
		customizePanel.add(xLable);
		customizePanel.add(xSlider);
		
		customizePanel.add(yLable);
		customizePanel.add(ySlider);
		
		customizePanel.add(speedLable);
		customizePanel.add(speedSlider);
		
		customizePanel.add(angleLable);
		customizePanel.add(angleSlider);
		
		customizePanel.add(radiusLable);
		customizePanel.add(radiusSlider);

	}
	
	/**
	 * Purpose: Have objects collide together and set new speeds and xPositions after a collision
	 * @param a
	 * @param b
	 */
	public static void objectCollisions(Object a, Object b)
	{ 
		 double aCurrXSpeed = a.getXSpeed();
		 double aCurrYSpeed = a.getYSpeed();
		 double bCurrXSpeed = b.getXSpeed();
		 double bCurrYSpeed = b.getYSpeed();
		 
		 double aCurrXPos = a.getXPos();
		 double aCurrYPos = a.getYPos();
		 double bCurrXPos = b.getXPos();
		 double bCurrYPos = b.getYPos();
		 
		 
	     double xDist = aCurrXPos - bCurrXPos; //distance between objects center point 
	     double yDist = aCurrYPos - bCurrYPos;
	        
	     double squaredDistance = xDist * xDist + yDist * yDist;
	     double radiusSumSquared = (a.getRadius() + b.getRadius()) * (a.getRadius() + b.getRadius());

	        if (squaredDistance <= radiusSumSquared) //when collision would happen
	        {
	            double dotXSpeed = (aCurrXSpeed - bCurrXSpeed);
	            double dotYSpeed = (aCurrYSpeed - bCurrYSpeed);
	         
	            double dotProduct = xDist * dotXSpeed + yDist * dotYSpeed;
	            
	            if(dotProduct < 0)  //checks if vectors are pointing in opposite direction
	            {
	            	double totalMass = a.getMass() + b.getMass();	        
	   	        	double collisionMassOnA = (2 * b.getMass() / totalMass);
	   	        	double collisionMassOnB = (2 * a.getMass() / totalMass);
	            	
	            	double collisionProduct = (dotProduct / squaredDistance);
	 	            
	 	            double elasticColA = (collisionMassOnA * collisionProduct);
	 	            double elasticColB = (collisionMassOnB * collisionProduct);
	 	            
	 	        	//final speed formulas for any object
	 	        	a.setXSpeed(aCurrXSpeed -(elasticColA * xDist));
	 	            a.setYSpeed(aCurrYSpeed -(elasticColA * yDist));
	 	            b.setXSpeed(bCurrXSpeed + (elasticColB * xDist));
	 	            b.setYSpeed(bCurrYSpeed +(elasticColB * yDist));
	            }
	        }     	
		}

	/**
	 * Purpose: Have each object collide to JPanel walls or with each other
	 */
	public static void collide()
	{
	
		Rectangle panel = new Rectangle(border.getMinX(), border.getMinY(),border.getMaxX(), border.getMaxY());
		
		for(int i = 0; i < objectList.size(); i++)
		{
			PaintRectangle.wallCollisions(panel,objectList.get(i));
			objectList.get(i).updateObject();
					
			for(int j = 0; j < objectList.size(); j++)
			{
	
				objectCollisions(objectList.get(i), objectList.get(j));
				
			}
		}
	

	}

	/**
	 * Purpose: Gives each button in JFrame an ActionListener
	 */
	public void setButtons()
	{
		ActionListener buttonListeners = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == addButton)
				{
					double x = rand.nextDouble();
					double y = rand.nextDouble();
					double speed = rand.nextDouble(1,9);
					double angle = rand.nextDouble(0,360);
					double radius = rand.nextDouble(10,80);
					
					Object rando = new Object(x,y,speed,angle,radius);
					objectList.add(rando);

				}
				
				else if(e.getSource() == demoButton)
				{
					Object obj1 = new Object(0,300,5,0,25);
					Object obj2 = new Object(800,300,-5,0,25);
					objectList.add(obj1);
					objectList.add(obj2);
				}
				
				else if(e.getSource() == resetButton)
				{
					objectList.clear();
				}
				
				else if(e.getSource() == pause)
				{
					start.stop();
				}
				
				else if(e.getSource() == play)
				{
					start.start();
				}
				
				else if(e.getSource() == customButton)
				{
					Object obj = new Object(xSlider.getValue(),ySlider.getValue(),speedSlider.getValue(),angleSlider.getValue(),radiusSlider.getValue());
					objectList.add(obj);

				}
				
			}
		};
		
		addButton.addActionListener(buttonListeners);
		demoButton.addActionListener(buttonListeners);
		resetButton.addActionListener(buttonListeners);
		play.addActionListener(buttonListeners);
		pause.addActionListener(buttonListeners);
		customButton.addActionListener(buttonListeners);
	}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		CollisionSandbox box = new CollisionSandbox();
	}

		
}
	

