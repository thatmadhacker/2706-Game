package ca.team2706.game.platformer;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ca.team2706.game.platformer.handlers.CollisionHandler;

public abstract class GameObject {
	
	protected int velX,velY,x;
	protected int y;
	protected int width;
	protected int height;
	protected BufferedImage texture;
	protected CollisionHandler ch;
	
	public GameObject(int x,int y,CollisionHandler ch, String textureLoc){
		this.x = x;
		this.y = y;
		this.ch = ch;
		try {
			texture = ImageIO.read(new File(textureLoc));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public GameObject(int x, int y, CollisionHandler ch){
		this.x = x;
		this.y = y;
		this.ch = ch;
	}
	public abstract void tick();
	public abstract void render(Graphics g);
	public Rectangle getBounds(){
		return new Rectangle(x,y,width,height);
	}
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public int getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public BufferedImage getTexture() {
		return texture;
	}
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
}
