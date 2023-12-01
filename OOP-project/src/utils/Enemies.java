package utils;

import main.Game;

public class Enemies {

    // Types of enemies
    public static final int WARTHOG = 31;

    // States list
    public static final int IDLE = 0;
    public static final int ATTACK = 1;
    public static final int DEATH = 2;
    public static final int WALK = 3;

    // Sizes of enemies
    public static final int WARTHOG_WIDTH_DEFAULT = 64;
    public static final int WARTHOG_HEIGHT_DEFAULT = 64;
    public static final int WARTHOG_WIDTH = (int)(WARTHOG_WIDTH_DEFAULT * Game.SCALE);
    public static final int WARTHOG_HEIGHT = (int)(WARTHOG_HEIGHT_DEFAULT * Game.SCALE);

    // Offset
    public static final int WARTHOG_XOFFSET = (int)(14 * Game.SCALE);
    public static final int WARTHOG_YOFFSET = (int)(30 * Game.SCALE);

    // Help function which informs how many different textures there are in one state
    public static int howManySprites(int type, int state) {
        switch(type) {
            case WARTHOG:
                switch (state) {
                    case IDLE:
                    case DEATH:
                    case ATTACK:
                    case WALK:
                        return 4;
                }
        }
        return 0;
    }

    // Max health of enemies of specific type
    public static int maxHealthOfEntity(int type) {
        switch(type) {
            case WARTHOG:
                return 5;
            default:
                return 1;
        }
    }

    // Damage which the enemy of specific type causes
    public static int strengthOfEnemy(int type) {
        switch(type) {
            case WARTHOG:
                return 1;
            default:
                return 0;
        }
    }

}
