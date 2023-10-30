package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

// Abstract class for all entities

public abstract class Entity {

	// Basic info
	protected float x, y;
	protected int width, height;
	protected Rectangle2D.Float hitbox;
	
	// Animation of entity
	protected int state;
	protected int animationIndex, animationCounter; // Needed for animation
	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	// Debugging only
	protected void drawHitbox(Graphics g) {	
		g.setColor(Color.BLACK);
		g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}
	
	protected void initHitbox(int width, int height) {
		hitbox = new Rectangle2D.Float(x, y, (int)(width * Game.SCALE), (int)(height * Game.SCALE));
	}
	
	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}
	
	public int getState() {
		return state;
	}
	
	public int getAnimationIndex() {
		return animationIndex;
	}
	
}
