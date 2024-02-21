package states;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.*;

import main.Game;
import utils.Import;

public class Options implements StateMethods {

	private Game game;
	public static JButton exitButton;
	public static JToggleButton soundButton, musicButton;
	private Font font = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));
	
	private BufferedImage backgroundImage;
	
	public Options(Game game) {
		this.game = game;
		backgroundImage = Import.importImage(Import.MENU_BACKGROUND_IMAGE);
		createButtons();
	}

	@Override
	public void update() {
		showButtons();
		exitButtonAction();
		soundButtonAction();
		musicButtonAction();
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.setColor(new Color(0,0,0,150));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

		String title = "OPTIONS";
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
	private void hideButtons() {
		exitButton.setVisible(false);
		musicButton.setVisible(false);
		soundButton.setVisible(false);
	}

	private void showButtons() {

		if(!game.getAudio().isMusicOn())
			musicButton.setForeground(Color.WHITE);
		else
			musicButton.setForeground(Color.GRAY);
		if(!game.getAudio().isSfxOn())
			soundButton.setForeground(Color.WHITE);
		else
			soundButton.setForeground(Color.GRAY);

		exitButton.setVisible(true);
		musicButton.setVisible(true);
		soundButton.setVisible(true);
	}
	
	private void createButtons() {
		exitButton = new JButton("EXIT");
		musicButton = new JToggleButton("MUSIC");
		soundButton = new JToggleButton("SOUND");

		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(font);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);	
		exitButton.setVisible(false);

		musicButton.setFont(font);
		musicButton.setOpaque(false);
		musicButton.setContentAreaFilled(false);
		musicButton.setBorderPainted(false);

		soundButton.setFont(font);
		soundButton.setOpaque(false);
		soundButton.setContentAreaFilled(false);
		soundButton.setBorderPainted(false);

		hideButtons();
	}
	
	private void drawButtons(Graphics g) {
		musicButton.setBounds(Game.GAME_WIDTH/2 - (int)(50 * Game.SCALE), Game.GAME_HEIGHT / 2,(int)(100 * Game.SCALE),(int)(15 * Game.SCALE));
		musicButton.printComponents(g);
		soundButton.setBounds(Game.GAME_WIDTH/2 - (int)(50 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(20 * Game.SCALE), (int)(100 * Game.SCALE),(int)(15 * Game.SCALE));
		soundButton.printComponents(g);
		exitButton.setBounds(Game.GAME_WIDTH/2 - (int)(55 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(60 * Game.SCALE),(int)(110 * Game.SCALE),(int)(15 * Game.SCALE));
		exitButton.printComponents(g);
	}

	private void exitButtonAction() {
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Gamestate.state = Gamestate.MENU;
				hideButtons();
			}
		});
	}

	private void soundButtonAction() {
		soundButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.getAudio().playSFX(game.getAudio().MEOW_SOUND1);
				if (soundButton.isSelected()) {
					game.getAudio().muteSFX(true);
					soundButton.setForeground(Color.GRAY);
				} else {
					game.getAudio().muteSFX(false);
					soundButton.setForeground(Color.WHITE);
				}
			}
		});
	}

	private void musicButtonAction() {
		musicButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.getAudio().playSFX(game.getAudio().MEOW_SOUND1);
				if (musicButton.isSelected()) {
					game.getAudio().muteMusic(true);
					musicButton.setForeground(Color.GRAY);
				} else {
					game.getAudio().muteMusic(false);
					musicButton.setForeground(Color.WHITE);
				}
			}
		});
	}

	// Keyboard
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
