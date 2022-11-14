package game;

public class Main {
	
	public static void main(String[] args) {

	
		GameApplication game = new GameApplication();
		
		
		game.getEngine().createWindow(1920/2, 1080/2);
	
		//game.loadWorld();
		
		game.init();
	
		game.launch();
	}
	
}
