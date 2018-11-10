package Unit17.Assignments;

//� A+ Computer Science  -  www.apluscompsci.com
//Name -
//Date -
//Class -
//Lab  -

import javax.swing.JFrame;

import java.awt.Component;
import java.awt.Toolkit;
import java.util.Scanner;

public class StarFighter extends JFrame
{
	static final int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

	public StarFighter()
	{
		super("Earth's Last Stand");
		setSize(WIDTH,HEIGHT);

		OuterSpace theGame = new OuterSpace();
		((Component)theGame).setFocusable(true);

		getContentPane().add(theGame);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	public static void main( String args[] )
	{
		StarFighter run = new StarFighter();
	}
}