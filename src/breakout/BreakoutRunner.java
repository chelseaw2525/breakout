package breakout;

import java.awt.Graphics2D;
import java.util.Scanner;
import utilities.GDV5;
//import utilities.Sound;

@SuppressWarnings("serial")
public class BreakoutRunner extends GDV5 {

	Scanner input = new Scanner(System.in);
	Ball myBall = new Ball(24);
	Paddle playL = new Paddle();
	Scoreboard myScore = new Scoreboard();
	Brick[][] myBricks = Brick.makeBricks();
	Screens screen = new Screens();

	private boolean start = false; // ball moving?
	private boolean gameOver = false; // show gameOver screen?
	private boolean splash = true; // show splash screen?
	//private boolean loadTime = false; //temp boolean to control sequencing after end game screen
	private int gameMode;
	
	public static void main(String[] args) {
		BreakoutRunner game = new BreakoutRunner();
		//Sound.loop("defrostening.wav");
		game.start();
	}
	
	// 0 = right, 1 = up, 2 = left, 3 = down controls behavior based on where paddle
	Boolean[] dirHitPad = {false, true, false, false};
	public boolean hitPad() {
		int i = collisionDirection(playL, myBall, myBall.returnDX(), myBall.returnDY());
		return dirHitPad[i];
	}
	
	@Override
	public void update() { // 60 fps
		System.out.println(start + " " + myBall.getCenterY());
		myBall.move(start, playL, hitPad(), myScore, myBricks);
		playL.move(KeysPressed, myBall);
		gameMode = Scoreboard.getOverMode();

	}
	
	//TODO: power-ups and fix the level advancements
	@Override
	public void draw(Graphics2D draw) { // processor speed, apprx. 3000 fps
		if (splash) { // shows splash screen
			myBricks = Brick.makeBricks();
			screen.drawSplash(draw, myScore);
			splash = myScore.showSplash(KeysPressed);
			myScore.gameLevel(KeysPressed);
		}
		else { // gameplay animation
			if (!myScore.endGame() && !gameOver) { //
				start = !gameOver;
				screen.drawGame(draw, myBall, playL, myScore, myBricks);
				if (myBall.isHitBrick()) {
					Particle.drawParticles(draw);
					Particle.moveParticles();
				}
			}
			else if (gameMode == 1) {
				start = false;
				screen.drawLvOver(draw, myScore);
				Scoreboard.loadNewLevel();
				gameOver = myScore.showSplash(KeysPressed);
 			}
			else if (gameMode == 2){
				start = false;
				screen.drawGameOver(draw, myScore);
				Scoreboard.loadSameLevel();
				gameOver = myScore.showSplash(KeysPressed);
			}
			else if (gameMode == 3 && !gameOver) {
				Scoreboard.loadNewLevel();
				myScore.scoreReset();
				myScore.addLevel();
				myBricks = Brick.makeBricks();
			}
			else if (gameMode == 4 && !gameOver) {
				myScore.scoreReset();
				myScore.livesReset();
				myBricks = Brick.makeBricks();
			}
		}
	}  
}

