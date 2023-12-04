package states;

public enum Gamestate {
	// List of states
	PLAYING, MENU, EXIT, HELP, STORY, OPTIONS, PAUSE, GAMEOVER, LEVELCOMPLETED;
	// Starting state
	public static Gamestate state = MENU;
}
