package Unit17.Assignments;

//© A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class OuterSpace extends Canvas implements KeyListener, Runnable
{
	private static final long serialVersionUID = 1L;
	private final int DAMAGE_SHIP_COLLISION = 3;
	private final int DAMAGE_SHIP_FIRE = 1;
	private final int DAMAGE_EARTH = 1;
	
	private final int REGEN_TICKS_SHIP = 300;
	private final int REGEN_TICKS_EARTH = 900;
	
	private final int MAX_HP_SHIP = 10;
	private final int MAX_HP_EARTH = 30;
	private final int BAR_WIDTH = 500;
	
	private static Ship ship;
	AlienHorde horde;
	List<Ammo> fire_player;
	List<Ammo> fire_alien;

	private boolean[] keys;
	private BufferedImage back;
	
	enum GameState {
		PLAY, WAIT_FOR_NEXT_WAVE, GAME_OVER
	}
	GameState state;
	
	
	private FlashText text;
	
	int tick = 0;
	private int waveNumber = 0;
	private int hp_ship = MAX_HP_SHIP;
	private int hp_earth = MAX_HP_EARTH;
	private StatBar bar_ship, bar_earth, bar_score;
	private int score = 1;
	private int score_max = 1;
	
	public OuterSpace()
	{
		setBackground(Color.black);

		keys = new boolean[5];

		//instantiate other stuff
		ship = new Ship(StarFighter.WIDTH/2, StarFighter.HEIGHT - 300, 4);
		fire_player = new ArrayList<Ammo>();
		fire_alien = new ArrayList<Ammo>();
		this.addKeyListener(this);
		new Thread(this).start();

		setVisible(true);
		
		horde = new AlienHorde(0);
		state = GameState.WAIT_FOR_NEXT_WAVE;
		text = new FlashText("Earth's Last Stand", 72);
		bar_ship = new StatBar("HP (Ship)", hp_ship, BAR_WIDTH, 20);
		bar_earth = new StatBar("HP (Earth)", hp_earth, BAR_WIDTH, 40);
		bar_score = new StatBar("Score", score, BAR_WIDTH, 60);
	}
	public static Ship getPlayer() {
		return ship;
	}
	public void update(Graphics window)
	{
		tick++;
		paint(window);
	}

	public void paint( Graphics window )
	{
		//set up the double buffering to make the game animation nice and smooth
		Graphics2D twoDGraph = (Graphics2D)window;

		//take a snap shop of the current screen and same it as an image
		//that is the exact same width and height as the current screen
		if(back==null)
			back = (BufferedImage)(createImage(getWidth(),getHeight()));

		//create a graphics reference to the back ground image
		//we will draw all changes on the background image
		Graphics gBack = back.createGraphics();

		gBack.setColor(Color.BLUE);
		gBack.drawString("StarFighter ", 25, 50 );
		gBack.setColor(Color.BLACK);
		gBack.fillRect(0,0,StarFighter.WIDTH,StarFighter.HEIGHT);

		ship.draw(gBack);
		for(Alien a : horde.getAliens()) {
			a.draw(gBack);
		}
		for(Ammo a : fire_player) {
			a.draw(gBack);
		}
		for(Ammo a : fire_alien) {
			a.draw(gBack);
		}
		bar_ship.setPoints(hp_ship);
		bar_ship.draw(gBack);
		bar_earth.setPoints(hp_earth);
		bar_earth.draw(gBack);
		bar_score.setPoints(score);
		bar_score.draw(gBack);
		
		switch(state) {
		case WAIT_FOR_NEXT_WAVE:
			text.draw(gBack);
			if(tick == text.getInterval()*3 - text.getInterval()/2) {
				System.out.println("Begin wave");
				state = GameState.PLAY;
				int alienCount = waveNumber*3;
				horde = new AlienHorde(alienCount);
				for(int i = 0; i < alienCount; i++) {
					score_max += StarFighter.HEIGHT - 50*i;
				}
				bar_score.setPoints_max(score_max);
				tick = 0;
			}
			twoDGraph.drawImage(back, null, 0, 0);
			return;
		case GAME_OVER:
			text.draw(gBack);
			twoDGraph.drawImage(back, null, 0, 0);
			return;
		default:
			break;
		}
		twoDGraph.drawImage(back, null, 0, 0);
		if(ship.getActive()) {
			ship.update();
			ship.checkBounds();
			if(keys[0])
			{
				ship.setDirection("LEFT");
			}
			else if(keys[1]) {
				ship.setDirection("RIGHT");
			}
			else if(keys[2]) {
				ship.setDirection("UP");
			}
			else if(keys[3]) {
				ship.setDirection("DOWN");
			}
			else {
				ship.setDirection("");
			}
			if(keys[4]) {
				if(ship.getFireTicks() > ship.getCooldownTicks()) {
					System.out.println("Fire");
					Ammo a = ship.createAmmo();
					ship.setFireTicks(0);
					a.setDirection("UP");
					fire_player.add(a);
				}
			}
		}
		
		horde.getAliens().removeIf((Alien alien) -> {
			alien.update();
			if(alien.getFiring() && alien.getFireTicks() > alien.getCooldownTicks() && Math.random() < 0.4) {
				Ammo a = alien.createAmmo();
				alien.setFireTicks(0);
				a.setDirection("DOWN");
				fire_alien.add(a);
			}
			if(GameObject.collision(alien, ship)) {
				hp_ship -= DAMAGE_SHIP_COLLISION;
				alien.setActive(false);
			} else if(alien.getY() > StarFighter.HEIGHT) {
				hp_earth -= DAMAGE_EARTH;
				alien.setActive(false);
			}
			for(Ammo a : fire_player) {
				if(GameObject.collision(a, alien)) {
					score += StarFighter.HEIGHT - a.getY();
					a.setActive(false);
					alien.setActive(false);
				}
			}
			return !alien.getActive();
		});
		fire_player.removeIf((Ammo a) -> {
			a.update();
			if(a.getY() < 0) {
				a.setActive(false);
			}
			return !a.getActive();
		});
		
		fire_alien.removeIf((Ammo a) -> {
			a.update();
			if(a.getY() > StarFighter.HEIGHT) {
				a.setActive(false);
			}
			if(GameObject.collision(a, ship)) {
				hp_ship -= DAMAGE_SHIP_FIRE;
				a.setActive(false);
			}
			return !a.getActive();
		});
		
		if(hp_ship < MAX_HP_SHIP) {
			if(hp_ship < 1) {
				/*
				for(Alien a : horde.getAliens()) {
					a.setFiring(true);
				}
				ship.setSpeed(0);
				ship.setY(-100);
				text = new FlashText("GAME OVER", 144);
				state = GameState.GAME_OVER;
				*/
				ship.setActive(false);
				ship.setY(-100);
			} else if(tick%REGEN_TICKS_SHIP == 0) {
				hp_ship++;
			}
		}
		if(hp_earth < MAX_HP_EARTH) {
			if(hp_earth < 1) {
				text = new FlashText("GAME OVER", 144);
				state = GameState.GAME_OVER;
				return;
			} else if(tick%REGEN_TICKS_EARTH == 0) {
				hp_earth++;
			}
		}
		
		System.out.println(horde.getAliens().size() + " Aliens");
		if(horde.getAliens().size() == 0) {
			tick = 0;
			state = GameState.WAIT_FOR_NEXT_WAVE;
			waveNumber++;
			text = new FlashText("Wave " + waveNumber, 24);
			System.out.println("Wave done");
		}
	}


	public void keyPressed(KeyEvent e)
	{
		setKeyState(e, true);
		repaint();
	}

	public void keyReleased(KeyEvent e)
	{
		setKeyState(e, false);
		repaint();
	}
	public void setKeyState(KeyEvent e, boolean state) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:	keys[0] = state;	break;
		case KeyEvent.VK_RIGHT:	keys[1] = state;	break;
		case KeyEvent.VK_UP:	keys[2] = state;	break;
		case KeyEvent.VK_DOWN:	keys[3] = state;	break;
		case KeyEvent.VK_SPACE:	keys[4] = state;	break;
		}
	}

	public void keyTyped(KeyEvent e)
	{

	}

	public void run()
	{
		try
		{
			while(true)
			{
				Thread.currentThread().sleep(10);
				repaint();
			}
		}catch(Exception e)
		{
		}
  	}
}

