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
	public static int ANIMATION_SPEED = 25;

	// Moving
	protected float entitySpeed = 0.7f * Game.SCALE;

	// Jumping && Falling
	protected float airSpeed = 0f;
	protected float fallSpeed = 0.5f * Game.SCALE;;
	protected float gravity = 0.04f * Game.SCALE;
	protected float jumpSpeed = -2.25f * Game.SCALE;
	protected boolean inAir = false;

	// Directions
	public static final int LEFT = 0;
	public static final int RIGHT = 2;
	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	// Hitbox & attackBox
	protected void initHitbox(float x, float y, float width, float height) {
		hitbox = new Rectangle2D.Float(x, y, width, height);
	}

	// Debugging only
	protected void drawHitbox(Graphics g, int offset) {
		g.setColor(Color.BLACK);
		g.drawRect((int) hitbox.x - offset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}

	protected void drawAttackBox(Rectangle2D.Float attackBox, Graphics g, int offset) {
		g.setColor(Color.RED);
		g.drawRect((int)attackBox.x - offset, (int)attackBox.y,(int)attackBox.width,(int)attackBox.height);
	}

	// Movement
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
		if (airSpeed > 0) { // Falling
			return currentTile * Game.TILES_SIZE + hitbox.height - 1;
		} else
			return currentTile * Game.TILES_SIZE;

	}
	
	protected static boolean isPossibleToMove(float x, float y, float width, float height, int[][] levelData) {
		// Checking if any part of character overlaps block
		if (!solidBlock(x, y, levelData))
			if (!solidBlock(x + width, y + height, levelData))
				if (!solidBlock(x + width, y, levelData))
					if (!solidBlock(x, y + height, levelData))
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
		if(lvlData < 0 || lvlData > 51 || (lvlData != 5 && lvlData != 31 && lvlData != 30 && lvlData != 32 && lvlData != 33)) return true;
		
		return false;
	}
	
	protected static boolean isOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {
		if (!solidBlock(hitbox.x, hitbox.y + hitbox.height + 1, levelData))
			if (!solidBlock(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData))
					return false;
		return true;
	}

	protected static boolean floor(Rectangle2D.Float hitbox, float speed, int[][] levelData) {
		if(speed > 0)
			return solidBlock(hitbox.x + hitbox.width + speed, hitbox.y + hitbox.height + 1, levelData);
		else
			return solidBlock(hitbox.x + speed, hitbox.y + hitbox.height + 1, levelData);
	}

	protected boolean noObstacle(int[][] levelData, Rectangle2D.Float hitbox, Rectangle2D.Float hitbox1, int height) {
		int firstX = (int)(hitbox.x / Game.TILES_SIZE);
		int secondX = (int)(hitbox1.x / Game.TILES_SIZE);
		if(firstX > secondX)
			return tilesAreWalkable(secondX, firstX, height, levelData);
		else
			return tilesAreWalkable(firstX, secondX, height, levelData);
	}

	protected boolean tilesAreWalkable(int start, int end, int height, int[][] levelData) {
		for (int i = 0; i < end - start; i++){
			if(solidBlock(start + i, height, levelData))
				return false;
			if(!solidBlock(start + i, y + 1, levelData))
				return false;
		}
		return true;
	}

	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}
	
	public int getAnimationIndex() {
		return animationIndex;
	}
	
}
