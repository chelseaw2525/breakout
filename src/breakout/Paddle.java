package breakout;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Paddle extends Rectangle {

	private int slideSpeed = 14;
	private static int width = 200;
	private static int locateX = 650 - width / 2; // y location of paddle (so it can slide up/down)

	public Paddle() { // locateX = 0+5 if left paddle, 1200-30-5 if right paddle
		super(locateX, 780, width, 20);
	}

	private int xEdge = 700;

	public void move(boolean[] KeysPressed, Ball ball) {
		if (KeysPressed[KeyEvent.VK_LEFT] && (this.getX() > 0)) {
			translate(-slideSpeed, 0);
		}
		if (KeysPressed[KeyEvent.VK_RIGHT] && (this.getMaxX() < xEdge)) {
			translate(slideSpeed, 0);
		}
	/*	if (this.getMinX() >= 0 && this.getMaxX() <= xEdge) {
			setLocation((int)ball.getCenterX() - (width/2), (int)this.getY());
		}
		else if (this.getMaxX() >= xEdge){
			setLocation(xEdge - width, (int)this.getY());
		}
		else {
			setLocation (1, (int)this.getY());
		}`*/
	}

	public int getSpeed() {
		return slideSpeed;
	}
}

