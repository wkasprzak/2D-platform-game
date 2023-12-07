package utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Import {

    // Resources names
	public static final String PLAYER = "/Cat-Sheet.png";
	public static final String TILES = "/tileset_forest.png";
	public static final String MENU_BACKGROUND_IMAGE = "/background_menu.png";
    public static final String BIG_CLOUDS = "/big_clouds.png";
    public static final String SMALL_CLOUDS = "/small_clouds.png";
    public static final String BACKGROUND = "/background.png";
    public static final String MOUNTAINS = "/mountains.png";
    public static final String PLAINS = "/plains.png";
    public static final String GRASS = "/grass.png";
    public static final String HP = "/hp.png";
    public static final String WARTHOG = "/warthog.png";
    public static final String ANGEL = "/angel.png";
    public static final String NEMO = "/nemo.png";
    public static final String SPIKES = "/spikes.png";

    // Importing images
	public static BufferedImage importImage(String fileName) {
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

    // Data from CSV file
    public static List<int[][]> importCSVData() {
        
    	URL url = Import.class.getResource("/levels");
        File directory = null;

        try {
            directory = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        List<int[][]> levelDataList = new ArrayList<>();

        File[] files = directory.listFiles();

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".csv")) {
                List<int[]> levelData = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] stringVal = line.split(",");
                        int[] row = new int[stringVal.length];
                        for (int i = 0; i < stringVal.length; i++) {
                            try {
                                row[i] = Integer.parseInt(stringVal[i]);
                            } catch (NumberFormatException e) {
                                row[i] = 0;
                            }
                        }
                        levelData.add(row);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int[][] levelDataArray = new int[levelData.size()][];
                for (int i = 0; i < levelData.size(); i++) {
                    levelDataArray[i] = levelData.get(i);
                }
                levelDataList.add(levelDataArray);
            }
        }
        return levelDataList;
    }
}
