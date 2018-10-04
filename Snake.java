import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *	This class represents the whole snake.
 */
public class Snake
{
	private ArrayList<Segment> snake;

	/**
	 * The constructor just resets the snake as if it were respawned.
	 */
	public Snake()
	{
		reset();
	}

	/**
	 *	This method checks whether it dies (goes out of bounds or eats itself)
	 *	@return true for dead snake, false for live snake.
	 */
	public boolean dies()
	{
		if(snake.get(0).getGridX() < 0 || snake.get(0).getGridX() >= Game.GRID_WIDTH)
		{
			return true;
		}

		if(snake.get(0).getGridY() < 0 || snake.get(0).getGridY() >= Game.GRID_HEIGHT)
		{
			return true;
		}

		for(int i = 1; i < snake.size(); i++)
		{
			if(snake.get(0).getGridX() == snake.get(i).getGridX() &&
				snake.get(0).getGridY() == snake.get(i).getGridY())
			{
				return true;
			}
		}

		return false;
	}

	/**
	 *	This method resets the snake to it's default values and location.
	 */
	public void reset()
	{
		snake = new ArrayList<Segment>();
		for(int i = 0; i < Game.INIT_SNAKE_SIZE; i++)
		{
			snake.add(new Segment((Game.GRID_WIDTH / 2) - i, Game.GRID_HEIGHT / 2));
		}
	}

	/**
	 *	This method calls the goUp method for the head segment of the snake.
	 */
	public void goUp()
	{
		snake.get(0).goUp();
	}

	/**
	 *	This method calls the goDown method for the head segment of the snake.
	 */
	public void goDown()
	{
		snake.get(0).goDown();
	}

	/**
	 *	This method calls the goLeft method for the head segment of the snake.
	 */
	public void goLeft()
	{
		snake.get(0).goLeft();
	}

	/**
	 *	This method calls the goRight method for the head segment of the snake.
	 */
	public void goRight()
	{
		snake.get(0).goRight();
	}

	/**
	 *	This method handles all the movement logic for the snake.
	 */
	public void move()
	{
		for(int i = 0; i < snake.size(); i++)
		{
			snake.get(i).move();
		}

		for(int i = snake.size() - 1; i > 0; i--)
		{
			if(snake.get(i - 1).getGoingUp())
			{
				snake.get(i).goUp();

			} else if(snake.get(i - 1).getGoingDown())
			{
				snake.get(i).goDown();

			} else if(snake.get(i - 1).getGoingLeft())
			{
				snake.get(i).goLeft();

			} else if(snake.get(i - 1).getGoingRight())
			{
				snake.get(i).goRight();
			}
		}
	}

	/**
	 *	Adds a segment to snake based on where the tail of the snake is.
	 */
	public void grow()
	{
		int tailGridX = snake.get(snake.size() - 1).getGridX();
		int tailGridY = snake.get(snake.size() - 1).getGridY();

		snake.add(new Segment(tailGridX - 1, tailGridY));
	}

	/**
	 *	Tests whether or not the head of the snake has collided with a give Food object.
	 *	@return true if it has collided with the food, false if it hasn't collided with the food.
	 */
	public boolean eats(Food f)
	{
		if(snake.get(0).getGridX() == f.getGridX() && snake.get(0).getGridY() == f.getGridY())
		{
			return true;
		}

		return false;
	}

	/**
	 *	This method handles how the snake's segments get drawn to the screen.
	 */
	public void draw(Graphics g)
	{

		for(int i = 0; i < snake.size(); i++)
		{
			snake.get(i).draw(g);
		}
	}
}