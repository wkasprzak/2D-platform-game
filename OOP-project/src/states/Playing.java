package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelHandler;
import main.Game;

import static utils.Import.importCSVData;

public class Playing implements StateMethods {

	private Game game;
	private Player player;
	private LevelHandler levelHandler;
	
	private boolean levelCompleted = false;
	
	public Playing(Game game) {
		this.game = game;
		initClasses();
	}
	
	public void loadNextLevel() {
		resetAll();
		levelHandler.loadNextLevel();
		//player.setSpawn(levelHandler.getCurrentLevel().getPlayerSpawn());
	}
	
	private void initClasses() {
		levelHandler = new LevelHandler(game);
		player = new Player(200, 200, (int) (32 * Game.SCALE), (int) (32 * Game.SCALE));
		player.LoadLevelData(levelHandler.getCurrentLevel().getLevelData());
	}

	@Override
	public void update() {
		player.update();
		levelHandler.update();
	}

	@Override
	public void draw(Graphics g) {
		levelHandler.draw(g);
		player.draw(g);
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
	
	public void resetAll() {
		levelCompleted = false;
	}

	public Player getPlayer() {
		return player;
	}

}
