package states;

import main.Game;
import utils.Import;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class LevelCompleted {

    private Playing playing;
    private BufferedImage backgroundImage;

    // Font
    private Font font = new Font("STENCIL", Font.BOLD, (int)(20 * Game.SCALE));

    public LevelCompleted(Playing playing) {
        this.playing = playing;
        backgroundImage = Import.importImage(Import.MENU_BACKGROUND_IMAGE);
    }

    public void draw(Graphics g) {

        // Darker background
        g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        // Text
        String gameOverString = "LEVEL COMPLETED";
        String continueString = "Press space to continue";

        // Getting size of text & setting position & printing
        FontMetrics fm = g.getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(gameOverString, (Game.GAME_WIDTH - fm.stringWidth(gameOverString)) / 2, (Game.GAME_HEIGHT - fm.getHeight()) / 2 - (int)(75 * Game.SCALE));
        g.drawString(continueString, (Game.GAME_WIDTH - fm.stringWidth(continueString)) / 2, (Game.GAME_HEIGHT - fm.getHeight()) - (int)(20 * Game.SCALE));

    }

    // Keyboard
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            playing.loadNextLevel();
        }
    }
}
