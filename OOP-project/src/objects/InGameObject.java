package objects;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class InGameObject {

    protected int x, y, type;
    protected Rectangle2D.Float hitbox;
    protected boolean active = true;
    protected int xOffset, yOffset;

    public static final int FISH = 32;
    public static final int FISH_VALUE = 1;

    public static final int SPIKES = 33;
    public static final int SPIKES_WIDTH_DEF = 32;
    public static final int SPIKES_HEIGHT_DEF = 32;
    public static final int SPIKES_WIDTH = (int)(0.5 * SPIKES_WIDTH_DEF * Game.SCALE);
    public static final int SPIKES_HEIGHT = (int)(0.5 * SPIKES_HEIGHT_DEF * Game.SCALE);


    public InGameObject(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    // Hitbox
    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    public void drawHitbox(Graphics g, int offset) {
        g.setColor(Color.BLACK);
        g.drawRect((int) hitbox.x - offset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    public void reset() {
        active = true;
    }

    // Getters & setters
    public int getType() {
        return type;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

}