package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.Import;

import static entities.EntitiesState.PlayerStates.*;
import static entities.EntitiesState.ANIMATION_SPEED;

public class Player extends Entity{
	
	// Appearance
	private BufferedImage[][] charactersAppearance;
	
	// Moving
	private boolean moving = false;
	private boolean left, right, jump;
	private int facedRight = 1, flipX = 0; // Changing the appearance of the character depending on the direction
	private float speed = 2.0f * Game.SCALE;
	
	// Hitbox
	private float minimalisationX = 7 * Game.SCALE;
	private float minimalisationY = 16 * Game.SCALE;
	
	// Level
	private int[][] levelData;
		
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		this.state = IDLE;
		loadGraphics();
		initHitbox(17,16);
	}
		
	private void setAction() {
		int startState = state;
		if(moving)
			state = RUNNING;
		else if(!moving)
			state = IDLE;
	}
	
	public void draw(Graphics g) {
		g.drawImage(charactersAppearance[state][animationIndex], (int)(hitbox.x - minimalisationX) + flipX, (int)(hitbox.y - minimalisationY), width * facedRight, height, null);
		drawHitbox(g);
	}
	
	public void update() {
		changePosition();
		updateAnimationCounter();
		setAction();	
	}
	
	private void updateAnimationCounter() {
		// Animation counter decides which sprite should be shown
		animationCounter++;
		if(animationCounter >= ANIMATION_SPEED) {
			animationCounter = 0;
			animationIndex++;
			if(animationIndex >= getSpriteAmount(state)) {
				animationIndex = 0;
			}
		}
	}
	
	private void changePosition() {

		moving = false;
		if((!left && !right) || (right && left)) 
			return;
		
		if(left) {
			if(isPossibleToMove(hitbox.x - speed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
				// Updating position
				hitbox.x -= speed;
				moving = true;
				// Changing animation side
				facedRight = -1;
				flipX = width;
			}
		} else if (right) {		
			if(isPossibleToMove(hitbox.x + speed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
				// Updating position
				hitbox.x += speed;
				moving = true;
				// Changing animation side
				facedRight = 1;
				flipX = 0;
			}
		} else if (jump) {
			this.y -= 5;
		}
	}
	
	private static boolean isPossibleToMove(float x, float y, float width, float height, int[][] levelData) {
		// Checking if any part of character overlaps block
		if(!solidBlock(x, y, levelData))
			if(!solidBlock(x + width, y, levelData))
				if(!solidBlock(x, y + height, levelData))
					if(!solidBlock(x + width, y + height, levelData))
						return true;	
		return false;
	}
	
	private static boolean solidBlock(float x, float y, int[][] levelData) {
		// Checking if in window
		if(x < 0 || x >= Game.GAME_WIDTH) return true;
		if(y < 0 || y >= Game.GAME_HEIGHT) return true;
		
		// Finding position
		float xPos = x / Game.TILES_SIZE;
		float yPos = y / Game.TILES_SIZE;
		int lvlData = levelData[(int)yPos][(int)xPos];
		
		// Looking if block on position is solidBlock (according to tileset)
		if(lvlData < 0 || lvlData > 29 || lvlData != 5) return true;
		
		return false;
	}
	
	private void loadGraphics() {
		BufferedImage image = Import.ImportData(Import.PLAYER);
		charactersAppearance = new BufferedImage[51][8];
		for(int i = 0; i < charactersAppearance.length; i++)
			for(int j = 0; j < charactersAppearance[i].length; j++)
				charactersAppearance[i][j] = image.getSubimage(j*32, i*32, 32, 32);
	}
	
	public void LoadLevelData(int[][] levelData) {
		this.levelData = levelData;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

}
