package states;

import main.Game;
import utils.Import;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class LevelCompleted implements StateMethods {

    private Playing playing;
    public static JButton nextLevelButton, backToMenuButton;
    private BufferedImage backgroundImage;

    // Font
    private Font font = new Font("STENCIL", Font.BOLD, (int)(20 * Game.SCALE));
    private Font font1 = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));

    public LevelCompleted(Playing playing) {
        this.playing = playing;
        backgroundImage = Import.importImage(Import.MENU_BACKGROUND_IMAGE);
        createButtons();
    }

    @Override
    public void update() {
        showButtons();
        nextLevelButtonAction();
        backToMenuButtonAction();
    }

    @Override
    public void draw(Graphics g) {

        // Darker background
        g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        // Text
        String gameOverString = "LEVEL COMPLETED";

        // Getting size of text & setting position & printing
        FontMetrics fm = g.getFontMetrics(font);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString(gameOverString, (Game.GAME_WIDTH - fm.stringWidth(gameOverString)) / 2, (Game.GAME_HEIGHT - fm.getHeight()) / 2 - (int)(75 * Game.SCALE));

        drawButtons(g);
    }

    // Buttons
    private void drawButtons(Graphics g) {
        nextLevelButton.setBounds(Game.GAME_WIDTH/2 - (int)(150 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(60 * Game.SCALE),(int)(150 * Game.SCALE),(int)(20 * Game.SCALE));
        nextLevelButton.printComponents(g);
        backToMenuButton.setBounds(Game.GAME_WIDTH/2 + (int)(10 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(60 * Game.SCALE),(int)(100 * Game.SCALE),(int)(20 * Game.SCALE));
        backToMenuButton.printComponents(g);
    }

    private void hideButtons() {
        nextLevelButton.setVisible(false);
        backToMenuButton.setVisible(false);
    }

    private void showButtons() {
        nextLevelButton.setVisible(true);
        backToMenuButton.setVisible(true);
    }

    private void createButtons() {
        nextLevelButton = new JButton("NEXT LEVEL");
        backToMenuButton = new JButton("MENU");

        nextLevelButton.setForeground(Color.WHITE);
        nextLevelButton.setFont(font1);
        nextLevelButton.setOpaque(false);
        nextLevelButton.setContentAreaFilled(false);
        nextLevelButton.setBorderPainted(false);

        backToMenuButton.setForeground(Color.WHITE);
        backToMenuButton.setFont(font1);
        backToMenuButton.setOpaque(false);
        backToMenuButton.setContentAreaFilled(false);
        backToMenuButton.setBorderPainted(false);

        hideButtons();
    }

    private void nextLevelButtonAction() {
        nextLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playing.loadNextLevel();
                Gamestate.state = Gamestate.PLAYING;
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

    // Keyboard
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
