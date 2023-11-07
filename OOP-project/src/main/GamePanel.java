package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;

import input.KeyboardInput;
import input.MouseInput;
import states.Menu;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;


public class GamePanel extends JPanel {

	private MouseInput mouse;
	private Game game;
	
	public GamePanel(Game game) {
		this.game = game;
		initInput();
		setSize();
		addButtons();
	}

	private void addButtons() {
		add(Menu.playButton);
		add(Menu.optionsButton);
		add(Menu.exitButton);
		add(Menu.howToPlayButton);
		add(Menu.storyButton);
	}

	private void setSize() {
		Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);
		setPreferredSize(size);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.draw(g);
	}
	
	private void initInput() {
		mouse = new MouseInput(this); // Create an instance of the MouseInput class (to operate on one instance)
		addKeyListener(new KeyboardInput(this));
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public Game getGame() {
		return game;
	}
	
}
