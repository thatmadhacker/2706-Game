package ca.team2706.game.platformer.objects;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.team2706.game.platformer.GameObject;
import ca.team2706.game.platformer.handlers.CollisionHandler;

public class GameOver extends GameObject {
	Image image;
	public GameOver(CollisionHandler ch) {
		super(0, 0, ch);
		try {
			image  = ImageIO.read(new File("assets/stage/gameover/gameover.png")).getScaledInstance(ch.handler.platformer.getWidth(),ch.handler.platformer.getHeight(), Image.SCALE_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(image,0,0,null);
	}

}
