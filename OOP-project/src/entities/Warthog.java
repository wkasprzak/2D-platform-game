package entities;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utils.Enemies.*;

public class Warthog extends Enemy {

    protected Rectangle2D.Float attackBox;

    public Warthog(float x, float y) {
        super(x, y, WARTHOG_WIDTH, WARTHOG_HEIGHT, WARTHOG);
        initHitbox(x,y,33* Game.SCALE,17*Game.SCALE);
        initAttackBox();
    }

    // Attack
    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x,y,(int)(20 * Game.SCALE), (int)(16*Game.SCALE));
    }

    private void updateAttackBox() {
        if(moveDirection == RIGHT) {
            attackBox.x = hitbox.x + hitbox.width / 2 + (int)(5 * Game.SCALE);
        } else if(moveDirection == LEFT) {
            attackBox.x = hitbox.x - (int)(7 * Game.SCALE);
        }
        attackBox.y = hitbox.y;
    }

    // Basics
    public void update(int levelData[][], Player player) {
        behaviour(levelData, player);
        updateAnimationCounter();
        updateAttackBox();
    }

    private void behaviour(int levelData[][], Player player) {
        if(first) {
            if(!isOnFloor(hitbox, levelData))
                inAir = true;
            first = false;
        }

        if(inAir) {
            if(isPossibleToMove(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
            } else {
                inAir = false;
                hitbox.y = (int) (hitbox.y / Game.TILES_SIZE) * Game.TILES_SIZE + hitbox.height - 5;
                enemyY = (int)(hitbox.y / Game.TILES_SIZE);
            }
        } else {
            switch(state) {
                case IDLE:
                    changeState(WALK);
                    break;
                case WALK:
                    if(playerInViewingArea(levelData,player)) {
                        face2Player(player);
                        if (canAttack(player))
                            changeState(ATTACK);
                    }

                    float speed = 0;
                    if(moveDirection == LEFT)
                        speed -= entitySpeed;
                    else
                        speed += entitySpeed;

                    if(isPossibleToMove(hitbox.x + speed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
                        if(floor(hitbox, speed, levelData)) {
                            hitbox.x += speed;
                            return;
                        }
                    }
                    changeDirection();
                    break;
                case ATTACK:
                    if(animationIndex == 0)
                        attackChecked = false;

                    if(animationIndex == 2 && !attackChecked)
                        checkIfPlayerWasHurt(attackBox, player);
                    break;
            }
        }
    }

}
