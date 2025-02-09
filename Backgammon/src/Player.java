import java.awt.Color;

public class Player
{
private int eatenpins;
private int score;
private boolean eatingmode;
private Color c;

	public Player(Color co)
	{
	  this.c=co;
      this.eatenpins=0;
      this.score=0;
      this.eatingmode=false;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}

	public int getEatenpins() {
		return eatenpins;
	}

	public void setEatenpins(int eatenpins) {
		this.eatenpins = eatenpins;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isEatingmode() {
		return eatingmode;
	}

	public void setEatingmode(boolean eatingmode) {
		this.eatingmode = eatingmode;
	}

}
