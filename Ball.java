package game;

import java.awt.*;

public class Ball implements GameObject {
	private int x;
	int y;
	private int diameter;
	private int dx = 6;
	private int dy = -6;

	public Ball(int x, int y, int diameter) {
		this.x = x;
		this.y = y;
		this.diameter = diameter;
	}

	@Override
	public void draw(Graphics brush) {
		brush.setColor(Color.WHITE);
		brush.fillOval(x, y, diameter, diameter);
	}

	@Override
	public void update() {
		x += dx;
		y += dy;
		if (x <= 0 || x + diameter >= 800) {
			dx = -dx;
		}
		if (y <= 0) {
			dy = -dy;
		}
	}

	public void bouncePaddle() {
		dy = -dy;
	}

	public boolean hitsPaddle(Paddle paddle) {
		return x + diameter > paddle.x && x < paddle.x + paddle.width && y + diameter > paddle.y
				&& y < paddle.y + paddle.height;
	}

	public boolean hitsBrick(Brick brick) {
		return x + diameter > brick.getX() && x < brick.getX() + brick.getWidth() && y + diameter > brick.getY()
				&& y < brick.getY() + brick.getHeight();
	}
}