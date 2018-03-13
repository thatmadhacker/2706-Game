package ca.team2706.game.platformer.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ca.team2706.game.platformer.GameObject;
import ca.team2706.game.platformer.handlers.CollisionHandler;

public class ResetDiff extends GameObject implements MouseListener {

	public ResetDiff(CollisionHandler ch) {
		super(ch.handler.platformer.getWidth()/2, ch.handler.platformer.getHeight()/2, ch);
		this.width = 100;
		this.height = 50;
		ch.handler.platformer.addMouseListener(this);
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		
		g.drawRect(x, y, width, height);
		
		if(ch.handler.platformer.coins > 4){
			g.setColor(Color.GREEN);
		}else{
			g.setColor(Color.RED);
		}
		
		g.drawString("Reset Difficulty: 5 Coins", x+5, y+5);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Rectangle r = new Rectangle(e.getX(),e.getY(),1,1);
		if(getBounds().intersects(r)){
			if(ch.handler.platformer.coins > 4){
				ch.handler.platformer.coins-=5;
				ch.handler.platformer.difficulty = 1000;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
