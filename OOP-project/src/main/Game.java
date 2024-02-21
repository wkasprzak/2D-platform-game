package main;

import java.awt.Graphics;

import audio.Audio;
import states.*;

public class Game implements Runnable {
	
	// Game thread
	private Thread gameThread;
	private final int FPS = 120;
	public final int UPS = 200;
	
	// Game states
	private Playing playing;
	private Menu menu;
	private Help help;
	private Story story;
	private Options options;
	private Pause pause;
	private GameOver gameOver;
	private WinState winState;
	private Audio audio;
	
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

	// Basics
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
		gameOver = new GameOver(this, playing);
		pause = new Pause(this, playing);
		winState = new WinState(this);
		audio = new Audio();
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
		case PAUSE:
			pause.draw(g);
			break;
		case GAMEOVER:
			gameOver.draw(g);
			break;
		case WINSTATE:
			winState.draw(g);
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
		case PAUSE:
			pause.update();
			break;
		case GAMEOVER:
			gameOver.update();
			break;
		case WINSTATE:
			winState.update();
			break;
		default:
			break;
		}
	}

	// Game loop
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
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

	// Getters & setters
	public Playing getPlaying() {
		return playing;
	}
	
	public Menu getMenu() {
		return menu;
	}

	public Audio getAudio() {
		return audio;
	}

	public WinState getWinstate() {
		return winState;
	}

}
