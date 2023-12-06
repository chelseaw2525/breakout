package breakout;

import java.awt.Color;
import java.awt.Graphics2D;

@SuppressWarnings({"serial" })
public class Particle extends Brick {
	static Particle[] particles;
	private int dX;
	private int dY;
	
	public Particle(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c, 1);
	}
	
	public static void makeParticles(Brick b) {
		particles = new Particle[40];
		int x = (int) b.getCenterX();
		int y = (int) b.getCenterY();
		int partPerRow = 8;
		int rowPerArr = 5;
		int n= 1;
		for (int i =0; i < particles.length; i++) {
			particles[i] = new Particle(x, y, (int)(b.getWidth()/partPerRow), (int)(b.getHeight()/rowPerArr), b.getCol());
			if (n % partPerRow == 0 ) {
				x = (int)b.getX();
				y += (int)b.getHeight()/rowPerArr;
			}
			n++;
		}	
	}
	
	public void move() {
		if (Math.random() < .5) {
			dX = 1;
		}
		else {
			dX = -1;
		}
		if (Math.random() < .5) {
			dY = 1;
		}
		else {
			dY = -1;
		}
		this.x += dX;
		this.y += dY;
	}
	
	public static void moveParticles() {
		for (Particle p: particles) {
			p.move();
		}
	}

	public void draw(Graphics2D pb) {	
		pb.setColor(this.getCol());
		pb.fill(this);
	}
	
	public static void drawParticles(Graphics2D pb) {
		for (Particle p: particles) {
			p.draw(pb);
		}
	}
}


