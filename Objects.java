/**
 * Responsibilities of class: Creates and holds methods for objects. Used to hold values for collision properties. 
 * 
 * @authors David Joves, Junhyeok "Jun" Oh, Juan Paulo "JP" Reyes
 * 
 * 
 * @References
 * 				Morelli, R., &amp; Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving. <br>
 * 					-Case Study: Game of Pong (Chapter 14)
 * 				https://youtu.be/tHNWIWxRDDA(how to update object position) 
 * 				Frankie, T TowersofHanoiGUI.java 
 * 				Frankie, T Tower.Java (draw object method) 
 * 
 * @Verson 1.0	
 */
package src;

import java.awt.*;

public class Object
{
	private double xPos;
	private double yPos;
	private double xSpeed;
	private double ySpeed;
	private double radius; //objects are drawn as balls
	
	public Object(double xPos, double yPos, double speed, double angle, double radius)
	{
		this.xPos = xPos; 
		this.yPos = yPos;
		this.xSpeed = speed * Math.cos(Math.toRadians(angle)); //magnitude * cos = x 
		this.ySpeed = speed * Math.sin(Math.toRadians(angle));//magnitude * sin = y
		this.radius = radius;
	}
	//getters
	/**
	 * Purpose: Returns x position on Panel
	 * @return
	 */
	public double getXPos()
	{
		return xPos;
	}
	/**
	 *Purpose: Returns y position on panel
	 * @return
	 */
	public double getYPos()
	{
		return yPos;
	}
	/**
	 * Purpose: Returns the x velocity of object
	 * @return
	 */
	public double getXSpeed()
	{
		return xSpeed;
	}
	/**
	 * Purpose: Returns the y velocity of object
	 * @return
	 */
	public double getYSpeed()
	{
		return ySpeed;
	}
	/**
	 * Purpose: Returns the radius of object
	 * @return
	 */
	public double getRadius()
	{
		return radius;
	}
	
	//setters
	/**
	 * Purpose: sets the object X position on panel
	 * @param xPos
	 */
	public void setXPos(double xPos)
	{
		this.xPos = xPos;
	}
	/**
	 * Purpose: sets the object Y position on panel
	 * @param yPos
	 */
	public void setYPos(double yPos)
	{
		this.yPos = yPos;
	}
	/**
	 * Purpose: sets the object X velocity value
	 * @param xSpeed
	 */
	public void setXSpeed(double xSpeed)
	{
		this.xSpeed = xSpeed;
	}
	/**
	 * Purpose: sets the object Y velocity value
	 * @param ySpeed
	 */
	public void setYSpeed(double ySpeed)
	{
		this.ySpeed = ySpeed;
	}
	/**
	 * Purpose: sets the object radius 
	 * @param radius
	 */
	public void setRadius(double radius)
	{
		this.radius = radius; 
	}
	
	//collision methods
	/**
	 * Purpose: returns the mass of our object using density formula 
	 * @return
	 */
	public double getMass() //for our collisions
	{
	     return radius / 100; //altered version of DENSITY FORMULA (dividing by 100 means properly sized for our simulator) 	
	}
	
	/**
	 * Purpose: changes the value position of our object as it is being simulator 
	 */
	public void updateObject() 
	{
		//changes the value of object position
		this.xPos += this.xSpeed;
		this.yPos += this.ySpeed;
	}
	
	/**
	 * Purpose: Draws our object as a circle
	 * @param g
	 */
	public void drawObject(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillOval((int) (xPos - radius), (int) (yPos - radius), (int) (2* radius), (int) (2 * radius));
		//draw circle so edge of object is right at the size of panel
	}
}
