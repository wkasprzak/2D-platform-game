package levels;

import entities.Warthog;
import main.Game;
import objects.Fish;
import objects.Spikes;

import java.awt.*;
import java.util.ArrayList;

import static levels.LevelHandler.spawnPoint;

public class Level {
	
	private int[][] levelData;
	private int[][] csvLevel;
	private ArrayList<Warthog> warthogs;
	private ArrayList<Fish> fish;
	private ArrayList<Spikes> spikes;

	private int maxOffset;

	private Point spawn;
	
	public Level(int[][] csvLevel) {
		this.csvLevel = csvLevel;
		createLevelData();
		createEnemies();
		createFish();
		createSpikes();
		checkLevelOffset();
		checkPlayerSpawn();
	}

	// Environment
	private void createSpikes() {
		spikes = LevelHandler.getSpikes(csvLevel);
	}

	private void createFish() {
		fish = LevelHandler.getFish(csvLevel);
	}

	private void createEnemies() {
		warthogs = LevelHandler.getWarthogs(csvLevel);
	}

	// Level data
	private void checkPlayerSpawn() {
		spawn = spawnPoint(csvLevel);
	}

	private void checkLevelOffset() {
		int levelWidth = csvLevel[0].length;
		int maxTilesOffset = levelWidth - Game.NUMBER_OF_TILES_IN_WIDTH;
		maxOffset = Game.TILES_SIZE * maxTilesOffset;
	}

	private void createLevelData() {
		levelData = csvLevel;
	}
	
	// Getters & setters

	public int[][] getLevelData() {
		return levelData;
	}
	
	public int getTileIndex(int x, int y) {
		return levelData[y][x];
	}

	public int getLevelOffset() {
		return maxOffset;
	}

	public ArrayList<Warthog> getWarthogs() {
		return warthogs;
	}

	public ArrayList<Fish> getFish() {
		return fish;
	}

	public ArrayList<Spikes> getSpikes() {
		return spikes;
	}

	public Point getSpawn() {
		return spawn;
	}
}
