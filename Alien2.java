package Unit17.Assignments;

import java.io.File;

import javax.imageio.ImageIO;

//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

public class Alien2 extends Alien {
	public Alien2(int x, int y, int s) {
		super(x, y);
		setSpeed(s);
		setDirection("RIGHT");
		try {
			setImage(ImageIO.read(new File("src\\Unit17\\Assignments\\alien.png")));
		} catch (Exception e) {
			// feel free to do something here
		}
	}
	public Ammo createAmmo() {
		return new Ammo(getX() + getWidth()/2, getY()+getHeight(), (int) (Math.random() * 4) + 2);
	}
	public int getFireCooldown() {
		return (int) (Math.random() * 20) + 10;
	}
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
			incY(-speed/2);
			break;
		case "DOWN":
			incY(speed/2);
			break;
		}
	}
}
