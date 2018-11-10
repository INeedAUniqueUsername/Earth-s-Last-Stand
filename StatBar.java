package Unit17.Assignments;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class StatBar {
	String name;
	int points;
	int points_max;
	int width;
	int y;
	Font font;
	public StatBar(String name, int points_max, int width, int y) {
		setName(name);
		setPoints(points_max);
		setPoints_max(points_max);
		setWidth(width);
		setY(y);
		font = new Font("Consolas", Font.PLAIN, 18);
	}
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(font);
		FontMetrics fm = g.getFontMetrics();
		g.drawString(name, 20, (int) (y + fm.getHeight()*0.75));
		g.fillRect(150, y, (int) ((double) width * points / points_max), 18);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public int getPoints_max() {
		return points_max;
	}
	public void setPoints_max(int points_max) {
		this.points_max = points_max;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
