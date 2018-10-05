import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
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

	public static final int INIT_SNAKE_SIZE = 3;

	private Food food;
	private Snake snake;

	private Score score;
	private JLabel lblScore;

	private ActionMap am;
	private InputMap im;

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

		food = new Food(GRID_WIDTH, GRID_HEIGHT);
		snake = new Snake();

		score = Score.deserialize();
		if(score == null)
		{
			System.out.println("Didn't load score");
			score = new Score();
		}
		lblScore = new JLabel("Score: " + score.getScore() + "\n               Highscore: " + score.getHighscore());
		lblScore.setFont(new Font("TimesNewRoman", Font.PLAIN, 32));
		lblScore.setForeground(Color.BLUE);
		add(lblScore);

		im = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		am = getActionMap();

		initInputs();
	}

	/**
	 *	This method initializes KeyBindings.
	 */
	private void initInputs()
	{
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "goUp");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), "goUp");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "goDown");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), "goDown");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "goLeft");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), "goLeft");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "goRight");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), "goRight");

		am.put("goUp", new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!snake.getSegment(0).getGoingDown())
				{
					snake.goUp();
				}
			}
		});
		am.put("goDown", new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!snake.getSegment(0).getGoingUp())
				{
					snake.goDown();
				}
			}
		});
		am.put("goLeft", new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!snake.getSegment(0).getGoingRight())
				{
					snake.goLeft();
				}
			}
		});
		am.put("goRight", new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!snake.getSegment(0).getGoingLeft())
				{
					snake.goRight();
				}
			}
		});
	}

	/**
	 *	The game loop where all the stuff gets updated and everything is redrawn to the screen.
	*/
	public void go()
	{
		while(true)
		{
			snake.move();

			if(snake.eats(food))
			{
				snake.grow();
				food.relocate();
				score.scored();
				lblScore.setText("Score: " + score.getScore() + "\n              Highscore: " + score.getHighscore());
			}

			if(snake.dies())
			{
				snake.reset();
				food.relocate();

				score.setHighscore();
				score.reset();
				score.serialize(score);
				lblScore.setText("Score: " + score.getScore() + "\n              Highscore: " + score.getHighscore());
			}

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
	 *	An overriden method from the extended JPanel class that allows drawing things to the screen.
	 *	@param g The graphics object to draw things with.
	*/
	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		snake.draw(g);
		food.draw(g);
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
