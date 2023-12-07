package objects;

import entities.Player;
import levels.Level;
import main.Game;
import states.Playing;
import utils.Import;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static objects.InGameObject.*;

public class ObjectHandler {

    private Playing playing;
    private BufferedImage fishImage;
    private BufferedImage spikesImage;
    private ArrayList<Fish> fish;
    private ArrayList<Spikes> spikes;

    public ObjectHandler(Playing playing) {
        this.playing = playing;
        loadImages();
    }

    public void update() {
        for (Fish f : fish)
            if (f.isActive())
                f.update();
    }

    // Environment & player
    public void checkSpikesTouched(Player player) {
        for(Spikes s : spikes) {
            if(s.getHitbox().intersects(player.getHitbox()))
                player.changeHP(-6);
        }
    }

    public void checkObjectTouched(Rectangle2D.Float hitbox) {
        for (Fish f : fish)
            if (f.isActive()) {
                if (hitbox.intersects(f.getHitbox())) {
                    f.setActive(false);
                    effect(f);
                }
            }
    }

    public void effect(Fish f) {
        if (f.getType() == InGameObject.FISH)
            playing.getPlayer().changeHP(FISH_VALUE);
    }

    // Loading
    public void loadObjects(Level newLevel) {
        fish = new ArrayList<>(newLevel.getFish());
        spikes = newLevel.getSpikes();
    }

    private void loadImages() {
        fishImage = Import.importImage(Import.NEMO);
        spikesImage = Import.importImage(Import.SPIKES);
    }

    // Drawing
    public void draw(Graphics g, int xOffset) {
        drawFish(g, xOffset);
        drawSpikes(g, xOffset);
    }

    private void drawSpikes(Graphics g, int xOffset) {
        for (Spikes s : spikes)
            g.drawImage(spikesImage, (int)(s.getHitbox().x - xOffset), (int)(s.getHitbox().y - s.getYOffset()), SPIKES_WIDTH, SPIKES_HEIGHT,null);
    }

    private void drawFish(Graphics g, int xOffset) {
        for (Fish f : fish)
            if (f.isActive()) {
                g.drawImage(fishImage, (int) (f.getHitbox().x - f.getXOffset() - xOffset), (int) (f.getHitbox().y - f.getYOffset()), (int) (16 * Game.SCALE), (int) (16 * Game.SCALE), null);
            }
    }

    // Reset
    public void resetObjects() {

        loadObjects(playing.getLevelHandler().getCurrentLevel());

        for (Fish f : fish)
            f.reset();
    }

}