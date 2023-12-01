package states;

import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOver implements StateMethods {

    private Playing playing;
    public GameOver(Playing playing) {
        this.playing = playing;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0,0,0, 150));
        g.fillRect(0,0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.setColor(Color.white);
        g.drawString("Game Over", Game.GAME_WIDTH / 2, 150);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            playing.restartGame();
            Gamestate.state = Gamestate.MENU;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
