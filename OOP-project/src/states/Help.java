package states;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import main.Game;
import utils.Import;

public class Help implements StateMethods {
	
	private Game game;
	private BufferedImage backgroundImage;
	public static JButton exitButton;

	// Fonts
	private Font font = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));
	private Font font1 = new Font("STENCIL", Font.PLAIN, (int)(10 * Game.SCALE));
	
	public Help(Game game) {
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
		// Text of instructions
		String title = "Instructions";
		String line1 = "How to play the game?";
		String line2 = "    -> Use 'A' and 'D' to move left/right";
		String line3 = "    -> Press 'Space' to jump";
		String line4 = "    -> Click the left mouse button to attack the enemy";

		// Size & positioning of title
		FontMetrics fm = g.getFontMetrics(font);
		int textWidth = fm.stringWidth(title);
		int textHeight = fm.getHeight();
		int x = (Game.GAME_WIDTH - textWidth) / 2;
		int y = (Game.GAME_HEIGHT - textHeight) / 2 - (int)(75 * Game.SCALE);

		// Drawing background, text & exit button
		g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		g.setColor(new Color(0,0,0,150));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

		// Setting font & color
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(title,x,y);
		g.setFont(font1);
		g.drawString(line1, (int)(50 * Game.SCALE), (int)(80 * Game.SCALE));
		g.drawString(line2, (int)(50 * Game.SCALE), (int)(100 * Game.SCALE));
		g.drawString(line3, (int)(50 * Game.SCALE), (int)(120 * Game.SCALE));
		g.drawString(line4, (int)(50 * Game.SCALE), (int)(140 * Game.SCALE));

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
