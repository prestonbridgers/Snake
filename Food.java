import java.awt.Graphics;
import java.awt.Color;
import java.util.Random;

/**
 *	This class represents the food in the game.
 */
public class Food
{
	private int gridX;
	private int gridY;
	private int x;
	private int y;

	private int gridXBounds;
	private int gridYBounds;

	private Random rand;

	/**
	 *	The constructor takes values for the boundaries of the food for the random number generator.
	 *	@param xBounds The x boundary.
	 *	@param yBounds The y boundary.
	 */
	public Food(int xBounds, int yBounds)
	{
		gridXBounds = xBounds;
		gridYBounds = yBounds;

		rand = new Random();

		relocate();
	}

	/**
	 *	Getter for gridX.
	 *	@return gridX
	 */
	public int getGridX()
	{
		return gridX;
	}

	/**
	 *	Getter for gridY.
	 *	@return gridY
	 */
	public int getGridY()
	{
		return gridY;
	}

	/**
	 *	This method uses a Random object and the boundaries set in the constructor
	 *	to randomly move the food to a new location on the grid.
	 */
	public void relocate()
	{
		gridX = rand.nextInt(gridXBounds);
		gridY = rand.nextInt(gridYBounds);

		x = gridX * Game.GRID_SIZE;
		y = gridY * Game.GRID_SIZE;
	}

	/**
	 *	This method handles how a Food object gets drawn to the screen.
	 */
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(x, y, Game.GRID_SIZE, Game.GRID_SIZE);
	}
}