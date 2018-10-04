import java.awt.Graphics;
import java.awt.Color;


/**
 *	The Player class holds the player's gridX and gridY as well as it's actual x and y locations.
 *	This class also handles player movement with boolean flags.
 *	@author Curt Bridgers
 *	@version 10/3/2018
*/
public class Segment
{
	private int gridX;
	private int gridY;
	private int x;
	private int y;

	private boolean isGoingUp = false;
	private boolean isGoingDown = false;
	private boolean isGoingLeft = false;
	private boolean isGoingRight = true;

	/**
	 *	This constructor takes a gridX and gridY and uses that to calculate the actual x and y.
	 *	@param locX The initial x position of the player on the grid.
	 *	@param locY The initial y position of the player on the grid.
	*/
	public Segment(int locX, int locY)
	{
		gridX = locX;
		gridY = locY;

		x = gridX * Game.GRID_SIZE;
		y = gridY * Game.GRID_SIZE;
	}

	/**
	 *	Getter for gridX.
	*/
	public int getGridX()
	{
		return gridX;
	}

	/**
	 *	Getter for gridY.
	*/
	public int getGridY()
	{
		return gridY;
	}

	/**
	 *	Getter for isGoingUp.
	 *	@return isGoingUp
	*/
	public boolean getGoingUp()
	{
		return isGoingUp;
	}

	/**
	 *	Getter for isGoingDown.
	 *	@return isGoingDown
	*/
	public boolean getGoingDown()
	{
		return isGoingDown;
	}

	/**
	 *	Getter for isGoingLeft.
	 *	@return isGoingLeft
	*/
	public boolean getGoingLeft()
	{
		return isGoingLeft;
	}

	/**
	 *	Getter for isGoingRight.
	 *	@return isGoingRight
	*/
	public boolean getGoingRight()
	{
		return isGoingRight;
	}

	/**
	 *	Sets the isGoingUp flag to true and sets the others to false.
	*/
	public void goUp()
	{
		isGoingUp = true;
		isGoingDown = false;
		isGoingLeft = false;
		isGoingRight = false;
	}

	/**
	 *	Sets the isGoingDown flag to true and sets the others to false.
	*/
	public void goDown()
	{
		isGoingUp = false;
		isGoingDown = true;
		isGoingLeft = false;
		isGoingRight = false;
	}

	/**
	 *	Sets the isGoingLeft flag to true and sets the others to false.
	*/
	public void goLeft()
	{
		isGoingUp = false;
		isGoingDown = false;
		isGoingLeft = true;
		isGoingRight = false;
	}

	/**
	 *	Sets the isGoingRight flag to true and sets the others to false.
	*/
	public void goRight()
	{
		isGoingUp = false;
		isGoingDown = false;
		isGoingLeft = false;
		isGoingRight = true;
	}

	/**
	 *	This method handles the movement of the player based on the state of the isGoing flags.
	*/
	public void move()
	{
		if(isGoingUp)
		{
			gridY--;
		} else if(isGoingDown)
		{
			gridY++;
		} else if(isGoingLeft)
		{
			gridX--;
		} else if(isGoingRight)
		{
			gridX++;
		}

		x = gridX * Game.GRID_SIZE;
		y = gridY * Game.GRID_SIZE;
	}

	/**
	 *	This method handles how the player gets drawn to the screen.
	 *	@param g The Graphics object to paint it to the screen with.
	*/
	public void draw(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(x, y, Game.GRID_SIZE, Game.GRID_SIZE);
	}
}
