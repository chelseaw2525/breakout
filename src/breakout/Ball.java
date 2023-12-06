package breakout;

import java.awt.Rectangle;

import utilities.GDV5;
//import utilities.Sound;

@SuppressWarnings("serial")
public class Ball extends Rectangle{
	
	private int speeds[] = {7, 8}; 			//possible ball speeds for randomization
	private static int centerX = (700/2 - 20);			//center of screen - offset for ball
	private static int centerY = 750;
	private static int placeX = centerX;
	private static int placeY = centerY;
	//private static int lvmod = 0;			//level mod is same for whole game

	
	public Ball(int size) {
		super(placeX, placeY, size, size);
	}
	/*
	static int lvmed = 1;
	static int lvhd = 3;
	
	public String level(boolean used) {		//how fast is ball?
		if (BreakoutRunner.KeysPressed[KeyEvent.VK_1]) {
			lvmod = 0;
			return "easy";
		}
		if (BreakoutRunner.KeysPressed[KeyEvent.VK_2]) {
			lvmod = lvmed;
			return "medium";
		}
		else if (BreakoutRunner.KeysPressed[KeyEvent.VK_3]) {
			lvmod = lvhd;
			return "hard";
		}
		lvmod = 0;
		return "default (easy)";
	}
	*/
	
	public int randomDirect() {					//returns random direction, below gives magnitude --> vector!
		int random = (int)(Math.random()*2);
		if (random == 0){
			random -= 1;
		}
		return random;
	} 
	
	public int getSpeedX() {
		return randomDirect() * (speeds[(int)(Math.random()*2)]);
	}
	
	public int getSpeedY() {
		return -1 *(speeds[(int)(Math.random()*2)]);
	}
	
	int speedX = getSpeedX();			// change in x
	int speedY = getSpeedY();			// change in y
	
	public void recenter() {
		speedX = 0;
		speedY = 0;
		this.x = centerX;				//back to center
		this.y = centerY;				//use this cuz it's more direct control over ball positioning
	}
	
	public int speedUp(int speed) {
		if (combo >= 2 && speed <= 12) {
			if (speed > 0) {
				speed += 1;
			}
			else if (speed < 0) {
				speed -= 1;
			}
		combo = 0;
		return speed;
		}
		else {
			return speed;
		}
	}
	
	private int counter = 0;
	public int combo = 0;
	private boolean hitBrick = false;
	public void move(boolean start, Rectangle L, boolean hitPad, Scoreboard sb, Brick[][] myBricks ) {    //  		see below
		if (start && counter >= 120)	{
			speedX = speedUp(speedX);
			speedY = speedUp(speedY);
			if (this.getMinX() <= 0) {									
				//Sound.playSound("boing.wav");
				speedX *= -1;
			}
			if (this.getMaxX() >= 700) {								// -25 is based on top left corner of ball				
				//Sound.playSound("boing.wav");
				speedX *= -1;
			} 
			if (this.getMinY() < 0) {
				//Sound.playSound("boing.wav");
				speedY *= -1;											// reverses direction of velocity!
			} 
			if (this.getMaxY() > 800) {
				combo = 0;
				recenter();
				sb.loseLives();
				counter = 0;
			}		
			if (this.intersects(L) && hitPad) {
				//Sound.playSound("boing.wav");
				combo = 0;
				speedY *= -1;
				if (this.getCenterX() - L.getCenterX() < -75) {
					speedX = -1* Math.abs(speedX);
				}
				else if (this.getCenterX() - L.getCenterX() > 75) {
					speedX = Math.abs(speedX);
				}
			}	
			for (int r = 0; r < myBricks.length; r++) {
				for (int col = 0; col < myBricks[r].length; col++) {
					if (this.intersects(myBricks[r][col])) {
						//Sound.playSound("shatter.wav");  //add shatter effect and sound
						combo++;
						//lvmod = (Scoreboard.getLevel() - r);
						int result = GDV5.collisionDirection(myBricks[r][col], this, this.speedX, this.speedY);
						if (result % 2 == 1) {
							speedY *= -1;
						}
						else {
							speedX *= -1;
						}
						Particle.makeParticles(myBricks[r][col]);
						Brick.breakBrick(myBricks[r][col]);
						hitBrick = true;
						Scoreboard.addLives();
					}
				}
			}
			//hitBrick = false;						
			this.setLocation(this.x +=speedX, this.y +=speedY);
		}
		else {							//returns ball to center
			System.out.println("fdkjadlfkjadslskjadsfjs");
			recenter();
			counter++;
			speedX = getSpeedX();
			speedY = getSpeedY();
		}
	}
	
	public int returnDX() {
		return speedX ;
	}
	
	public int returnDY() {
		return speedY;
	}

	public boolean isHitBrick() {
		return hitBrick;
	}

}


