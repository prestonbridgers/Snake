import java.awt.Graphics;
import java.awt.Color;


/**
 *	The Player class holds the player's gridX and gridY as well as it's actual x and y locations.
 *	This class also handles player movement with boolean flags.
 *	@author Curt Bridgers
 *	@version 10/3/2018
*/
public class Player
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
	public Player(int locX, int locY)
	{
		gridX = locX;
		gridY = locY;

		x = gridX * Game.GRID_SIZE;
		y = gridY * Game.GRID_SIZE;
	}

	/**
	 *	Setter for isGoingUp
	 *	@param up
	*/
	public void setIsGoingUp(boolean up)
	{
		isGoingUp = up;
	}

	/**
	 *	Setter for isGoingDown
	 *	@param down
	*/
	public void setIsGoingDown(boolean down)
	{
		isGoingDown = down;
	}

	/**
	 *	Setter for isGoingLeft
	 *	@param left
	*/
	public void setIsGoingLeft(boolean left)
	{
		isGoingLeft = left;
	}

	/**
	 *	Setter for isGoingRight
	 *	@param right
	*/
	public void setIsGoingRight(boolean right)
	{
		isGoingRight = right;
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

		//System.out.printf("Grid:   (%d, %d\n", gridX, gridY);	//DEBUG
		//System.out.printf("Actual: (%d, %d)\n", x, y);		//DEBUG
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
