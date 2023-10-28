package entities;

public class EntitiesState {
	
	public static int ANIMATION_SPEED = 15;

	public static class PlayerStates {
		public static final int IDLE = 0;
		public static final int RUNNING = 4;
		public static final int JUMPING = 19;
		public static final int FALLING = 20;
		public static final int ATTACK = 39;
		public static final int HIT = 52;
		public static final int DEAD = 53; 
		
		public static int getSpriteAmount(int player_action) {
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
	}
}
