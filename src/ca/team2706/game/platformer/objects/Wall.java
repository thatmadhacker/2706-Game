package ca.team2706.game.platformer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.team2706.game.platformer.GameObject;
import ca.team2706.game.platformer.handlers.CollisionHandler;

public class Wall extends GameObject {
	private boolean main;
	private Image image;
	private int powerCubeWidth;
	public static final int POWER_CUBE_SIZE = 64;
	public Wall(int x, int y,int powerCubeWidth, CollisionHandler ch, boolean main) {
		super(x, y, ch);
		this.main = main;
		this.powerCubeWidth = powerCubeWidth;
		this.height = POWER_CUBE_SIZE;
		this.width = POWER_CUBE_SIZE*powerCubeWidth;
		if(!main){
			try {
				image = ImageIO.read(new File("assets/entity/ingame/wall.png"));//.getScaledInstance(POWER_CUBE_SIZE,POWER_CUBE_SIZE, Image.SCALE_SMOOTH);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void tick() {
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		if (main) {
			g.setColor(Color.ORANGE);
			g.fillRect(0, 0, 20, ch.handler.platformer.getHeight());
			
			g.fillRect(ch.handler.platformer.getWidth()-20, 0, 20, ch.handler.platformer.getHeight());
		}else{
			//g.drawImage(image, x, y, null);
			for(int i = 0; i < powerCubeWidth;i++){
				g.drawImage(image, x+(i*POWER_CUBE_SIZE), y, null);
			}
		}
	}

}
