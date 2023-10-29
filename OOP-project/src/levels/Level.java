package levels;

public class Level {
	
	private int[][] levelData;
	private int[][] csvLevel;
	
	public Level(int[][] csvLevel) {
		this.csvLevel = csvLevel;
		createLevelData();
	}
	
	private void createLevelData() {
		levelData = csvLevel;
	}

	public int[][] getLevelData() {
		return levelData;
	}
	
	public int getLevelIndex(int x, int y) {
		return levelData[y][x];
	}
}
