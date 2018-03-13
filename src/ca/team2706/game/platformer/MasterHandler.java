package ca.team2706.game.platformer;

import java.awt.Graphics;
import java.util.LinkedList;

import ca.team2706.game.platformer.objects.GameOver;
import ca.team2706.game.platformer.objects.Wall;

public class MasterHandler {

	public Platformer platformer;
	public Wall wall;
	public LinkedList<GameObject> objects = new LinkedList<GameObject>();
	public LinkedList<GameObject> shopObjects = new LinkedList<GameObject>();
	public boolean gameOver = false;
	public boolean inShop = false;

	public MasterHandler(Platformer platformer) {
		this.platformer = platformer;
	}

	public void tick() {
		try {
			if (!inShop) {
				for (GameObject obj : objects) {
					obj.tick();
				}
			} else {
				for (GameObject obj : shopObjects) {
					obj.tick();
				}
			}
		} catch (Exception e) {

		}
		wall.tick();
	}

	public void render(Graphics g) {
		try {
			if (!inShop) {
				for (GameObject obj : objects) {
					obj.render(g);
				}
			} else {
				for (GameObject obj : shopObjects) {
					obj.render(g);
				}
			}
		} catch (Exception e) {

		}
		if (!gameOver) {
			wall.render(g);
		}
	}

	public void addObject(GameObject obj) {
		if (!inShop) {
			objects.add(obj);
		}
	}

	public void removeObject(GameObject obj) {
		objects.remove(obj);
	}

	public void gameOver() {
		gameOver = true;
		try {
			for (GameObject obj : objects) {
				if (!(obj instanceof GameOver)) {
					removeObject(obj);
				}
			}
		} catch (Exception e) {

		}
	}

	public void restart() {
		objects.clear();
		platformer.genPlayer();
		platformer.score = 0;
		platformer.difficulty = 1000;
		gameOver = false;
	}

	public void shop() {
		inShop = !inShop;
	}

}
