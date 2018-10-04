import java.io.*;

/**
 *	This class holds the score which is serializable meaning the state of this object can be saved and loaded later.
 *	@author Curt Bridgers
 *	@version 10/4/2018
 */
public class Score implements Serializable
{
	private int score = 0;
	private int highscore;

	public static final String scoreFile = "highscore.ser";

	/**
	 *	Getter for the score field.
	 *	@return score
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 *	Getter for the highscore field.
	 *	@return highscore
	 */
	public int getHighscore()
	{
		return highscore;
	}

	/**
	 *	Checks if the current score is a new highscore and sets the highscore variable appropriately.
	 */
	public void setHighscore()
	{
		if(score > highscore)
		{
			highscore = score;
		}
	}

	/**
	 *	This method increments the score field by 1.
	 */
	public void scored()
	{
		score++;
	}

	/**
	 *	This method sets the score to 0.
	 */
	public void reset()
	{
		score = 0;
	}

	/**
	 *	This static method takes a Score object and serializes it.
	 */
	public static void serialize(Score score)
	{
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(scoreFile));
			out.writeObject(score);
			out.close();
		} catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 *	This method tries to deserialize a serialized Score object if one exists.
	 */
	public static Score deserialize()
	{
		Score score = null;

		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(scoreFile));
			score = (Score) in.readObject();
			in.close();
		} catch(Exception e)
		{
			e.printStackTrace();
		}

		return score;
	}
}