package ca.team2706.game.platformer.objects;

import java.awt.Color;
import java.awt.Graphics;

import ca.team2706.game.platformer.GameObject;
import ca.team2706.game.platformer.handlers.CollisionHandler;

public class Coin extends GameObject{

	public Coin(int x, int y, CollisionHandler ch) {
		super(x, y, ch);
		this.width = 16;
		this.height = 16;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillOval(x, y, width, height);
	}

}
