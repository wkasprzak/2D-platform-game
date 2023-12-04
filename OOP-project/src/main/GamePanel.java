package main;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import input.KeyboardInput;
import input.MouseInput;
import states.*;

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
		add(Menu.helpButton);
		add(Menu.storyButton);
		add(Help.exitButton);
		add(Story.exitButton);
		add(Options.exitButton);
		add(Options.soundButton);
		add(Options.musicButton);
		add(Playing.pauseButton);
		add(Pause.returnToMenuButton);
		add(Pause.restartButton);
		add(Pause.goBackButton);
		add(Pause.musicButton);
		add(Pause.soundButton);
		add(GameOver.tryAgainButton);
		add(GameOver.backToMenuButton);
		add(LevelCompleted.nextLevelButton);
		add(LevelCompleted.backToMenuButton);
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
