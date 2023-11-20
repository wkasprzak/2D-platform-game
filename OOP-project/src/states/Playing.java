package states;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import entities.Player;
import levels.LevelHandler;
import main.Game;
import utils.Import;

import javax.swing.*;

public class Playing implements StateMethods {

	private Game game;
	private Player player;
	private LevelHandler levelHandler;
	private Pause pause;

	// Pause button
	private final Font font = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));
	public static boolean paused = false;
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

	public Playing(Game game) {
		this.game = game;
		initClasses();
		createPauseButton();
		importBackground();
	}

	private void importBackground() {
		for (int i = 0; i < backgroundTypes.length; i++) {
			levelBackground[i] = Import.ImportData(backgroundTypes[i]);
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

	public static void restartGame() {
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

	public void loadNextLevel() {
		resetAll();
		levelHandler.loadNextLevel();
		levelWidth = levelHandler.getCurrentLevel().getLevelData()[0].length;
	}
	
	private void initClasses() {
		levelHandler = new LevelHandler(game);
		pause = new Pause(game);
		player = new Player(50, 50, (int) (32 * Game.SCALE), (int) (32 * Game.SCALE));
		player.loadLevelData(levelHandler.getCurrentLevel().getLevelData());
	}

	@Override
	public void update() {
		if(!paused) {
			player.update();
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
		player.draw(g, offset);
		pauseButton.setBounds(Game.GAME_WIDTH - (int)(70 * Game.SCALE), (int)(5 * Game.SCALE), (int)(110 * Game.SCALE),(int)(15 * Game.SCALE));
		pauseButton.printComponents(g);
		if(paused) {
			pause.draw(g);
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
	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void resetAll() {
		//levelCompleted = false;
	}

	public Player getPlayer() {
		return player;
	}

}
