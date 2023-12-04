package states;

import main.Game;
import utils.Import;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Pause implements StateMethods {

    private Game game;
    private Playing playing;
    public static JButton returnToMenuButton, restartButton, goBackButton, musicButton, soundButton;
    private Font font = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));
    private BufferedImage backgroundImage;

    public Pause(Game game, Playing playing) {
        this.game = game;
        this.playing = playing;
        backgroundImage = Import.importImage(Import.MENU_BACKGROUND_IMAGE);
        createButtons();
    }

    @Override
    public void update() {
        showButtons();
        musicButtonAction();
        soundButtonAction();
        goBackButtonAction();
        returnToMenuButtonAction();
        restartAction();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.setColor(new Color(0,0,0,150));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        String title = "GAME PAUSED";
        FontMetrics fm = g.getFontMetrics(font);
        int textWidth = fm.stringWidth(title);
        int textHeight = fm.getHeight();
        int x = (Game.GAME_WIDTH - textWidth) / 2;
        int y = (Game.GAME_HEIGHT - textHeight) / 2 - (int)(75 * Game.SCALE);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(title, x, y);

        drawButtons(g);
    }

    // Buttons
    private void drawButtons(Graphics g) {
        musicButton.setBounds(Game.GAME_WIDTH/2 - (int)(50 * Game.SCALE), Game.GAME_HEIGHT / 2 - (int)(20 * Game.SCALE),(int)(100 * Game.SCALE),(int)(15 * Game.SCALE));
        musicButton.printComponents(g);
        soundButton.setBounds(Game.GAME_WIDTH/2 - (int)(50 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(5 * Game.SCALE), (int)(100 * Game.SCALE),(int)(15 * Game.SCALE));
        soundButton.printComponents(g);

        goBackButton.setBounds(Game.GAME_WIDTH/2 - (int)(120 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(60 * Game.SCALE),(int)(100 * Game.SCALE),(int)(15 * Game.SCALE));
        goBackButton.printComponents(g);
        returnToMenuButton.setBounds(Game.GAME_WIDTH/2 - (int)(30 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(60 * Game.SCALE),(int)(60 * Game.SCALE),(int)(15 * Game.SCALE));
        returnToMenuButton.printComponents(g);
        restartButton.setBounds(Game.GAME_WIDTH/2 + (int)(25 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(60 * Game.SCALE),(int)(100 * Game.SCALE),(int)(15 * Game.SCALE));
        restartButton.printComponents(g);
    }

    private void hideButtons() {
        returnToMenuButton.setVisible(false);
        restartButton.setVisible(false);
        goBackButton.setVisible(false);
        musicButton.setVisible(false);
        soundButton.setVisible(false);
    }

    private void showButtons() {
        returnToMenuButton.setVisible(true);
        restartButton.setVisible(true);
        goBackButton.setVisible(true);
        musicButton.setVisible(true);
        soundButton.setVisible(true);
    }

    private void createButtons() {
        returnToMenuButton = new JButton("MENU");
        restartButton = new JButton("RESTART");
        goBackButton = new JButton("RETURN");
        musicButton = new JButton("MUSIC");
        soundButton = new JButton("SOUND");

        returnToMenuButton.setForeground(Color.WHITE);
        returnToMenuButton.setFont(font);
        returnToMenuButton.setOpaque(false);
        returnToMenuButton.setContentAreaFilled(false);
        returnToMenuButton.setBorderPainted(false);

        restartButton.setForeground(Color.WHITE);
        restartButton.setFont(font);
        restartButton.setOpaque(false);
        restartButton.setContentAreaFilled(false);
        restartButton.setBorderPainted(false);

        goBackButton.setForeground(Color.WHITE);
        goBackButton.setFont(font);
        goBackButton.setOpaque(false);
        goBackButton.setContentAreaFilled(false);
        goBackButton.setBorderPainted(false);

        musicButton.setForeground(Color.WHITE);
        musicButton.setFont(font);
        musicButton.setOpaque(false);
        musicButton.setContentAreaFilled(false);
        musicButton.setBorderPainted(false);

        soundButton.setForeground(Color.WHITE);
        soundButton.setFont(font);
        soundButton.setOpaque(false);
        soundButton.setContentAreaFilled(false);
        soundButton.setBorderPainted(false);

        hideButtons();
    }

    private void restartAction() {
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gamestate.state = Gamestate.PLAYING;
                playing.restartGame();
                playing.pauseButton.setVisible(true);
                hideButtons();
            }
        });
    }

    private void returnToMenuButtonAction() {
        returnToMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gamestate.state = Gamestate.MENU;
                playing.restartGame();
                hideButtons();
            }
        });
    }

    private void goBackButtonAction() {
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Gamestate.state = Gamestate.PLAYING;
                playing.pauseButton.setVisible(true);
                playing.paused = false;
                hideButtons();
            }
        });
    }

    private void soundButtonAction() {
        soundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Game.isSound()) {
                    Game.setSound(false);
                    soundButton.setForeground(Color.GRAY);
                } else {
                    Game.setSound(true);
                    soundButton.setForeground(Color.WHITE);
                }
                soundButton.requestFocusInWindow(null);
                soundButton.repaint(); // To make it faster
            }
        });
    }

    private void musicButtonAction() {
        musicButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Game.isMusic()) {
                    Game.setMusic(false);
                    musicButton.setForeground(Color.GRAY);
                } else {
                    Game.setMusic(true);
                    musicButton.setForeground(Color.WHITE);
                }
                musicButton.requestFocusInWindow(null);
                musicButton.repaint(); // To make it faster
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
