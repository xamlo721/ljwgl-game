package game;

import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.api.components.ICamera;
import com.xamlo.engine.Engine;

import game.graphics.PrimitiveCamera;
import game.graphics.PrimitiveScene;
import game.graphics.gui.GUIMainMenu;

public class GameApplication {
	
	protected Engine engine;
//	protected PlanetGenerator planet;

	
	public GameApplication() {
		ICamera cam = new PrimitiveCamera();
		cam.move(new Vector3f(0.0f, 0.0f, 1.5f));//Костыль, чтобы рисовало меню нормально
		
		engine = new Engine(cam, new GUIMainMenu());
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
