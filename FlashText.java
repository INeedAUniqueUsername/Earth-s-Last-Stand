package Unit17.Assignments;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class FlashText {
	private int tick = 0;
	private final int interval = 180;
	private String message;
	private Font font;
	public FlashText(String message, int size) {
		setMessage(message);
		setFont(new Font("Consolas", Font.BOLD, size));
	}
	public void draw(Graphics g) {
		
		if(tick < interval) {
			g.setColor(Color.RED);
		} else if(tick < interval * 2) {
			g.setColor(new Color(255, 128, 0));
		} else if(tick < interval * 3) {
			g.setColor(Color.YELLOW);
		} else {
			g.setColor(Color.RED);
			tick = 0;
		}
		g.setFont(font);
		tick++;
		if(tick%interval < interval/2) {
			g.drawString(message, StarFighter.WIDTH/2 - g.getFontMetrics().stringWidth(message)/2, 600);
		}
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public int getInterval() {
		return interval;
	}
}
