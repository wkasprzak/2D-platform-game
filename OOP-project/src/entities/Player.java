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
	private boolean left, right, jump, down;
	private int facedLeft = 1, flipX = 0;
	
	// Level
	private int[][] levelData;
		
	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		this.state = IDLE;
		loadGraphics();
	}
		
	private void setAction() {
		int startState = state;
		if(moving)
			state = RUNNING;
		else if(!moving)
			state = IDLE;
		
	}
	
	public void draw(Graphics g) {
		g.drawImage(charactersAppearance[state][animationIndex], (int)x + (int)Game.SCALE * flipX / 2, (int)y, (int)Game.SCALE * width * facedLeft / 2, (int)Game.SCALE * height / 2, null);
	}
	
	public void update() {
		changePosition();
		updateAnimationCounter();
		setAction();
		
	}
	
	private void updateAnimationCounter() {
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
			this.x -= 5;
			facedLeft = -1;
			flipX = width;
		} else if (right) {
			this.x += 5;
			facedLeft = 1;
			flipX = 0;
		} else if (jump) {
			this.y -= 5;
		} else if (down) {
			this.y += 5;
		}
		moving = true;
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

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}
}
