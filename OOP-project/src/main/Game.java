package main;

import java.awt.Graphics;

import entities.Player;
import states.Gamestate;
import states.Playing;

public class Game implements Runnable {
	
	// Game thread
	private Thread gameThread;
	private final int FPS = 60;
	private final int UPS = 100;
	
	// Game states
	private Playing playing;
	// TO DO: ADD MENU & OPTION & PAUSE STATE
	
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
		playing = new Playing(this);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void draw(Graphics g) {
		switch(Gamestate.state) {
		case MENU:
			break;
//		case OPTIONS:
//			break
		case PLAYING:
			playing.draw(g);
			break;
		}
	}
	
	public void update() {
		switch(Gamestate.state) {
		case MENU:
			break;
//		case OPTIONS:
//			break
		case PLAYING:
			playing.update();
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
	
}
