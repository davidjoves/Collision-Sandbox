/**
 * Responsibilities of class: Creates border for a JPanel to experience collisions 
 * 
 * @authors David Joves, Junhyeok "Jun" Oh, Juan Paulo "JP" Reyes
 * 
 * 
 * @References
 * 				Morelli, R., &amp; Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving. <br>
 * 					-Case Study: Game of Pong (Chapter 14)
 * 				Frankie, T Tower.Java (draw object method) 
 * 
 * @Verson 1.0	
 */

package CollisionSandbox.src;

import java.awt.*;

import CollisionSandbox.src.Object;

public class PaintRectangle 
{
    int minX, maxX, minY, maxY;
    private Color colorFilled =  Color.BLACK;

    public PaintRectangle(int x, int y, int width, int height, Color colorFilled) 
    {
        minX = x;
        minY = y;
        maxX = x + width - 1;
        maxY = y + height - 1;
        this.colorFilled = colorFilled;
    }
 
    /**
     * Purpose: Create collisions using a rectangle object
     * @param wall
     * @param object
     */
	public static void wallCollisions(Rectangle wall, Object object)
	{
		//create bounds so it hits edge of object and not center
	
		double currObjectXPos = object.getXPos();
		double currObjectYPos = object.getYPos();
		double objectRadius = object.getRadius();
		
		double leftBound = wall.getX() + objectRadius;	
		double upperBound = wall.getY() + objectRadius;
		double rightBound = wall.getWidth() - objectRadius;
		double lowerBound = wall.getHeight() - objectRadius;
		
		
		if(currObjectXPos < leftBound)
		{
			object.setXSpeed(-object.getXSpeed());
			object.setXPos(leftBound);
		}
		else if(currObjectXPos > rightBound)
		{
			object.setXSpeed(-object.getXSpeed());
			object.setXPos(rightBound);
		}
		
		
		if(currObjectYPos < upperBound)
		{
			object.setYSpeed(-object.getYSpeed());
			object.setYPos(upperBound);
		}
		else if(currObjectYPos > lowerBound)
		{
			object.setYSpeed(-object.getYSpeed());
			object.setYPos(lowerBound); 
		}
		
	}
    
	/**
	 * Purpose: draw a our rectangle object
	 * @param g
	 */
	public void draw(Graphics g) 
    {
        g.setColor(colorFilled);
        g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
        g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
    }
}
