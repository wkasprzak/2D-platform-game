package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class Import {
	
	public static final String PLAYER = "/Cat-Sheet.png";
	public static final String TILES = "/tileset_forest.png";
	public static final String LEVEL_ONE = "/level_one.png";
	
	public static BufferedImage ImportData(String fileName) {		
		BufferedImage image = null;
		InputStream is = Import.class.getResourceAsStream(fileName);
		try {
			image = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return image;
	}
	
	public static int[][] ImportLevelData() {
		int[][] levelData = new int[Game.NUMBER_OF_TILES_IN_HEIGHT][Game.NUMBER_OF_TILES_IN_WIDTH];
		BufferedImage level = ImportData(LEVEL_ONE);
		for(int i = 0; i < level.getHeight(); i++)
			for(int j = 0; j < level.getWidth(); j++) {
				Color color = new Color(level.getRGB(j, i));
				levelData[i][j] = color.getRed();
				}
		return levelData;
	}
	
}
