package ca.team2706.game.platformer.handlers;

import java.awt.Rectangle;

import ca.team2706.game.platformer.GameObject;
import ca.team2706.game.platformer.MasterHandler;
import ca.team2706.game.platformer.objects.Player;
import ca.team2706.game.platformer.objects.Wall;

public class CollisionHandler {

	public MasterHandler handler;

	public CollisionHandler(MasterHandler handler) {
		this.handler = handler;
	}

	public void tryXMove(GameObject obj, int velX) {
		try {
			boolean hit = false;
			Rectangle next = new Rectangle(obj.getX(), obj.getY(), obj.getWidth() + velX, obj.getHeight());
			if (obj.getX() < 20) {
				obj.setX(handler.platformer.getWidth()-52);
				obj.setVelX(0);
			} else if (obj.getX() > handler.platformer.getWidth()-52) {
				obj.setX(20);
				obj.setVelX(0);
			}
			if (obj.getX() <= 20) {
				if (obj instanceof Player) {
					((Player) obj).onWall = true;
					((Player) obj).numJumps = 0;
					hit = true;
				}
			} else if (obj.getX() >= handler.platformer.getWidth()-52) {
				if (obj instanceof Player) {
					((Player) obj).onWall = true;
					((Player) obj).numJumps = 0;
					hit = true;
				}
			}
			if (obj.getX() + (int) velX < 20) {
				obj.setX(20);
			} else if (obj.getX() + velX + obj.getWidth() > handler.platformer.getWidth()-20) {
				obj.setX(handler.platformer.getWidth()-20 - obj.getWidth());
			}
			for (GameObject tempObject : handler.objects) {
				if (!tempObject.equals(obj)) {
					if (obj.getX() >= tempObject.getX() && tempObject.getX() > obj.getX() + velX + obj.getWidth()) {
						obj.setX(tempObject.getX() - obj.getWidth());
						return;
					}
					if (next.intersects(tempObject.getBounds())) {
						obj.setX(tempObject.getX() - obj.getWidth());
						return;
					}

				}
			}
			obj.setX((int) (obj.getX() + (int) obj.getVelX()));
			for (GameObject tempObject : handler.objects) {
				if (obj instanceof Wall) {
					Wall wall = (Wall) tempObject;
					if (obj instanceof Player) {
						Player p = (Player) obj;
						if (obj.getX() == (wall.getX() - obj.getWidth())
								|| obj.getX() == (wall.getX() + wall.getWidth())) {
							hit = true;
							p.onWall = true;
							p.numJumps = 0;
						}
					}

				}
			}
			if (!hit) {
				if (obj instanceof Player) {
					((Player) obj).onWall = false;
				}
			}
		} catch (Exception e) {

		}

	}

	public void tryYMove(GameObject obj, int velY) {
		try {
			Rectangle next = new Rectangle(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight() + velY);
			for (GameObject tempObject : handler.objects) {
				if (!tempObject.equals(obj)) {
					if (next.intersects(tempObject.getBounds())) {
						if (obj.getY() < tempObject.getY()) {
							int yDiff = (obj.getY() + velY) - tempObject.getY();
							obj.setY(obj.getY() - yDiff - obj.getHeight());
							if (obj instanceof Player) {
								((Player) obj).onGround = true;
								((Player) obj).numJumps = 0;
							}
						} else {
							if (obj instanceof Player) {
								((Player) obj).hit();
							}
							int yDiff = (obj.getY() + velY) - tempObject.getY();
							obj.setY(obj.getY() + yDiff);
						}
					}
				}
			}
			obj.setY((int) (obj.getY() + velY));
		} catch (Exception e) {

		}
	}
}
