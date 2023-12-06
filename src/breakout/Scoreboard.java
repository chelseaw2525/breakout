package breakout;

import java.awt.event.KeyEvent;

public class Scoreboard {
	private static int score = 0;
	private static int level = 1;
	private static int lives = 3;
//	private boolean roundEnd = false;

	public boolean showSplash(boolean[] KeyType) {
		if (KeyType[KeyEvent.VK_ENTER]) {
			return false;
		}
		return true;
	}
	
	private int counter = 30;
	public void gameLevel(boolean[] KeyType) {
		if (counter >= 20) {
			if (level < 25 && KeyType[KeyEvent.VK_UP]) {
				level++;
				counter = 0;
			}
			if (level > 25 && KeyType[KeyEvent.VK_DOWN]) {
				level--;
				counter = 0;
			}
		}
		counter++;
	}

	
	
	private static int overMode = 0;
	
	public boolean endGame() {
		if (lives <= 0) {
				overMode = 2;
				return true;
			}
		else if (getScore() >= Brick.scoreMax()){
				overMode = 1;
				return true;
			}
		else {
			overMode = 0;
			return false;
		}
	}
	
	public static void loadNewLevel() {
		overMode = 3;
	}
	
	public static void loadSameLevel() {
		overMode = 4;
	}
	
	public static int getOverMode() {
		return overMode;
	}


	public static void addScore(int value) {
		score += value;
	}

	public int getScore() {
		return score;
	}

	public void scoreReset() {
		score = 0;
	}
	
	public void livesReset() {
		lives = 3;
	}

	public static int getLevel() {
		return level;
	}

	public void addLevel() {
		level++;
	}

	public int getLives() {
		return lives;
	}

	public void loseLives() {
		lives--;
	}
	
	public static void addLives() {
		if (score > 1 && score % 200 == 0) { 
			lives++;
		}
	}
	
}


