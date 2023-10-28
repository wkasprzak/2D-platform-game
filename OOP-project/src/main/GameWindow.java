package main;

import javax.swing.JFrame;

public class GameWindow {

	private JFrame gameWindow;
	public GameWindow(GamePanel gamePanel) {
		
		gameWindow = new JFrame("Catticus: The Adventures of a Hungry Cat");
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.add(gamePanel);
		gameWindow.pack();
		gameWindow.setResizable(false);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);
		
	}
	
}
