package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.Import;

public class Player extends Entity{
	
	// Appearance
	private BufferedImage[][] charactersAppearance;
	
	// Moving
	private boolean moving = false, attacking = false;
	private boolean left, right, jump;
	private int facedRight = 1, flipX = 0; // Changing the appearance of the character depending on the direction
	private float playerSpeed = 1.0f * Game.SCALE;
	
	// Jumping && Falling
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeed = 0.5f * Game.SCALE;
	private boolean inAir = false;
	
	// Hitbox
	private float minimalisationX = 9 * Game.SCALE;
	private float minimalisationY = 17 * Game.SCALE;
	
	// Level
	private int[][] levelData;
	
	// Number of row of Cat-Sheet.png in which state occurs 
	private static final int IDLE = 0;
	private static final int RUNNING = 4;
	private static final int JUMPING = 19;
	private static final int FALLING = 20;
	private static final int ATTACK = 39;
	private static final int HIT = 52;
	private static final int DEAD = 53; 
	
	// How many different looks state has
	private static int howManyPics(int player_action) {
		switch(player_action) {
		case IDLE:
		case RUNNING:
		case DEAD:
			return 8;
		case ATTACK:
		case JUMPING:
		case FALLING:
			return 4;
		case HIT:
			return 2;
		default: 
			return 1;
		}
	}
		
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		this.state = IDLE;
		this.maxHealth = 100;
		this.currentHealth = maxHealth;
		loadGraphics();
		initHitbox(15 * Game.SCALE, 14 * Game.SCALE);
	}
		
	private void setAction() {
		int startState = state;
		if(moving)
			state = RUNNING;
		else 
			state = IDLE;
		
		if(inAir) {
			if(airSpeed < 0) state = JUMPING;
			else state = FALLING;
		}
		
		if(startState != state) resetAnimation();
	}
	
	private void resetAnimation() {
		animationCounter = 0;
		animationIndex = 0;
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
			if(animationIndex >= howManyPics(state)) {
				animationIndex = 0;
			}
		}
	}
	
	private void changePosition() {

		moving = false;
		if(jump && !inAir) {
			inAir = true;
			airSpeed = jumpSpeed;
		}
				
		if(!inAir && ((!left && !right) || (right && left))) return;		

		float speed = 0;
		
		if(left) {
			speed -= playerSpeed;
			// Changing animation side
			facedRight = -1;
			flipX = width;
		}
		if (right) {	
			speed += playerSpeed;
			// Changing animation side
			facedRight = 1;
			flipX = 0;
		}
		
		if(!isOnFloor(hitbox, levelData) && !inAir)
			inAir = true;
		
		if(inAir) {
			if(isPossibleToMove(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateX(speed);
			} else {
				hitbox.y = getCloserToFloor(hitbox, airSpeed);
				if(airSpeed > 0) {
					inAir = false;
					airSpeed = 0;
				} else airSpeed = fallSpeed;
				updateX(speed);
			}
		} else updateX(speed);
		
		moving = true;
	}
	
	private void updateX(float speed) {
		if(isPossibleToMove(hitbox.x + speed, hitbox.y, hitbox.width, hitbox.height, levelData)) hitbox.x += speed;
		else hitbox.x = getCloserToWall(hitbox, speed);
	}

	private void changeHealth(int value) {
		
	}

	private void loadGraphics() {
		BufferedImage image = Import.ImportData(Import.PLAYER);
		charactersAppearance = new BufferedImage[51][8];
		for(int i = 0; i < charactersAppearance.length; i++)
			for(int j = 0; j < charactersAppearance[i].length; j++)
				charactersAppearance[i][j] = image.getSubimage(j*32, i*32, 32, 32);
	}
	
	public void loadLevelData(int[][] levelData) {
		this.levelData = levelData;
		if(!isOnFloor(hitbox, levelData)) inAir = true;
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
