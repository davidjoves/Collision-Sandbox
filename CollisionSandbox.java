package CollisionSandbox.src;

import javax.swing.*;

import CollisionSandbox.src.Object;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
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
	private static JLabel xLable ;
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
		
		createSandboxPanel(borderWidth, borderHeight);
		createAddButtonPanel();
		createControlPanel();
		createCustomPanel();
		setButtons();
		
		setLayout(new BorderLayout(20,20));
		add(controlPanel,BorderLayout.NORTH);
		add(sandboxPanel,BorderLayout.CENTER);
		add(addButtonPanel,BorderLayout.EAST);
		add(customizePanel,BorderLayout.WEST);
		
	    setVisible(true);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	
	public void createSandboxPanel(int height, int width)
	{
		sandboxPanel = new JPanel();
		borderWidth = width;
		borderHeight = height;
		
		border = new PaintRectangle(0,0, borderWidth, borderHeight, Color.BLACK, Color.WHITE);
		
		DrawEverything draw = new DrawEverything();
		sandboxPanel.add(draw);		
		
	}
 	private class DrawEverything extends JPanel
 	{
 		@Override
 		public void paintComponent(Graphics g)
 		{
 			border.draw(g);
 			for(Object object : objectList)
 			{
 				object.drawObject(g);
 			}
 		}
 	
	@Override
		public Dimension getPreferredSize()
		{
			return (new Dimension(borderWidth,borderHeight));
		}

 	}


	
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
	            
	            if(dotProduct < 0)  //checks if vectors are pointing in opposite directions direction
	            {
	            	double totalMass = a.getMass() + b.getMass();	        
	   	        	double collisionMassOnA = (2 * b.getMass() / totalMass);
	   	        	double collisionMassOnB = (2 * a.getMass() / totalMass);
	            	
	            	double collisionProduct = (dotProduct / squaredDistance);
	 	            
	 	            double elasticColA = (collisionMassOnA * collisionProduct);
	 	            double elasticColB = (collisionMassOnB * collisionProduct);
	 	            
	 	        	
	 	        	  a.setXSpeed(aCurrXSpeed -(elasticColA * xDist));
	 	            a.setYSpeed(aCurrYSpeed -(elasticColA * yDist));
	 	            b.setXSpeed(bCurrXSpeed + (elasticColB * xDist));
	 	            b.setYSpeed(bCurrYSpeed +(elasticColB * yDist));
	            }
	        }     	
		}

	public static void collide()
	{
	
		Rectangle panel = new Rectangle(border.minX, border.minY,border.maxX, border.maxY);
		
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

	
	public static void main(String[] args)
	{
		CollisionSandbox box = new CollisionSandbox();
	}

		
}
	

