package CollisionSandbox.src;

import java.awt.*;

public class Object
{
	private double xPos;
	private double yPos;
	private double xSpeed;
	private double ySpeed;
	private double radius;
	
	
	public Object(double xPos, double yPos, double speed, double angle, double radius)
	{
		this.xPos = xPos; 
		this.yPos = yPos;
		this.xSpeed = speed*Math.cos(Math.toRadians(angle));
		this.ySpeed = speed*Math.sin(Math.toRadians(angle));
		this.radius = radius;
	}
	
	public double getXPos()
	{
		return xPos;
	}
	
	public double getYPos()
	{
		return yPos;
	}
	
	public double getXSpeed()
	{
		return xSpeed;
	}
	
	public double getYSpeed()
	{
		return ySpeed;
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	public void setXPos(double xPos)
	{
		this.xPos = xPos;
	}
	
	public void setYPos(double yPos)
	{
		this.yPos = yPos;
	}
	
	public void setXSpeed(double xSpeed)
	{
		this.xSpeed = xSpeed;
	}
	
	public void setYSpeed(double ySpeed)
	{
		this.ySpeed = ySpeed;
	}
	
	public void setRadius(double radius)
	{
		this.radius = radius; 
	}
	
	public double getMass()
	 {
	     return 2*radius*radius*radius/1000f;
	 }
	
	public void updateObject() 
	{
		this.xPos += this.xSpeed;
		this.yPos += this.ySpeed;
	}
	
	public void drawObject(Graphics graphic)
	{
		graphic.setColor(Color.YELLOW);
		graphic.fillOval((int) (xPos - radius), (int) (yPos - radius), (int) (2* radius), (int) (2 * radius));
	}
}
