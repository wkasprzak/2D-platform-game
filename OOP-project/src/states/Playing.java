package states;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import entities.Enemies;
import entities.Player;
import levels.LevelHandler;
import main.Game;
import objects.ObjectHandler;
import utils.Import;

import javax.swing.*;

public class Playing implements StateMethods {

	private Game game;
	private Player player;
	private Enemies enemies;
	private LevelHandler levelHandler;
	private ObjectHandler objectHandler;
	private LevelCompleted levelCompletedOverlay;
	private Pause pause;

	public boolean ok;

	// Pause button
	private final Font font = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));
	public boolean paused;
	public static JButton pauseButton;

	// Levels longer than window width
	private int offset;
	private final int leftBorder = (int)(0.2 * Game.GAME_WIDTH);
	private final int rightBorder = (int)(0.8 * Game.GAME_WIDTH);
	private int maxOffset;

	// Game background
	private final String[] backgroundTypes = {Import.BACKGROUND,Import.BIG_CLOUDS,Import.SMALL_CLOUDS,Import.MOUNTAINS,Import.PLAINS,Import.GRASS};
	private final BufferedImage[] levelBackground = new BufferedImage[6];
	private final int[] assetWidth = new int[6];

	private boolean levelCompleted;
	private boolean gameOver;
	private boolean playerDying;
	private boolean win;

	public Playing(Game game) {
		this.game = game;
		initClasses();
		createPauseButton();
		importBackground();

		checkLevelOffset();
		loadFirstLevel();
	}

	// Basics
	private void initClasses() {
		levelHandler = new LevelHandler(game);
		enemies = new Enemies(this);
		pause = new Pause(game, this);
		objectHandler = new ObjectHandler(this);
		player = new Player(50, 50, (int) (32 * Game.SCALE), (int) (32 * Game.SCALE), this);
		player.setSpawnPoint(levelHandler.getCurrentLevel().getSpawn());
		player.loadLevelData(levelHandler.getCurrentLevel().getLevelData());
		levelCompletedOverlay = new LevelCompleted(this);
	}

	@Override
	public void update() {
		if(levelCompleted) {
			enemies.resetEnemies();
		}
		else if(playerDying) {
			player.update();
		} else if (!gameOver) {
			player.update();
			objectHandler.update();
			enemies.update(levelHandler.getCurrentLevel().getLevelData(), player);
			pauseButtonAction();
			ifPosCloseToWindowEnd();
		}
	}

	@Override
	public void draw(Graphics g) {
		drawBackground(g, offset);

		levelHandler.draw(g, offset);
		enemies.draw(g, offset);
		player.draw(g, offset);
		objectHandler.draw(g, offset);

		pauseButton.setBounds(Game.GAME_WIDTH - (int)(70 * Game.SCALE), (int)(5 * Game.SCALE), (int)(110 * Game.SCALE),(int)(15 * Game.SCALE));
		pauseButton.printComponents(g);

		if(paused) {
			pause.draw(g);
		} else if(gameOver) {
			pauseButton.setVisible(false);
			Gamestate.state = Gamestate.GAMEOVER;
		} else if(levelCompleted) {
			pauseButton.setVisible(false);
			levelCompletedOverlay.draw(g);
		}
	}

	// Level creation
	public void loadNextLevel() {
		restartGame();
		levelHandler.loadNextLevel();
		player.setSpawnPoint(levelHandler.getCurrentLevel().getSpawn());
	}

	private void loadFirstLevel() {
		enemies.addEnemies(levelHandler.getCurrentLevel());
		objectHandler.loadObjects(levelHandler.getCurrentLevel());
	}

	private void checkLevelOffset() {
		maxOffset = levelHandler.getCurrentLevel().getLevelOffset();
	}

	public void setLevelOffset(int levelOffset) {
		this.maxOffset = levelOffset;
	}

	// Background
	private void importBackground() {
		for (int i = 0; i < backgroundTypes.length; i++) {
			levelBackground[i] = Import.importImage(backgroundTypes[i]);
			assetWidth[i] = levelBackground[i].getWidth();
		}
	}

	private void drawBackground(Graphics g, int offset) {
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 6; j++) {
				g.drawImage(levelBackground[j], i * Game.GAME_WIDTH - offset,0, Game.GAME_WIDTH, Game.GAME_HEIGHT,null);
			}
		}
	}

	// Environment
	public void checkIfEnemyWasHurt(Rectangle2D.Float attackBox) {
		enemies.checkIfHit(attackBox);
	}

	public void checkFishTouched(Rectangle2D.Float hitbox) {
		objectHandler.checkObjectTouched(hitbox);
	}

	public void checkSpikesTouched(Player player) {
		objectHandler.checkSpikesTouched(player);
	}

	// Position
	private void ifPosCloseToWindowEnd() {
		int playerX = (int)player.getHitbox().x;
		int diff = playerX - offset;
		if(diff > rightBorder)
			offset += diff - rightBorder;
		else if(diff < leftBorder)
			offset += diff - leftBorder;

		if(offset > maxOffset)
			offset = maxOffset;
		else if(offset < 0)
			offset = 0;
	}

	// Buttons
	private void createPauseButton() {
		pauseButton = new JButton("| |");
		pauseButton.setForeground(Color.WHITE);
		pauseButton.setFont(font);
		pauseButton.setOpaque(false);
		pauseButton.setContentAreaFilled(false);
		pauseButton.setBorderPainted(false);
		pauseButton.setVisible(false);
	}

	private void pauseButtonAction() {
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paused = !paused;
				Gamestate.state = Gamestate.PAUSE;
				pauseButton.setVisible(false);
			}
		});
	}

	// Reset
	public void restartGame() {
		gameOver = false;
		paused = false;
		playerDying = false;
		levelCompleted = false;
		objectHandler.resetObjects();
		player.resetAll();
		enemies.resetEnemies();
	}

	// Keyboard
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			player.setLeft(true);
			break;
		case KeyEvent.VK_D:
			player.setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			player.setJump(true);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(!gameOver) {
			if (levelCompleted)
				levelCompletedOverlay.keyReleased(e);
			else
				switch (e.getKeyCode()) {
					case KeyEvent.VK_A:
						player.setLeft(false);
						break;
					case KeyEvent.VK_D:
						player.setRight(false);
						break;
					case KeyEvent.VK_SPACE:
						player.setJump(false);
						break;
				}
		}
	}

	// Getters & setters
	public Player getPlayer() {
		return player;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

    public void setDying(boolean playerDying) {
		this.playerDying = playerDying;
    }

	public Enemies getEnemies() {
		return enemies;
	}

	public void setLevelCompleted(boolean levelCompleted) {
		this.levelCompleted = levelCompleted;
	}

	public ObjectHandler getObjectHandler() {
		return objectHandler;
	}

	public LevelHandler getLevelHandler() {
		return levelHandler;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

}
