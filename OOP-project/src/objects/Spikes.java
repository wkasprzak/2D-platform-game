package objects;

import main.Game;

public class Spikes extends InGameObject {

    public Spikes(int x, int y, int type) {
        super(x, y, type);
        initHitbox(32,16);
        xOffset = (int)(0 * Game.SCALE);
        yOffset = (int)(8 * Game.SCALE);

        hitbox.y += yOffset;
    }

}
