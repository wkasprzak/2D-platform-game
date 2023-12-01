package states;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import entities.Enemies;
import entities.Player;
import levels.LevelHandler;
import main.Game;
import utils.Import;

import javax.swing.*;

public class Playing implements StateMethods {

	private Game game;
	private Player player;
	private Enemies enemies;
	private LevelHandler levelHandler;
	private Pause pause;
	private GameOver gameOverScreen;

	// Pause button
	private final Font font = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));
	public boolean paused = false;
	public static JButton pauseButton;

	// Levels longer than window width
	private int offset;
	private final int leftBorder = (int)(0.2 * Game.GAME_WIDTH);
	private final int rightBorder = (int)(0.8 * Game.GAME_WIDTH);
	private int levelWidth = 50;
	private final int maxTilesOffset = levelWidth - Game.NUMBER_OF_TILES_IN_WIDTH;
	private final int maxOffset = maxTilesOffset * Game.TILES_SIZE;

	// Game background
	private final String[] backgroundTypes = {Import.BACKGROUND,Import.BIG_CLOUDS,Import.SMALL_CLOUDS,Import.MOUNTAINS,Import.PLAINS,Import.GRASS};
	private final BufferedImage[] levelBackground = new BufferedImage[6];
	private final int[] assetWidth = new int[6];

	// TO DO
	//private boolean levelCompleted = false;
	private boolean gameOver;
	private boolean playerDying;

	public Playing(Game game) {
		this.game = game;
		initClasses();
		createPauseButton();
		importBackground();
	}

	private void importBackground() {
		for (int i = 0; i < backgroundTypes.length; i++) {
			levelBackground[i] = Import.importImage(backgroundTypes[i]);
			assetWidth[i] = levelBackground[i].getWidth();
		}
	}

	private void drawBackground(Graphics g, int offset) {
		for(int i = 0; i < Game.GAME_WIDTH / assetWidth[0] + 1; i++) {
			for(int j = 0; j < 6; j++) {
				g.drawImage(levelBackground[j], i * Game.GAME_WIDTH - offset,0, Game.GAME_WIDTH, Game.GAME_HEIGHT,null);
			}
		}
	}

	public void restartGame() {
		gameOver = false;
		paused = false;
		playerDying = false;
		player.resetAll();
		enemies.resetEnemies();
	}

	private void createPauseButton() {
		pauseButton = new JButton("| |");
		pauseButton.setForeground(Color.WHITE);
		pauseButton.setFont(font);
		pauseButton.setOpaque(false);
		pauseButton.setContentAreaFilled(false);
		pauseButton.setBorderPainted(false);
		pauseButton.setVisible(false);
	}

	public void checkIfEnemyWasHurt(Rectangle2D.Float attackBox) {
		enemies.checkIfHit(attackBox);
	}

	public void loadNextLevel() {
		restartGame();
		levelHandler.loadNextLevel();
		levelWidth = levelHandler.getCurrentLevel().getLevelData()[0].length;
	}
	
	private void initClasses() {
		levelHandler = new LevelHandler(game);
		enemies = new Enemies(this);
		pause = new Pause(game, this);
		player = new Player(50, 50, (int) (32 * Game.SCALE), (int) (32 * Game.SCALE), this);
		player.loadLevelData(levelHandler.getCurrentLevel().getLevelData());
		gameOverScreen = new GameOver(this);
	}

	@Override
	public void update() {
		if(gameOver) {
			//gameOverScreen.update();
		} else if(playerDying) {
			player.update();
		} else if (!paused) {
			player.update();
			enemies.update(levelHandler.getCurrentLevel().getLevelData(), player);
			pauseButtonAction();
			ifPosCloseToWindowEnd();
		}
	}

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

	@Override
	public void draw(Graphics g) {
		drawBackground(g, offset);
		levelHandler.draw(g, offset);
		enemies.draw(g, offset);
		player.draw(g, offset);
		pauseButton.setBounds(Game.GAME_WIDTH - (int)(70 * Game.SCALE), (int)(5 * Game.SCALE), (int)(110 * Game.SCALE),(int)(15 * Game.SCALE));
		pauseButton.printComponents(g);
		if(paused) {
			pause.draw(g);
		} else if(gameOver) {
			pauseButton.setVisible(false);
			Gamestate.state = Gamestate.GAMEOVER;
		}
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

	@Override
	public void keyPressed(KeyEvent e) {
		if(gameOver)
			gameOverScreen.keyPressed(e);
		else
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
		if(!gameOver)
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

	public Player getPlayer() {
		return player;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

    public void setDying(boolean playerDying) {
		this.playerDying = playerDying;
    }
}
