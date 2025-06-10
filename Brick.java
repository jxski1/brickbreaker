package game;

import java.awt.*;

public class Brick implements GameObject {
	int x;
	int y;
	int width;
	int height;
	boolean hit = false;

	public Brick(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics brush) {
		if (!hit) {
			brush.setColor(Color.PINK);
			brush.fillRect(x, y, width, height);
			brush.setColor(Color.BLACK);
			brush.drawRect(x, y, width, height);
		}
	}

	public void destroy() {
		hit = true;
	}

	public boolean isHit() {
		return hit;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
