package CollisionSandbox;

import java.awt.*;
import CollisionSandbox.Object;

public class PaintRectangle {
	
	
    int minX;
    int maxX;
    int minY;
    int maxY;
    private final Color colorFilled;
    private final Color colorBorder;
    private static final Color DEFAULT_COLOR_FILLED = Color.BLACK;
    private static final Color DEFAULT_COLOR_BORDER = Color.YELLOW;

    
    public PaintRectangle(int x, int y, int width, int height, Color colorFilled, Color colorBorder) {
        minX = x;
        minY = y;
        maxX = x + width - 1;
        maxY = y + height - 1;
        this.colorFilled = colorFilled;
        this.colorBorder = colorBorder;
    }
 
    /**
     * Purpose: Create collisions using the painted rectangle
     * @param wall
     * @param object
     */
	public static void wallCollisions(Rectangle wall, Object object)	{
		//Makes it so the collision takes effect at the edge of the object versus the center or the object
		double currObjectXPos = object.getXPos();
		double currObjectYPos = object.getYPos();
		double objectRadius = object.getRadius();
		double leftBound = wall.getX() + objectRadius;	
		double upperBound = wall.getY() + objectRadius;
		double rightBound = wall.getWidth() - objectRadius;
		double lowerBound = wall.getHeight() - objectRadius;
		if(currObjectXPos < leftBound)	{
			object.setXSpeed(-object.getXSpeed());
			object.setXPos(leftBound);
		}
		else if(currObjectXPos > rightBound)	{
			object.setXSpeed(-object.getXSpeed());
			object.setXPos(rightBound);
		}
		if(currObjectYPos < upperBound)	{
			object.setYSpeed(-object.getYSpeed());
			object.setYPos(upperBound);
		}
		else if(currObjectYPos > lowerBound)	{
			object.setYSpeed(-object.getYSpeed());
			object.setYPos(lowerBound); 
		}
		
	}
    @Override
    public void draw(Graphics g) {
        g.setColor(colorFilled);
        g.fillRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
        g.setColor(colorBorder);
        g.drawRect(minX, minY, maxX - minX - 1, maxY - minY - 1);
    }
}
