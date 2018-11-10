package Unit17.Assignments;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class ScreenText {
	private String message;
	private Font font;
	public ScreenText(String message, int size) {
		setMessage(message);
		setFont(new Font("Consolas", Font.BOLD, size));
	}
	public void draw(Graphics g) {
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setFont(Font font) {
		this.font = font;
	}
	public String getMessage() {
		return message;
	}
	public Font getFont() {
		return font;
	}
}
