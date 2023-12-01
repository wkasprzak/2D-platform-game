package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import main.Game;
import utils.Import;
import states.*;
import main.GameWindow.*;

public class Menu implements StateMethods {

	private Game game;
	private BufferedImage backgroundImage;
	public static JButton playButton, optionsButton, storyButton, helpButton, exitButton;

	// Font
	private Font font = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));

	public Menu(Game game) {
		this.game = game;
		backgroundImage = Import.importImage(Import.MENU_BACKGROUND_IMAGE);
		createButtons();
	}

	@Override
	public void update() {
		showButtons();
		playButtonAction();
		storyButtonAction();
		optionsButtonAction();
		exitButtonAction();
		helpButtonAction();
	}

	@Override
	public void draw(Graphics g) {

		// Menu text
		String gameTitle1 = "Catticus:";
		String gameTitle2 = "The Adventures";
		String gameTitle3 = "of a Hungry Cat";

        // Getting size of text & setting position
        FontMetrics fm = g.getFontMetrics(font);
        int textWidth1 = fm.stringWidth(gameTitle1);
        int textWidth2 = fm.stringWidth(gameTitle2);
        int textWidth3 = fm.stringWidth(gameTitle3);
        int textHeight = fm.getHeight();
		int x1 = (Game.GAME_WIDTH - textWidth1) / 2;
		int x2 = (Game.GAME_WIDTH - textWidth2) / 2;
		int x3 = (Game.GAME_WIDTH - textWidth3) / 2;
		int y = (Game.GAME_HEIGHT - textHeight) / 2 - (int)(75 * Game.SCALE);

        // Drawing background
		g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.setColor(new Color(0,0,0,150));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

		// Drawing text & buttons
		g.setColor(Color.WHITE);
		g.setFont(font);
        g.drawString(gameTitle1, x1, y);
        g.drawString(gameTitle2, x2, y + (int)(15 * Game.SCALE));
        g.drawString(gameTitle3, x3, y + (int)(30 * Game.SCALE));
        drawButtons(g);
	}
	
	private void drawButtons(Graphics g) {
		playButton.setBounds(Game.GAME_WIDTH/2 - (int)(55 * Game.SCALE), Game.GAME_HEIGHT / 2,(int)(110 * Game.SCALE),(int)(15 * Game.SCALE));
		playButton.printComponents(g);
		storyButton.setBounds(Game.GAME_WIDTH/2 - (int)(55 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(20 * Game.SCALE),(int)(110 * Game.SCALE),(int)(15 * Game.SCALE));
		storyButton.printComponents(g);
		optionsButton.setBounds(Game.GAME_WIDTH/2 - (int)(55 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(40 * Game.SCALE),(int)(110 * Game.SCALE),(int)(15 * Game.SCALE));
		optionsButton.printComponents(g);
		exitButton.setBounds(Game.GAME_WIDTH/2 - (int)(55 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(60 * Game.SCALE),(int)(110 * Game.SCALE),(int)(15 * Game.SCALE));
		exitButton.printComponents(g);
		helpButton.setBounds(Game.GAME_WIDTH - (int)(70 * Game.SCALE), (int)(5 * Game.SCALE), (int)(110 * Game.SCALE),(int)(15 * Game.SCALE));
		helpButton.printComponents(g);
	}

	private void hideButtons() {
    	playButton.setVisible(false);
    	optionsButton.setVisible(false);
    	exitButton.setVisible(false);
    	storyButton.setVisible(false);
    	helpButton.setVisible(false);
	}
	
	private void showButtons() {
    	playButton.setVisible(true);
    	optionsButton.setVisible(true);
    	exitButton.setVisible(true);
    	storyButton.setVisible(true);
    	helpButton.setVisible(true);
	}

	private void createButtons() {
		playButton = new JButton("PLAY");
		optionsButton = new JButton("OPTIONS");
		storyButton = new JButton("STORY");
		exitButton = new JButton("EXIT");
		helpButton = new JButton("?");

		playButton.setForeground(Color.WHITE);
		playButton.setFont(font);
		playButton.setOpaque(false);
		playButton.setContentAreaFilled(false);
		playButton.setBorderPainted(false);

		optionsButton.setForeground(Color.WHITE);
		optionsButton.setFont(font);
		optionsButton.setOpaque(false);
		optionsButton.setContentAreaFilled(false);
		optionsButton.setBorderPainted(false);

		storyButton.setForeground(Color.WHITE);
		storyButton.setFont(font);
		storyButton.setOpaque(false);
		storyButton.setContentAreaFilled(false);
		storyButton.setBorderPainted(false);

		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(font);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);

		helpButton.setForeground(Color.WHITE);
		helpButton.setFont(font);
		helpButton.setOpaque(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setBorderPainted(false);
	}

	private void playButtonAction() {
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gamestate.state = Gamestate.PLAYING;
				Playing.pauseButton.setVisible(true);
				hideButtons();
			}
		});
	}

	private void storyButtonAction() {
		storyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gamestate.state = Gamestate.STORY;
				Story.exitButton.setVisible(true);
				hideButtons();
			}
		});
	}

	private void optionsButtonAction() {
		optionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gamestate.state = Gamestate.OPTIONS;
				Options.exitButton.setVisible(true);
				hideButtons();
			}
		});

	}

	private void exitButtonAction() {
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void helpButtonAction() {
		helpButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gamestate.state = Gamestate.HELP;
				Help.exitButton.setVisible(true);
				hideButtons();
			}
		});
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}
