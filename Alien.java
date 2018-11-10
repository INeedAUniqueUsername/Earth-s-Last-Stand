package Unit17.Assignments;

//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Alien extends GameObject {
	boolean firing = false;
	public static final int WIDTH = 35;
	public static final int HEIGHT = 40;
	private int tick = 0;
	public int getWidth() {
		return WIDTH;
	}
	public int getHeight() {
		return HEIGHT;
	}
	public Alien() {
		this(0, 0);
	}

	public Alien(int x, int y) {
		this(x, y, 0);
	}

	public Alien(int x, int y, int s) {
		super(x, y);
		setSpeed(s);
		setDirection("RIGHT");
		try {
			//setImage(ImageIO.read(new File("src\\Unit17\\Assignments\\alien.png")));
			setImage(ImageIO.read(getClass().getResource("/Unit17/Assignments/alien.png")));
		} catch (Exception e) {
			// feel free to do something here
		}
	}
	public Ammo createAmmo() {
		return new Ammo(getX() + getWidth()/2, getY()+getHeight(), (int) (Math.random() * 8) + 4);
	}
	public String toString() {
		return String.format("%s\nSpeed: %d",super.toString(), getSpeed());
	}
	public void setFiring(boolean b) {
		firing = b;
	}
	public boolean getFiring() {
		return firing;
	}
	public int getCooldownTicks() {
		return 25;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		super.update();
		tick++;
		
		int x = getX();
		int y = getY();
		int x2 = x + getWidth();
		int y2 = y + getHeight();
		
		Ship player = OuterSpace.getPlayer();
		boolean checkMove = tick%60 == 0;
		boolean checkFire = tick%20 == 0;
		if(player != null && (checkMove || checkFire)) {
			System.out.println("Move 1 : " + checkMove);
			//Make three clones of the player to abuse wraparound
			int x_target_center = player.getX();
			int x_target_left = x_target_center - StarFighter.WIDTH;
			int x_target_right = x_target_center + StarFighter.WIDTH;
			
			int diff_x_center = Math.abs(x - x_target_center);
			int diff_x_left = Math.abs(x - x_target_left);
			int diff_x_right = Math.abs(x - x_target_right);
			
			//Find the closest clone
			int x_target = x_target_center;
			if(diff_x_left < diff_x_center) {
				x_target = x_target_left;
			}
			if(diff_x_right < diff_x_center) {
				x_target = x_target_right;
			}
			int y_target = player.getY();
			
			int x2_target = x_target + player.getWidth();
			int y2_target = y_target + player.getHeight();
			
			int diff_x = Math.abs(x - x_target);
			int diff_y = Math.abs(y - y_target);
			if(checkMove) {	
				System.out.println("Move 2 : " + checkMove);
				if(diff_x < 5 * Alien.WIDTH && y2 < y_target && diff_y < 3 * StarFighter.HEIGHT) {
					System.out.println("Move 3 : Horizontal");
					setDirection(x < x_target ? "LEFT" : "RIGHT");
				} else {
					System.out.println("Move 3 : Vertical");
					setDirection("DOWN");
				}
				/*
				if(Math.random() < 0.1 || y > y_target) {	
				}
				*/
			}
			if(checkFire) {
				setFiring(diff_x < 3 * Alien.WIDTH && y2 < y_target);
			}
			/*
			switch(direction) {
			case "LEFT":
				if(getX() < 0) {
					setDirection("DOWN");
				}
				break;
			case "RIGHT":
				if(getX() + getWidth() > StarFighter.WIDTH) {
					setDirection("DOWN");
				}
				break;
			case "DOWN":
				setDirection(getX() < 0 ? "RIGHT" : "LEFT");
				break;
			}
			*/
		} else if(player == null) {
			setDirection("DOWN");
		}
	}
}
