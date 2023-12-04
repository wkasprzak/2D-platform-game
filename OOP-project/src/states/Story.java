package states;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import main.Game;
import utils.Import;

public class Story implements StateMethods {
	
	private Game game;
	private BufferedImage backgroundImage;
	public static JButton exitButton;

	// Fonts
	private Font font = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));
	private Font font1 = new Font("STENCIL", Font.PLAIN, (int)(15 * Game.SCALE));

	public Story(Game game) {
		this.game = game;
		backgroundImage = Import.importImage(Import.MENU_BACKGROUND_IMAGE);
		createExitButton();
	}

	@Override
	public void update() {
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Gamestate.state = Gamestate.MENU;
            	exitButton.setVisible(false);
            }
        });
	}

	@Override
	public void draw(Graphics g) {
		String title = "Story";
		String line1 = "Assist Catticus on his";
		String line2 = "journey to collect food";
		String line3 = "guarded by sinister enemies.";

		// Getting size of text & setting position
		FontMetrics fm = g.getFontMetrics(font);
		int textWidth1 = fm.stringWidth(title);
		int textWidth2 = fm.stringWidth(line1);
		int textWidth3 = fm.stringWidth(line2);
		int textWidth4 = fm.stringWidth(line3);
		int textHeight = fm.getHeight();
		int x1 = (Game.GAME_WIDTH - textWidth1) / 2;
		int x2 = (Game.GAME_WIDTH - textWidth2) / 2;
		int x3 = (Game.GAME_WIDTH - textWidth3) / 2;
		int x4 = (Game.GAME_WIDTH - textWidth4) / 2;
		int y = (Game.GAME_HEIGHT - textHeight) / 2 - (int)(75 * Game.SCALE);

		// Drawing background, text & buttons
		g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.setColor(new Color(0,0,0,150));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

		// Setting font & color
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(title, x1, y);
		g.setFont(font1);
		g.drawString(line1, x2, y + (int)(50 * Game.SCALE));
		g.drawString(line2, x3, y + (int)(70 * Game.SCALE));
		g.drawString(line3, x4, y + (int)(90 * Game.SCALE));
		drawExitButton(g);
	}

	// Buttons
	private void createExitButton() {
		exitButton = new JButton("EXIT");
		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(font);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);	
		exitButton.setVisible(false);
	}
	
	private void drawExitButton(Graphics g) {
		exitButton.setBounds(Game.GAME_WIDTH/2 - (int)(55 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(60 * Game.SCALE),(int)(110 * Game.SCALE),(int)(15 * Game.SCALE));
		exitButton.printComponents(g);
	}

	// Keyboard
	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
