package breakout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
//import utilities.Sound;
import breakout.Ball;


@SuppressWarnings({ "serial", "unused" })
public class Brick extends Rectangle {
	
	private Color color;
	private static int arrayWidth = 8;
	private int value;
	private static int hexCol = 0x000000;
	private Color myColor = new Color(hexCol, false);
	
	public Brick(int xPos, int yPos, int w, int h, Color c, int val) {
		super(xPos, yPos, w, h);
		color = c;
		value = val;
	}

	public static Color changeColor() {  //make the color the new shade
			Color myColor = new Color(hexCol, false);
			return myColor;
	}
	
	//but i want rgb gamer lights
	public static void shiftColor() {
		hexCol += 0x0011ff;
	}
	
	public static Brick[][] makeBricks() { 
		System.out.println("level: "  + Scoreboard.getLevel());
		int rows = Scoreboard.getLevel();
		int columns = arrayWidth;
		int x = 10;
		int y = 10;
		int padding = 3;
		int pointVal = Scoreboard.getLevel() * 10;
		Brick[][] b = new Brick[rows][columns];  //2d array
		for (int r = 0; r < rows; r++) {
			for (int col = 0; col < columns; col++) {
				b[r][col] = new Brick(x, y, 80, 25, changeColor(), pointVal);
				x += 80 + padding;
			}
			x = 10;
			y += 25 + padding;
			//hexCol += 0x0011ff;
			shiftColor();
			pointVal -= 10;
		}
		return b;
	}
	
	public static void breakBrick(Brick b) {
		b.setLocation(2000,1000);
		Scoreboard.addScore(b.value);
	}
	
	public static int scoreMax() {
		int temp = 0;
		for (int i = 0; i <= Scoreboard.getLevel(); i++) {
			temp += i;
		}
		return (arrayWidth * 10 * temp - 60);
	}
	
	public void draw(Graphics2D b) {
		b.setColor(color);
		b.fill(this);
	}
	
	public Color getCol() {
		return color;
	}
	
	public void setCol(Color c) {
		color = c;
	}
		
}


	



