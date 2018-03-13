package ca.team2706.game.platformer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

import com.thatmadhacker.utils.joystick.Joystick;
import com.thatmadhacker.utils.joystick.Joystick.JoystickListener;

import ca.team2706.game.platformer.handlers.CollisionHandler;
import ca.team2706.game.platformer.objects.Player;
import ca.team2706.game.platformer.objects.ResetDiff;
import ca.team2706.game.platformer.objects.Wall;

public class Platformer extends JFrame implements Runnable, JoystickListener {

	private static final long serialVersionUID = 1L;
	public Wall obstacle;
	public Wall obstacle1;
	public Random random;
	public MasterHandler handler;
	public CollisionHandler collisionHandler;
	public Player player;
	public int difficulty = 1000;
	public int coins = 0;
	private static double lastFrameTime;
	public int score = 0;
	
	public double fps = 0;
	
	public static final int NANOSECONDS_PER_SECOND = 1000000000;

	public static long fpsTimer = System.nanoTime();
	
	public static Joystick joystick;

	public static void main(String[] args) {
		new Platformer();
	}

	public Platformer() {
		setupGUI();
		loadConfig();
		init();
		new Thread(this).start();
	}

	private void setupGUI() {
		setResizable(true);
		setSize(Toolkit.getDefaultToolkit().getScreenSize());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		requestFocus();
	}

	private void loadConfig() {

	}

	private void init() {
		random = new Random();
		handler = new MasterHandler(this);
		collisionHandler = new CollisionHandler(handler);
		handler.wall = new Wall(0, 0, 0, collisionHandler, true);
		player = new Player(getWidth()/2, (int) (getHeight()/1.5), collisionHandler, this);
		handler.addObject(player);
		obstacle = new Wall(random.nextInt(getWidth()-20) + 20, 25, random.nextInt(5)+1, collisionHandler, false);
		obstacle.setVelY(6);
		handler.addObject(obstacle);
		obstacle1 = new Wall(random.nextInt(getWidth()-20) + 20, 25, random.nextInt(5) + 1, collisionHandler, false);
		obstacle1.setVelY(6);
		handler.addObject(obstacle1);
		handler.shopObjects.add(new ResetDiff(collisionHandler));
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(difficulty);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (handler.gameOver == true) {
						while (handler.gameOver) {
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
					obstacle = new Wall(random.nextInt(getWidth()-20) + 20, 25, random.nextInt(5) + 1, collisionHandler,
							false);
					obstacle.setVelY(6);
					handler.addObject(obstacle);
					obstacle1 = new Wall(random.nextInt(getWidth()-20) + 20, 25, random.nextInt(5) + 1, collisionHandler,
							false);
					obstacle1.setVelY(6);
					handler.addObject(obstacle1);
					if (difficulty > 200) {
						difficulty -= 3;
					}
				}

			}

		}).start();
		final Platformer platformer = this;
		new Thread(new Runnable() {
			public void run() {
				joystick = new Joystick(platformer, false);
			}
		}).start();
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		requestFocus();
	}

	@Override
	public void run() {
		while (true) {
			try {
				tick();
				render();
			} catch (Exception e) {
				e.printStackTrace();
			}
			long now = System.nanoTime();
			fps = ((double) NANOSECONDS_PER_SECOND) / (now - fpsTimer);
			fps = ((int)(fps*10))/10.0; // round to 1 decimal place
			fpsTimer = now;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void tick() {
		try {
			synchronized (handler.objects) {
				for (GameObject obj : handler.objects) {
					if (obj instanceof Wall && obj.getY() > 638) {
						handler.removeObject(obj);
						score++;
					}
				}
			}
		} catch (Exception e) {

		}

		handler.tick();

	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(new Color(148, 0, 211));
		//g.setColor(Color.BLACK);
		
		g.fillRect(0, 0, getWidth(), getHeight());

		handler.render(g);

		g.setColor(Color.BLACK);
		
		g.drawString("Score: " + score, 50, 50);
		
		g.drawString("Coins: "+coins, 50, 100);
		
		g.drawString("Fps: "+fps, 50, 150);
		
		g.dispose();
		bs.show();
	}

	public static double lastFrameTime() {
		return lastFrameTime;
	}

	@Override
	public void onButtonPressed(int button) {
		player.onPressed(button);
	}

	@Override
	public void onButtonReleased(int button) {

	}

	@Override
	public void onSpeedChange(int value) {

	}

	@Override
	public void onRotate(int value) {

	}

	@Override
	public void onMove(int x, int y) {
		player.onMove(x, y);
	}

	public void genPlayer() {
		player = new Player(getWidth()/2, getHeight()-33, collisionHandler, this);
		handler.addObject(player);
	}
}
