package entities;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utils.Enemies.*;

public abstract class Enemy extends Entity {

    protected int type, state;
    protected int moveDirection = LEFT;
    protected int enemyY;
    protected boolean first = true;

    protected boolean attackChecked;
    protected float attackRange = Game.TILES_SIZE;

    protected int maxHealth, currentHealth;
    protected boolean active = true;

    public Enemy(float x, float y, int width, int height, int type) {
        super(x, y, width, height);
        this.type = type;
        initHitbox(x,y,width, height);
        maxHealth = maxHealthOfEntity(type);
        currentHealth = maxHealth;
    }

    // Animation counter decides which sprite should be shown
    protected void updateAnimationCounter() {
        animationCounter++;
        if(animationCounter >= ANIMATION_SPEED) {
            animationCounter = 0;
            animationIndex++;
            if(animationIndex >= howManySprites(type, state)) {
                animationIndex = 0;
                if(state == ATTACK)
                    state = IDLE;
                else if(state == DEATH)
                    active = false;
            }
        }
    }

    protected void changeState(int state) {
        this.state = state;
        animationCounter = 0;
        animationIndex = 0;
    }

    // HP change
    public void hurt(int value) {
        currentHealth -= value;
        if(currentHealth <= 0)
            changeState(DEATH);
    }

    // Attacking
    // Checks if player is inside of viewing area of entity
    protected boolean playerInViewingArea(int[][] levelData, Player player) {
        int playerY = (int)(player.getHitbox().y / Game.TILES_SIZE);
        if(playerY == enemyY)
            if(playerInAttackRange(player)) {
                if(noObstacle(levelData, hitbox, player.hitbox, enemyY))
                    return true;
            }
        return false;
    }

    protected void checkIfPlayerWasHurt(Rectangle2D.Float attackBox, Player player) {
        if(attackBox.intersects(player.hitbox))
            player.changeHP(-strengthOfEnemy(type));
        attackChecked = true;
    }

    protected boolean playerInAttackRange(Player player) {
        int distance = (int)Math.abs(player.hitbox.x - hitbox.x);
        return distance <= attackRange * 5;
    }

    protected boolean canAttack(Player player) {
        int distance = (int)Math.abs(player.hitbox.x - hitbox.x);
        return distance <= attackRange;
    }

    // Movement
    protected void face2Player(Player player) {
        if(player.hitbox.x > hitbox.x) moveDirection = RIGHT;
        else moveDirection = LEFT;
    }

    protected void changeDirection() {
        if(moveDirection == LEFT) moveDirection = RIGHT;
        else moveDirection = LEFT;
    }

    // Needed in order to change the look of entity based on direction
    public int flipX() {
        if (moveDirection == RIGHT) return width;
        else return 0;
    }

    public int flipW() {
        if (moveDirection == RIGHT) return -1;
        else return 1;
    }

    // Resetting
    public void resetEnemy() {
        hitbox.x = x;
        hitbox.y = y;
        first = true;
        currentHealth = maxHealth;
        changeState(IDLE);
        active = true;
        fallSpeed = 0;
    }

    // Getters & setters
    public int getState() { return state; }

    public boolean isActive() { return active; }

}
