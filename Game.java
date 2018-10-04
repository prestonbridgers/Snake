import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *	The Game class is the main hub of the program where the JFrame is created and the gamePanel is added.
 *	@author Curt Bridgers
 *	@version 10/3/2018
*/
public class Game extends JPanel
{
	public static final String NAME = "Snake Game";
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	public static final int GRID_SIZE = 20;
	public static final int GRID_WIDTH = WIDTH / GRID_SIZE;
	public static final int GRID_HEIGHT = HEIGHT / GRID_SIZE;

	private int startingSize = 10;
	private ArrayList<Segment> snake;

	/**
	 *	The constructor where certain variables of the gamePanel are set.
	*/
	public Game()
	{
		Dimension dim = new Dimension(WIDTH, HEIGHT);
		setSize(dim);
		setPreferredSize(dim);
		setMinimumSize(dim);
		setMaximumSize(dim);
		setFocusable(true);

		initSnake();

		addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				int code = e.getKeyCode();

				switch(code)
				{
					case 87: //W
					case 38: //up_arrow
						snake.get(0).goUp();
						break;
					case 65: //A
					case 37: //left_arrow
						snake.get(0).goLeft();
						break;
					case 83: //S
					case 40: //down_arrow
						snake.get(0).goDown();
						break;
					case 68: //D
					case 39: //right_arrow
						snake.get(0).goRight();
						break;
				}
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		requestFocus();
	}

	/**
	 *	Initializes the snake to the default values (such as if it were to respawn or how it
	 * 	would be seen at the start of the game).
	*/
	private void initSnake()
	{
		snake = new ArrayList<Segment>();
		for(int i = 0; i < startingSize; i++)
		{
			snake.add(new Segment((GRID_WIDTH / 2) - i, GRID_HEIGHT / 2));
		}
	}

	/**
	 *	The game loop where all the stuff gets updated and everything is redrawn to the screen.
	*/
	public void go()
	{
		while(true)
		{
			handleSnakeMovement();

			repaint();
			try
			{
				Thread.sleep(100);
			} catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 *	Handles the logic for snake movement as well as calls the move() method on all segments.
	*/
	private void handleSnakeMovement()
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
	 *	An overriden method from the extended JPanel class that allows drawing things to the screen.
	 *	@param g The graphics object to draw things with.
	*/
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for(int i = 0; i < snake.size(); i++)
		{
			snake.get(i).draw(g);
		}

		//Drawing the grid
		///////////////////////////////////////////////////////////////////
		g.setColor(Color.RED);
		for(int x = 0; x < GRID_WIDTH; x++)
		{
			g.drawLine(x * GRID_SIZE, 0, x * GRID_SIZE, HEIGHT);
		}
		for(int y = 0; y < GRID_HEIGHT; y++)
		{
			g.drawLine(0, y * GRID_SIZE, WIDTH, y * GRID_SIZE);
		}
		///////////////////////////////////////////////////////////////////
	}

	/**
	 *	The main method of the program.
	 *	@param args A String array.
	*/
	public static void main(String[] args)
	{
		Game g = new Game();

		JFrame f = new JFrame(NAME);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.add(g);
		f.pack();
		f.setVisible(true);

		g.go();
	}
}
