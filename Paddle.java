package game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle {
	int x, y, width, height;
	int speed = 8;

	public Paddle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, width, height);
	}

	public void update() {
		if (x < 0)
			x = 0;
		if (x + width > 800)
			x = 800 - width;
	}

	public class Movement implements KeyListener {
		private boolean left = false;
		private boolean right = false;

		public Movement() {
			left = false;
			right = false;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right = false;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		public void handleMovement(Paddle paddle) {
			if (left) {
				paddle.x -= speed;
			}
			if (right) {
				paddle.x += speed;
			}
		}
	}
}
