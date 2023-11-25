package entities;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utils.Enemies.*;

public class Warthog extends Enemy {

    // Attackbox
    protected Rectangle2D.Float attackBox;

    public Warthog(float x, float y) {
        super(x, y, WARTHOG_WIDTH, WARTHOG_HEIGHT, WARTHOG);
        initHitbox((int)(33* Game.SCALE),(int)(18*Game.SCALE));
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x,y,(int)(20 * Game.SCALE), (int)(18*Game.SCALE));
    }

    public void update(int levelData[][], Player player) {
        updateMove(levelData, player);
        updateAnimationCounter();
        updateAttackBox();
    }

    private void updateAttackBox() {
        if(moveDirection == RIGHT) {
            attackBox.x = hitbox.x + hitbox.width / 2 + (int)(5 * Game.SCALE);
        } else if(moveDirection == LEFT) {
            attackBox.x = hitbox.x - (int)(7 * Game.SCALE);
        }
        attackBox.y = hitbox.y;
    }

    private void updateMove(int levelData[][], Player player) {
        if(first) {
            if(!isOnFloor(hitbox, levelData))
                inAir = true;
            first = false;
        }

        if(inAir) {
            if(isPossibleToMove(hitbox.x, hitbox.y + fallSpeed, hitbox.width, hitbox.height, levelData)) {
                hitbox.y += fallSpeed;
                fallSpeed += gravity;
            } else {
                inAir = false;
                enemyY = (int)(hitbox.y / Game.TILES_SIZE);
            }
        } else {
            switch(state) {
                case IDLE:
                    changeState(WALK);
                    break;
                case WALK:
                    if(playerInViewingArea(levelData,player))
                        face2Player(player);
                    if(canAttack(player))
                        changeState(ATTACK);

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
            }
        }
    }

}
