package entities;

import levels.Level;
import states.Playing;
import utils.Import;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utils.Enemies.*;

public class Enemies {

    private Playing playing;

    private BufferedImage[][] warthogAppearance;
    private ArrayList<Warthog> warthogs = new ArrayList<>();

    public Enemies(Playing playing) {
        this.playing = playing;
        loadEnemySprites();
    }

    // Adding warthogs
    public void addEnemies(Level level) {
        warthogs = level.getWarthogs();
    }

    public void draw(Graphics g, int offset) {
        drawWarthogs(g, offset);
    }

    private void drawWarthogs(Graphics g, int offset) {
        for(Warthog w : warthogs)
            if (w.isActive()) {
                g.drawImage(warthogAppearance[w.getState()][w.getAnimationIndex()],(int)w.getHitbox().x - offset - WARTHOG_XOFFSET + w.flipX(),(int)w.getHitbox().y - WARTHOG_YOFFSET, WARTHOG_WIDTH * w.flipW(), WARTHOG_HEIGHT, null);
                //w.drawAttackBox(w.attackBox, g,offset);
                //w.drawHitbox(g, offset);
            }
    }

    private void loadEnemySprites() {
        warthogAppearance = new BufferedImage[4][4];
        BufferedImage image = Import.importImage(Import.WARTHOG);
        for (int i = 0; i < warthogAppearance.length; i++) {
            for (int j = 0; j < warthogAppearance[i].length; j++) {
                warthogAppearance[i][j] = image.getSubimage(j * WARTHOG_WIDTH_DEFAULT, i * WARTHOG_HEIGHT_DEFAULT, WARTHOG_WIDTH_DEFAULT, WARTHOG_HEIGHT_DEFAULT);
            }
        }
    }

    // Maintenance
    public void update(int levelData[][], Player player) {
        boolean isAnyActive = false;
        for(Warthog w : warthogs)
            if(w.isActive()) {
                w.update(levelData, player);
                isAnyActive = true;
            }
        if(!isAnyActive) {
            playing.setLevelCompleted(true);
        }
    }

    public void checkIfHit(Rectangle2D.Float attackBox) {
        for(Warthog w : warthogs) {
            if(w.isActive())
                if(attackBox.intersects(w.getHitbox())) {
                    w.hurt(5);
                    return;
                 }
        }
    }

    public void resetEnemies() {
        for(Warthog w : warthogs)
            w.resetEnemy();
    }
}
