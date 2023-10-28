package entities;

public abstract class Entity {

	protected float x, y;
	protected int width, height;
	protected int state;
	protected int animationIndex, animationCounter;
	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public int getState() {
		return state;
	}
	
	public int getAnimationIndex() {
		return animationIndex;
	}
	
}
