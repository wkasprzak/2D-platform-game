package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import main.Game;
import utils.Import;

public class Story implements StateMethods {
	
	private Game game;
	public static JButton exitButton;
	private Font font = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));
	
	private BufferedImage backgroundImage;
	
	public Story(Game game) {
		this.game = game;
		backgroundImage = Import.ImportData(Import.MENU_BACKGROUND_IMAGE);
		createButton();
		
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
		g.drawImage(backgroundImage, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
		drawButtons(g);
	}
	
	private void createButton() {
		exitButton = new JButton("EXIT");
		exitButton.setForeground(Color.WHITE);
		exitButton.setFont(font);
		exitButton.setOpaque(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);	
		exitButton.setVisible(false);
	}
	
	private void drawButtons(Graphics g) {
		exitButton.setBounds(Game.GAME_WIDTH/2 - (int)(55 * Game.SCALE), Game.GAME_HEIGHT / 2 + (int)(60 * Game.SCALE),(int)(110 * Game.SCALE),(int)(15 * Game.SCALE));
		exitButton.printComponents(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
