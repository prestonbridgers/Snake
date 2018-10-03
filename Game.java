import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

	private Player p;

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
						p.setIsGoingUp(true);
						p.setIsGoingDown(false);
						p.setIsGoingLeft(false);
						p.setIsGoingRight(false);
						break;
					case 65: //A
					case 37: //left_arrow
						p.setIsGoingUp(false);
						p.setIsGoingDown(false);
						p.setIsGoingLeft(true);
						p.setIsGoingRight(false);
						break;
					case 83: //S
					case 40: //down_arrow
						p.setIsGoingUp(false);
						p.setIsGoingDown(true);
						p.setIsGoingLeft(false);
						p.setIsGoingRight(false);
						break;
					case 68: //D
					case 39: //right_arrow
						p.setIsGoingUp(false);
						p.setIsGoingDown(false);
						p.setIsGoingLeft(false);
						p.setIsGoingRight(true);
						break;
				}
			}
			public void keyReleased(KeyEvent e) {}
			public void keyTyped(KeyEvent e) {}
		});
		requestFocus();

		p = new Player(GRID_WIDTH / 2, GRID_HEIGHT / 2);
	}

	/**
	 *	The game loop where all the stuff gets updated and everything is redrawn to the screen.
	*/
	public void go()
	{
		while(true)
		{
			p.move();

			repaint();
			try
			{
				Thread.sleep(200);
			} catch(InterruptedException e)
			{
				e.printStackTrace();
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

		p.draw(g);
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