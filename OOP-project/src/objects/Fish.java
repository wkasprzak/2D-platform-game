package objects;

import main.Game;

public class Fish extends InGameObject {

    private float hoverOffset;
    private int maxHoverOffset, hoverDir = 1;

    public Fish(int x, int y, int type) {
        super(x, y, type);
        initHitbox(16,16);
        xOffset = (int)(2 * Game.SCALE);
        yOffset = (int)(2 * Game.SCALE);
        maxHoverOffset = (int) (4 * Game.SCALE);
    }

    public void update() {
        updateHover();
    }

    // Hover
    private void updateHover() {
        hoverOffset += (0.075f * Game.SCALE * hoverDir);

        if (hoverOffset >= maxHoverOffset) hoverDir = -1;
        else if (hoverOffset < 0) hoverDir = 1;

        hitbox.y = y + hoverOffset;
    }
}

