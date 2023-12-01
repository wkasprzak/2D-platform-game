package states;

import main.Game;
import utils.Import;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameOver implements StateMethods {

    private Playing playing;
    public static JButton tryAgainButton, backToMenuButton;
    private BufferedImage angel, backgroundImage;

    // Font
    private Font font = new Font("STENCIL", Font.BOLD, (int)(20 * Game.SCALE));
    private Font font1 = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));

    public GameOver(Playing playing) {
        this.playing = playing;
        angel = Import.importImage(Import.ANGEL);
        backgroundImage = Import.importImage(Import.MENU_BACKGROUND_IMAGE);
        createButtons();
    }

    @Override
    public void update() {
        showButtons();
        tryAgainButtonAction();
        backToMenuButtonAction();
    }

    @Override
    public void draw(Graphics g) {

        // Darker background
        g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        // Image of angel cat
        g.drawImage(angel, Game.GAME_WIDTH / 2 - (int)(32 * Game.SCALE), Game.GAME_HEIGHT / 2 - (int)(50 * Game.SCALE), (int)(64 * Game.SCALE),(int)(64 * Game.SCALE), null);

        // Text
        String gameOverString = "GAME OVER";

        // Getting size of text & setting position & printing
        FontMetrics fm = g.getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(gameOverString, (Game.GAME_WIDTH - fm.stringWidth(gameOverString)) / 2, (Game.GAME_HEIGHT - fm.getHeight()) / 2 - (int)(75 * Game.SCALE));

        drawButtons(g);
    }

    private void drawButtons(Graphics g) {
        tryAgainButton.setBounds(Game.GAME_WIDTH/2 - (int)(150 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(60 * Game.SCALE),(int)(150 * Game.SCALE),(int)(20 * Game.SCALE));
        tryAgainButton.printComponents(g);
        backToMenuButton.setBounds(Game.GAME_WIDTH/2 + (int)(10 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(60 * Game.SCALE),(int)(100 * Game.SCALE),(int)(20 * Game.SCALE));
        backToMenuButton.printComponents(g);
    }

    private void hideButtons() {
        tryAgainButton.setVisible(false);
        backToMenuButton.setVisible(false);
    }

    private void showButtons() {
        tryAgainButton.setVisible(true);
        backToMenuButton.setVisible(true);
    }

    private void createButtons() {
        tryAgainButton = new JButton("TRY AGAIN");
        backToMenuButton = new JButton("MENU");

        tryAgainButton.setForeground(Color.WHITE);
        tryAgainButton.setFont(font1);
        tryAgainButton.setOpaque(false);
        tryAgainButton.setContentAreaFilled(false);
        tryAgainButton.setBorderPainted(false);

        backToMenuButton.setForeground(Color.WHITE);
        backToMenuButton.setFont(font1);
        backToMenuButton.setOpaque(false);
        backToMenuButton.setContentAreaFilled(false);
        backToMenuButton.setBorderPainted(false);

        hideButtons();
    }

    private void tryAgainButtonAction() {
        tryAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gamestate.state = Gamestate.PLAYING;
                playing.restartGame();
                playing.pauseButton.setVisible(true);
                hideButtons();
            }
        });
    }

    private void backToMenuButtonAction() {
        backToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gamestate.state = Gamestate.MENU;
                playing.restartGame();
                hideButtons();
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
