package Unit17.Assignments;

//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Ship extends GameObject {
	private static final int WIDTH = 35;
	private static final int HEIGHT = 50;
	public int getWidth() {
		return WIDTH;
	}
	public int getHeight() {
		return HEIGHT;
	}
	
	public Ship() {
		this(0, 0);
	}

	public Ship(int x, int y) {
		this(x, y, 0);
	}

	public Ship(int x, int y, int s) {
		super(x, y);
		setSpeed(s);
		try {
			//setImage(ImageIO.read(new File("src\\Unit17\\Assignments\\ship.png")));
			setImage(ImageIO.read(getClass().getResource("/Unit17/Assignments/ship.png")));
		} catch (Exception e) {
			// feel free to do something here
			System.out.println("Hey, look what I found!");
			System.out.println(e);
		}
	}
	public Ammo createAmmo() {
		return new Ammo(getX() + getWidth()/2, getY()+30, 10);
	}
	public int getCooldownTicks() {
		return 18;
	}
	public String toString() {
		return String.format("%s\nSpeed: %d", super.toString(), getSpeed());
	}
	public void checkBounds() {
		super.checkBounds();
		int y = getY();
		int height = getHeight();
		int y2 = y + height;
		int y_bound = StarFighter.HEIGHT;
		
		if(y < 0) {
			setY(0);
		} else if(y2 > y_bound) {
			setY(y_bound - height);
		}
	}
}
