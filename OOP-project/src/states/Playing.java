package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import entities.Player;
import levels.LevelHandler;
import main.Game;

public class Playing implements StateMethods {

	private Game game;
	private Player player;
	private LevelHandler levelHandler;
	
	//private boolean levelCompleted = false;
	private boolean paused = false;
	
	public Playing(Game game) {
		this.game = game;
		initClasses();
	}
	
	public void loadNextLevel() {
		resetAll();
		levelHandler.loadNextLevel();
	}
	
	private void initClasses() {
		levelHandler = new LevelHandler(game);
		player = new Player(50, 50, (int) (32 * Game.SCALE), (int) (32 * Game.SCALE));
		player.loadLevelData(levelHandler.getCurrentLevel().getLevelData());
	}

	@Override
	public void update() {
		player.update();
	}

	@Override
	public void draw(Graphics g) {
		levelHandler.draw(g);
		player.draw(g);
		if(paused) {
			g.setColor(new Color(0,0,0,200));
			g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
		}
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
		case KeyEvent.VK_ESCAPE:
			paused = !paused;
			break;
		case KeyEvent.VK_DELETE:
			Gamestate.state = Gamestate.MENU;
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
