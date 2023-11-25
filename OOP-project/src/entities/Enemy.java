package entities;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utils.Enemies.*;

public abstract class Enemy extends Entity {

    protected int type, state;
    protected int moveDirection = LEFT;
    protected boolean first = true;
    protected int enemyY;
    protected float attackRange = Game.TILES_SIZE;

    public Enemy(float x, float y, int width, int height, int type) {
        super(x, y, width, height);
        this.type = type;
        initHitbox(width, height);
    }

    protected void updateAnimationCounter() {
        // Animation counter decides which sprite should be shown
        animationCounter++;
        if(animationCounter >= ANIMATION_SPEED) {
            animationCounter = 0;
            animationIndex++;
            if(animationIndex >= howManySprites(type, state)) {
                animationIndex = 0;
                if(state == ATTACK)
                    state = IDLE;
            }
        }
    }

    protected void changeState(int state) {
        this.state = state;
        animationCounter = 0;
        animationIndex = 0;
    }

    protected boolean playerInViewingArea(int[][] levelData, Player player) {
        int playerY = (int)(player.getHitbox().y / Game.TILES_SIZE);
        if(playerY == enemyY)
            if(playerInAttackRange(player)) {
                if(noObstacle(levelData, hitbox, player.hitbox, enemyY))
                    return true;
            }
        return false;
    }

    protected boolean playerInAttackRange(Player player) {
        int distance = (int)Math.abs(player.hitbox.x - hitbox.x);
        return distance <= attackRange * 5;
    }

    protected boolean canAttack(Player player) {
        int distance = (int)Math.abs(player.hitbox.x - hitbox.x);
        return distance <= attackRange;
    }

    protected void face2Player(Player player) {
        if(player.hitbox.x > hitbox.x) moveDirection = RIGHT;
        else moveDirection = LEFT;
    }

    protected void changeDirection() {
        if(moveDirection == LEFT)
            moveDirection = RIGHT;
        else moveDirection = LEFT;
    }

    public int flipX() {
        if (moveDirection == RIGHT)
            return width;
        else
            return 0;
    }

    public int flipW() {
        if (moveDirection == RIGHT)
            return -1;
        else
            return 1;

    }

    public int getIndex() {
        return animationIndex;
    }

    public int getState() {
        return state;
    }

}
