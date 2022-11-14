package game;

import com.xamlo.engine.Engine;

public class GameApplication {
	
	protected Engine engine;
//	protected PlanetGenerator planet;

	
	public GameApplication() {
		engine = new Engine();
//		planet = new PlanetGenerator();
	}
	

	
	public void init(){
//		planet.generateGround();

		engine.init();

	}
	
//	public void loadWorld() {
//		engine.setWorld(planet);
//	}
	
	public void launch() {
		engine.start();
	}
	
	public Engine getEngine() {
		return engine;
	}
	
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
}
