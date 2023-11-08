package main;

import java.awt.Graphics;

import entities.Player;
import states.Gamestate;
import states.Help;
import states.Menu;
import states.Options;
import states.Playing;
import states.Story;

public class Game implements Runnable {
	
	// Game thread
	private Thread gameThread;
	private final int FPS = 120;
	public static int UPS = 200;
	
	// Game states
	private Playing playing;
	private Menu menu;
	private Help help;
	private Story story;
	private Options options;
	// TO DO: ADD PAUSE STATE
	
	// Game window
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	
	// Dimensions of the game window
	public static int DEFAULT_TILES_SIZE = 16;
	public static float SCALE = 2.0f;
	public static int TILES_SIZE = (int)(SCALE * DEFAULT_TILES_SIZE);
	public static int NUMBER_OF_TILES_IN_HEIGHT = 15;
	public static int NUMBER_OF_TILES_IN_WIDTH = 25;
	public static int GAME_HEIGHT = TILES_SIZE * NUMBER_OF_TILES_IN_HEIGHT;
	public static int GAME_WIDTH = TILES_SIZE * NUMBER_OF_TILES_IN_WIDTH;
	
	public Game() {	
		initStates();
		initWindow();
		startGameLoop();
	}
	
	private void initWindow() {
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.setFocusable(true);
		gamePanel.requestFocus();	
	}

	private void initStates() {
		menu = new Menu(this);
		playing = new Playing(this);
		help = new Help(this);
		story = new Story(this);
		options = new Options(this);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void draw(Graphics g) {
		switch(Gamestate.state) {
		case MENU:
			menu.draw(g);
			break;
		case OPTIONS:
			options.draw(g);
			break;
		case HELP:
			help.draw(g);
			break;
		case STORY:
			story.draw(g);
			break;
		case PLAYING:
			playing.draw(g);
			break;
		default:
			System.exit(0);
			break;
		}
	}
	
	public void update() {
		switch(Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case OPTIONS:
			options.update();
			break;
		case HELP:
			help.update();
			break;
		case STORY:
			story.update();
			break;
		case PLAYING:
			playing.update();
			break;
		default:
			break;
		}
	}
	
	public void run() {
		
		// How long each frame will last
		double timePerFrame = 1000000000.0 / FPS;
		double timePerUpdate = 1000000000.0 / UPS;

		long previousTime = System.nanoTime();
		long lastCheck = System.currentTimeMillis();
		
		int frames = 0;
		int updates = 0;
		
		double deltaU = 0;
		double deltaF = 0;

		while (true) {
			long currentTime = System.nanoTime();

			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;

			if (deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}

			if (deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public Playing getPlaying() {
		return playing;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
}
