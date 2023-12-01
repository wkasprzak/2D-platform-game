package entities;

import levels.LevelHandler;
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
        addEnemies();
    }

    private void addEnemies() {
        warthogs = LevelHandler.getWarthogs();
    }

    public void update(int levelData[][], Player player) {
        for(Warthog w : warthogs)
            if(w.isActive())
                w.update(levelData, player);
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

    public void checkIfHit(Rectangle2D.Float attackBox) {
        for(Warthog w : warthogs) {
            if(w.isActive())
                if(attackBox.intersects(w.getHitbox())) {
                    w.hurt(5);
                    return;
                 }
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

    public void resetEnemies() {
        for(Warthog w : warthogs) {
            w.resetEnemy();
        }
    }
}
