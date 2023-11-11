package states;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelHandler;
import main.Game;

import javax.swing.*;

public class Playing implements StateMethods {

	private Game game;
	private Player player;
	private LevelHandler levelHandler;
	private Pause pause;
	
	//private boolean levelCompleted = false;
	private Font font = new Font("STENCIL", Font.BOLD, (int)(15 * Game.SCALE));
	public static boolean paused = false;
	public static JButton pauseButton;
	
	public Playing(Game game) {
		this.game = game;
		initClasses();
		createPauseButton();
	}

	public static void restartGame() {
	}

	private void createPauseButton() {
		pauseButton = new JButton("| |");
		pauseButton.setForeground(Color.BLACK);
		pauseButton.setFont(font);
		pauseButton.setOpaque(false);
		pauseButton.setContentAreaFilled(false);
		pauseButton.setBorderPainted(false);
		pauseButton.setVisible(false);
	}

	public void loadNextLevel() {
		resetAll();
		levelHandler.loadNextLevel();
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
		}
	}

	@Override
	public void draw(Graphics g) {
		levelHandler.draw(g);
		player.draw(g);
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
