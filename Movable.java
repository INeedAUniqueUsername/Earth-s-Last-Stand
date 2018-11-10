package Unit17.Assignments;

//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Graphics;

public abstract class Movable implements Locatable {
	private int xPos, yPos;
	public Movable() { this(0, 0); }
	public Movable(int x, int y) { setPos(x, y); }
	
	public void setPos(int x, int y) { setX(x); setY(y); }
	public void setX(int x) { xPos = x; }
	public void setY(int y) { yPos = y; }
	
	public void incX(int x) { xPos += x; }
	public void incY(int y) { yPos += y; }
	
	public int getX() { return xPos; }
	public int getY() { return yPos; }
	
	public abstract void setSpeed(int s);
	public abstract int getSpeed();

	public abstract void draw(Graphics window);
	public void move(String direction) {
		// add more code to complete the move method
		int speed = getSpeed();
		switch(direction.toUpperCase()) {
		case "LEFT":
			incX(-speed);
			break;
		case "RIGHT":
			incX(speed);
			break;
		case "UP":
			incY(-speed);
			break;
		case "DOWN":
			incY(speed);
			break;
		}
	}
	public String toString() {
		return String.format("X: %d\nY: %d", xPos, yPos);
	}
}