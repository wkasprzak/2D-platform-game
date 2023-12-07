package levels;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import entities.Warthog;
import objects.Fish;
import objects.Spikes;
import states.Gamestate;
import main.Game;
import utils.Import;

import static objects.InGameObject.FISH;
import static objects.InGameObject.SPIKES;
import static utils.Enemies.WARTHOG;

public class LevelHandler {

	private Game game;
	private BufferedImage[] levelTiles;
	public ArrayList<Level> levels;
	public int levelIndex;
	
	public LevelHandler(Game game) {
		this.game = game;
		importTileset();
		levels = new ArrayList<>();
		createLevels();
	}

	// Creating level
	private void createLevels() {
		List<int[][]> allLevels = Import.importCSVData();
		for(int[][] level : allLevels)
			levels.add(new Level(level));
	}

	private void importTileset() {
		BufferedImage image = Import.importImage(Import.TILES);
		levelTiles = new BufferedImage[35];
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 6; j++) {
				levelTiles[i*6 + j] = image.getSubimage(j*16, i*16, 16, 16);
			}
		}
	}

    public void loadNextLevel() {
		levelIndex++;
		if(levelIndex >= levels.size()) {
			levelIndex = 0;
			Gamestate.state = Gamestate.WINSTATE;
		}

		Level newLevel = levels.get(levelIndex);
		game.getPlaying().getEnemies().addEnemies(newLevel);
		game.getPlaying().getPlayer().loadLevelData(newLevel.getLevelData());
		game.getPlaying().setLevelOffset(newLevel.getLevelOffset());
		game.getPlaying().getObjectHandler().loadObjects(newLevel);
		System.out.println(newLevel.getWarthogs().size());
	}

	public void draw(Graphics g, int offset) {
		for(int i = 0; i < Game.NUMBER_OF_TILES_IN_HEIGHT; i++) {
			for(int j = 0; j < levels.get(levelIndex).getLevelData()[i].length; j++) {
				int index = levels.get(levelIndex).getTileIndex(j, i);
				g.drawImage(levelTiles[index], j*Game.TILES_SIZE - offset, i*Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
		}
	}

	// Environment
	public static ArrayList<Warthog> getWarthogs(int[][] csvLevel) {
		ArrayList<Warthog> list = new ArrayList<>();
		for(int i = 0; i < Game.NUMBER_OF_TILES_IN_HEIGHT; i++) {
			for(int j = 0; j < csvLevel[i].length; j++) {
				if(csvLevel[i][j] == WARTHOG)
					list.add(new Warthog(j * Game.TILES_SIZE, i * Game.TILES_SIZE));
			}
		}
		return list;
	}

	public static ArrayList<Fish> getFish(int[][] csvLevel) {
		ArrayList<Fish> list = new ArrayList<>();
		for(int i = 0; i < Game.NUMBER_OF_TILES_IN_HEIGHT; i++) {
			for(int j = 0; j < csvLevel[i].length; j++) {
				if(csvLevel[i][j] == FISH)
					list.add(new Fish(j * Game.TILES_SIZE, i * Game.TILES_SIZE, 32));
			}
		}
		return list;
	}

	public static ArrayList<Spikes> getSpikes(int[][] csvLevel) {
		ArrayList<Spikes> list = new ArrayList<>();
		for(int i = 0; i < Game.NUMBER_OF_TILES_IN_HEIGHT; i++) {
			for(int j = 0; j < csvLevel[i].length; j++) {
				if(csvLevel[i][j] == SPIKES)
					list.add(new Spikes(j * Game.TILES_SIZE, i * Game.TILES_SIZE, SPIKES));
			}
		}
		return list;
	}

	public static Point spawnPoint(int[][] csvLevel) {
		for(int i = 0; i < Game.NUMBER_OF_TILES_IN_HEIGHT; i++) {
			for(int j = 0; j < csvLevel[i].length; j++) {
				if(csvLevel[i][j] == 30)
					return new Point(j * Game.TILES_SIZE, i * Game.TILES_SIZE);
			}
		}
		return new Point(Game.TILES_SIZE, Game.TILES_SIZE);
	}

	// Getter
	public Level getCurrentLevel() {
		return levels.get(levelIndex);
	}

}
