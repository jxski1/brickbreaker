package game;

import java.awt.*;
import java.util.ArrayList;

public class YourGameName extends Game {

	private Paddle paddle;
	private Paddle.Movement movement;
	private Ball ball;
	private ArrayList<Brick> bricks = new ArrayList<>();
	private boolean running = true;
	private ScoreManager score = new ScoreManager();
	private boolean rotateRight = true;

	public YourGameName() {
		super("Brick Breaker", 800, 600);

		paddle = new Paddle(350, 550, 100, 10);
		movement = paddle.new Movement();

		addKeyListener(movement);

		addKeyListener(new java.awt.event.KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if (e.getKeyCode() == java.awt.event.KeyEvent.VK_K) {
					restart();
				}
			}
		});

		ball = new Ball(400, 400, 15);

		int rows = 5;
		int cols = 10;
		int brickWidth = 75;
		int brickHeight = 20;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				bricks.add(new Brick(col * (brickWidth + 5), row * (brickHeight + 5), brickWidth, brickHeight));
			}
		}
	}

	@Override
	public void paint(Graphics brush) {
		Graphics2D g2d = (Graphics2D) brush;

		if (!running) {
			if (bricks.isEmpty()) {
				g2d.setColor(Color.GREEN);
				g2d.drawString("You Win! Score: " + score.getScore(), width / 2 - 60, height / 2 - 20);
			} else {
				g2d.setColor(Color.RED);
				g2d.translate(width / 2, height / 2);
				if (rotateRight) {
					g2d.rotate(Math.toRadians(15));
				} else {
					g2d.rotate(Math.toRadians(-15));
				}
				g2d.drawString("Game Over! Score: " + score.getScore(), -50, 0);
				g2d.rotate(rotateRight ? Math.toRadians(-15) : Math.toRadians(15));
				g2d.translate(-width / 2, -height / 2);

				g2d.drawString("Press 'K' to Keep Going", width / 2 - 60, height / 2 + 50);
			}
			return;
		}

		brush.setColor(Color.BLACK);
		brush.fillRect(0, 0, width, height);

		movement.handleMovement(paddle);
		paddle.draw(brush);
		paddle.update();
		ball.draw(brush);
		ball.update();

		for (Brick brick : bricks) {
			brick.draw(brush);
		}

		bricks.removeIf(brick -> {
			if (ball.hitsBrick(brick) && !brick.isHit()) {
				brick.destroy();
				ball.bouncePaddle();
				score.addScore(100);
				return true;
			}
			return false;
		});

		if (ball.hitsPaddle(paddle)) {
			ball.bouncePaddle();
		}

		brush.setColor(Color.WHITE);
		brush.drawString("Score: " + score.getScore(), 10, 200);

		if (bricks.isEmpty()) {
			g2d.setColor(Color.GREEN);
			g2d.drawString("You Win! Score: " + score.getScore(), width / 2 - 60, height / 2 - 20);
			running = false;
		}

		if (ball.y > height) {
			stop();
			rotateRight = !rotateRight;
		}
	}

	private void restart() {
		score.resetScore();
		running = true;
		bricks.forEach(brick -> brick.hit = false);
		ball = new Ball(400, 400, 15);
		repaint();
	}

	public void stop() {
		running = false;
		repaint();
	}

	public static void main(String[] args) {
		YourGameName game = new YourGameName();
		game.repaint();
	}

	private class ScoreManager {
		private int score = 0;

		public void addScore(int points) {
			score += points;
		}

		public int getScore() {
			return score;
		}

		public void resetScore() {
			score = 0;
		}
	}
}