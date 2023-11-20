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
	protected int maxHealth;
	protected int currentHealth;
	
	// Animation of entity
	protected int state;
	protected int animationIndex, animationCounter; // Needed for animation
	protected static int ANIMATION_SPEED = 25;
	
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
	
	protected void initHitbox(float width, float height) {
		hitbox = new Rectangle2D.Float(x, y, width, height);
	}
	
	protected static float getCloserToWall(Rectangle2D.Float hitbox, float speed) {
		int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
		if (speed > 0) {
			// Right
			int tileX = currentTile * Game.TILES_SIZE;
			int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
			return tileX + xOffset - 1;
		} else
			// Left
			return currentTile * Game.TILES_SIZE;
	}
	
	protected static float getCloserToFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
		if (airSpeed > 0) {
			// Falling - touching floor
			int tileY = currentTile * Game.TILES_SIZE;
			int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
			return tileY + yOffset - 1;
		} else
			// Jumping
			return currentTile * Game.TILES_SIZE;

	}
	
	protected static boolean isPossibleToMove(float x, float y, float width, float height, int[][] levelData) {
		// Checking if any part of character overlaps block
		if(!solidBlock(x, y, levelData))
			if(!solidBlock(x + width, y, levelData))
				if(!solidBlock(x, y + height, levelData))
					if(!solidBlock(x + width, y + height, levelData))
						return true;	
		return false;
	}
	
	protected static boolean solidBlock(float x, float y, int[][] levelData) {
		// Checking if in window
		int maxLevelWidth = levelData[0].length * Game.TILES_SIZE;
		if(x < 0 || x >= maxLevelWidth) return true;
		if(y < 0 || y >= Game.GAME_HEIGHT) return true;
		
		// Finding position
		float xPos = x / Game.TILES_SIZE;
		float yPos = y / Game.TILES_SIZE;
		int lvlData = levelData[(int)yPos][(int)xPos];
		
		// Looking if block on position is solidBlock (according to tileset)
		if(lvlData < 0 || lvlData > 29 || lvlData != 5) return true;
		
		return false;
	}
	
	protected static boolean isOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {
		if (!solidBlock(hitbox.x, hitbox.y + hitbox.height + 1, levelData))
			if (!solidBlock(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData))
					return false;
		return true;
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
