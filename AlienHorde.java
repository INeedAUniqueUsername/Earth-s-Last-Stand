package Unit17.Assignments;


//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date - 
//Class -
//Lab  -

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlienHorde
{
	private ArrayList<Alien> aliens;

	public AlienHorde(int size)
	{
		Alien[] horde = new Alien[size];
		for(int i = 0; i < size; i++) {
			horde[i] = new Alien((int) (Math.random() * StarFighter.WIDTH), -100, 1);
		}
		setAliens(new ArrayList<Alien>(Arrays.asList(horde)));
		/*
		aliens = new ArrayList<Alien>(0);
		for(int i = size; i > 0;) {
			int level = (int) (Math.random() * Math.min(i, 5)) + 1;
			i -= level;
			int x = (int) (Math.random() * StarFighter.WIDTH);
			int y = -100;
			Alien a = new Alien(x, y, 1);
			switch(level) {
			case 1:
				a = new Alien2(x, y, 2);
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			default:
				
				break;
			}
			aliens.add(a);
		}
		*/
	}
	public static int alternate(int i) {
		return i%2 == 0 ? i : -i;
	}
	public void setAliens(ArrayList<Alien> aa) {
		aliens = aa;
	}
	public List<Alien> getAliens() {
		return aliens;
	}
	public void add(Alien al)
	{
	   aliens.add(al);
	}

	public String toString()
	{
		return "" + aliens;
	}
}
