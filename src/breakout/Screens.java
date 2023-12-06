package breakout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

@SuppressWarnings("static-access")
public class Screens {
	static Font title = new Font("SANS_SERIF", Font.BOLD, 25);
	static Font text = new Font("SANS_SERIF", Font.PLAIN, 17);
	static Font num = new Font("SERIF", Font.BOLD, 50);

	public void drawSplash(Graphics2D win, Scoreboard myScore) {
		win.setColor(Color.WHITE);
		win.setFont(title);
		win.drawString("Breakout by Chelsea Wen", 200, 100);
		win.drawString("Left/Right arrow keys to move paddle!", 150, 150);
		win.setFont(text);
		win.drawString("Use the paddle to prevent the ball from escaping", 200, 250);
		win.drawString("and try to break all the bricks shown on screen!", 200, 275);
		win.drawString("When you clear a level, you'll progress to the next.", 200, 300);
		win.setColor(Color.BLUE.brighter());
		win.drawString("Use UP/DOWN arrow keys to select level, then hit ENTER to play.", 130, 450);
		win.setFont(text);
		win.drawString("Difficulty Level: " + myScore.getLevel(), 300, 500);
	}

	public void drawGame(Graphics2D win, Rectangle myBall, Rectangle playL, Scoreboard myScore, Brick[][] myBricks) {
		win.setColor(Color.red);
		win.fillOval((int) myBall.getX(), (int) myBall.getY(), (int) myBall.getWidth(), (int) myBall.getHeight());
		win.setColor(Color.GREEN);
		win.fillRect((int) playL.getX(), (int) playL.getY(), (int) playL.getWidth(), (int) playL.getHeight());
		win.setFont(num);	
		for (Brick[] line:myBricks) {
			for (Brick instance:line) {
				instance.draw(win);
			}
		}
		win.setColor(Color.white);
		win.drawString("Score: " + myScore.getScore(), 300, 450);
		win.drawString("Lives: " + myScore.getLives(), 300, 550);
	}

	public void drawGameOver(Graphics2D win, Scoreboard myScore) {
		win.setColor(Color.WHITE);
		win.setFont(title);
		win.drawString("Game Over! You reached LEVEL " + myScore.getLevel(), 100, 150);
		win.setColor(Color.YELLOW);
		win.drawString("You scored " + myScore.getScore() + " points.", 300, 450);
		win.setFont(text);
		win.drawString("Play level again? (Press ENTER)", 300, 500);
	}
	
	public void drawLvOver(Graphics2D win, Scoreboard myScore) {
		win.setColor(Color.WHITE);
		win.setFont(title);
		win.drawString("You passed! You reached LEVEL " + myScore.getLevel(), 100, 150);
		win.setColor(Color.YELLOW);
		win.drawString("You scored " + myScore.getScore() + " points.", 300, 450);
		win.setFont(text);
		win.drawString("Continue to next level? (Press ENTER)", 300, 500);
	}

}


