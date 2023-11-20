package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import states.Gamestate;
import main.Game;
import utils.Import;

public class LevelHandler {

	private Game game;
	private BufferedImage[] levelTiles;
	private ArrayList<Level> levels;
	private int levelIndex = 0;
	
	public LevelHandler(Game game) {
		this.game = game;
		importTileset();
		levels = new ArrayList<>();
		createLevels();
	}
	
	public void loadNextLevel() {
		levelIndex++;
		if(levelIndex >= levels.size()) {
			levelIndex = 0;
			System.out.println("No more levels, game completed!");
			Gamestate.state = Gamestate.MENU;
		}
		
		Level newLevel = levels.get(levelIndex);
		game.getPlaying().getPlayer().loadLevelData(newLevel.getLevelData());
	}
	
	private void createLevels() {
		List<int[][]> allLevels = Import.importCSVData();
		for(int[][] level : allLevels)
			levels.add(new Level(level));		
	}

	private void importTileset() {
		BufferedImage image = Import.ImportData(Import.TILES);
		levelTiles = new BufferedImage[30];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				levelTiles[i*6 + j] = image.getSubimage(j*16, i*16, 16, 16);
			}
		}
	}

	public void draw(Graphics g, int offset) {
		for(int i = 0; i < Game.NUMBER_OF_TILES_IN_HEIGHT; i++) {
			for(int j = 0; j < levels.get(levelIndex).getLevelData()[i].length; j++) {
				int index = levels.get(levelIndex).getTileIndex(j, i);
				g.drawImage(levelTiles[index], j*Game.TILES_SIZE - offset, i*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
		}
	}
	
	public Level getCurrentLevel() {
		return levels.get(levelIndex);
	}

}
