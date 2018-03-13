package ca.team2706.game.platformer.objects;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.team2706.game.platformer.GameObject;
import ca.team2706.game.platformer.Platformer;
import ca.team2706.game.platformer.handlers.CollisionHandler;

public class Player extends GameObject implements KeyListener {
	public static final int JUMP_POWER = -24;
	public static final int GRAVITY = 2;
	private BufferedImage image;
	public boolean onGround = false;
	public boolean aPressed = false;
	public boolean dPressed = false;
	public boolean lose = false;
	public int numJumps = 0;
	public int lastXMove;
	public boolean jumpingOnWall = false;
	public boolean onWall = false;

	public Player(int x, int y, CollisionHandler ch, Platformer platformer) {
		super(x, y, ch);
		try {
			image = ImageIO.read(new File("assets/entity/ingame/player.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.width = 32;
		this.height = 32;
		platformer.addKeyListener(this);
	}

	@Override
	public void tick() {
		if (y < 25) {
			y = 25;
			velY = 0;
		} else if (y > ch.handler.platformer.getHeight()-34) {
			y = ch.handler.platformer.getHeight()-34;
			velY = 0;
			onGround = true;
		}
		if(onGround && y < ch.handler.platformer.getHeight()-34){
			velY = GRAVITY;
			onGround = false;
		}
		if (aPressed) {
			velX = -10;
		}
		if (dPressed) {
			velX = 10;
		}
		if (!aPressed && !dPressed) {
			velX = 0;
		}
		if (onWall && (velY + (GRAVITY / 4)) > 0) {
			velY += GRAVITY / 4;
		} else {
			velY += GRAVITY;
		}
		ch.tryYMove(this, velY);
		if(onWall){
			velY = 0;
			numJumps = 0;
		}
		if(onGround){
			velY = 0;
			numJumps = 0;
			onGround = true;
		}
		if (y < 25) {
			y = 25;
			velY = 0;
		} else if (y > 607) {
			y = 607;
			velY = 0;
			onGround = true;
			numJumps = 0;
		}
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ch.tryYMove(this, 0);
		if(velX != 0){
			ch.tryXMove(this, velX);
			if(velX < 0){
				lastXMove = -1;
			}else if(velX > 0){
				lastXMove = 1;
			}
		}
		if(onWall){
			numJumps = 0;
		}
		for(GameObject obj : ch.handler.objects){
			if(obj != this){
				if(getBounds().intersects(obj.getBounds())){
					lose = true;
				}
			}
		}
		for(GameObject obj : ch.handler.objects){
			if(obj instanceof Coin){
				Coin coin = (Coin) obj;
				if(getBounds().intersects(coin.getBounds())){
					ch.handler.removeObject(coin);
					ch.handler.platformer.coins++;
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			aPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			dPressed = true;
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			jump();
		}
		if(e.getKeyCode() == KeyEvent.VK_R && ch.handler.gameOver){
			ch.handler.restart();
		}
		if(e.getKeyCode() == KeyEvent.VK_E){
			ch.handler.shop();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			aPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			dPressed = false;
		}
	}

	private void jump() {
		final Player p = this;
		new Thread(new Runnable() {
			public void run() {
				if (numJumps < 2) {
					numJumps++;
					onGround = false;
					if(onWall && y < 607){
						velY+=JUMP_POWER/2;
					}else if(numJumps == 2){
						velY += JUMP_POWER/1.5;
					}else{
						velY += JUMP_POWER;
					}
					ch.tryYMove(p, velY);
					if (y < 25) {
						y = 25;
						velY = 0;
					} else if (y > ch.handler.platformer.getHeight()-33) {
						y = ch.handler.platformer.getHeight()-33;
						velY = 0;
						onGround = true;
						numJumps = 0;
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(onWall && y < 607){
						new Thread(new Runnable(){

							@Override
							public void run() {
								onWall = false;
								for(int i = 0; i < 50;i++){
									x+=(lastXMove) * -1;
									try {
										Thread.sleep(5);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
							
						}).start();
					}
				}
			}
		}).start();
	}
	public void hit(){
		ch.handler.removeObject(this);
		
		ch.handler.addObject(new GameOver(ch));
		ch.handler.gameOver();
	}
	public void onMove(int x, int y){
		if(x > 60){
			dPressed = true;
			aPressed = false;
		}else if(x < 42){
			aPressed = true;
			dPressed = false;
		}else{
			aPressed = false;
			dPressed = false;
		}
	}
	public void onPressed(int button){
		if(button == 1){
			jump();
		}
		if(button == 4){
			ch.handler.shop();
		}
		if(button == 2 && ch.handler.gameOver){
			ch.handler.restart();
		}
	}
}
