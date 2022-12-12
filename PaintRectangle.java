/**
 * Responsibilities of class: Creates border for a JPanel to experience collisions 
 * 
 * @authors David Joves, Junhyeok "Jun" Oh, Juan Paulo "JP" Reyes
 * 
 * 
 * @References
 *				https://www.youtube.com/watch?v=tHNWIWxRDDA&t=1s (for wall collisions)
 *				https://youtu.be/qIr2XYZrznI(coding wall)
 * 				Morelli, R., &amp; Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving. <br>
 * 					-Case Study: Game of Pong (Chapter 14)
 * 				Frankie, T Tower.Java (draw object method) 
 * 
 * @Verson 1.0	
 */

package src;

import java.awt.*;

public class PaintRectangle 
{
    private int minX, maxX, minY, maxY;
    private Color colorFilled =  Color.BLACK;

    public PaintRectangle(int x, int y, int width, int height, Color colorFilled) 
    {
        this.minX = x;
        this.minY = y;
        this.maxX = x + width - 1;
        this.maxY = y + height - 1;
        this.colorFilled = colorFilled;
    }
 
    //getters
    /**
     * Purpose: Get the minimum x distance for the wall for our ball to collide
     * @return minX
     */
    public int getMinX()
    {
    	return minX;
    }
    /**
     * Purpose: Get the maximum x distance for the wall for our ball to collide
     * @return minX
     */
    public int getMaxX()
    {
    	return maxX;
    }
    /**
     * Purpose: Get the minimum y distance for the wall for our ball to collide
     * @return minX
     */
    public int getMinY()
    {
    	return minY;
    }
    /**
     * Purpose: Get the maximum y distance for the wall for our ball to collide
     * @return minX
     */
    public int getMaxY()
    {
    	return maxY;
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
        g.fillRect(minX, minY, maxX, maxY);
        g.drawRect(minX, minY, maxX, maxY);
    }
}
