public class UserObject 
{
	//FIELDS

    private String name; 
	private double mass = 1;
	private double xVel = 0;
	private double yVel = 0;
	private double xPos; 
	private double yPos; 
	

    //Constructor 

    public UserObject(String name, double mass, double xVel, double yVel, double xPos, double yPos)
    {   
        this.setName(name);
        this.setMass(mass);
        this.setXVel(xVel);
        this.setYVel(yVel);
        this.setXPos(xPos);
        this.setYPos(yPos);
    }

    //SETTERS
    public void setName(String name)
    {
        this.name = name;
    }
	public void setMass(double mass)
	{
		this.mass = mass; 
	}
    
    public void setXVel(double xVel)
    {
        this.xVel = xVel; 
    }
    public void setYVel(double yVel)
    {
        this.yVel = yVel; 
    }
    public void setXPos(double xPos)
    {
        this.xPos = xPos; 
    }
    public void setYPos(double yPos)
    {
        this.yPos = yPos; 
    }
    //GETTERS 
    public String getName(String name)
    {
        return name;
    }
    public double getMass()
    {
        return mass; 
    }
    
    public double getXVel()
    {
        return xVel; 
    }
    public double getYVel()
    {
        return yVel; 
    }
    public double getXPos()
    {
        return xPos; 
    }
    public double getYPos()
    {
        return yPos; 
    }
    
   
    




}
