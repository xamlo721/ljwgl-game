package game;

import com.xamlo.core.engine.graphics.api.components.ICamera;
import com.xamlo.core.engine.graphics.components.DefaultSceneController;
import com.xamlo.core.engine.graphics.components.DefaultSceneRenderer;
import com.xamlo.engine.api.resources.IResourceLoader;
import com.xamlo.engine.resource.SimpleResourceLoader;
import com.xamlo.engine.resources.ResourceLoader;

import org.joml.Vector3f;

import com.xamlo.engine.Engine;

import game.graphics.PrimitiveCamera;
import game.graphics.PrimitiveScene;
import game.graphics.gui.GUIMainMenu;

public class GameApplication {
	
	protected Engine engine;
	
	private static IResourceLoader<String> resourceLoader = SimpleResourceLoader.createFromBundledList("/default_resources.properties");
	
//	protected PlanetGenerator planet;

	
	public GameApplication() {
		
		ResourceLoader.setResourceLoader(resourceLoader);
		
		ICamera cam = new PrimitiveCamera();
		cam.move(new Vector3f(0.0f, 0.0f, 1.5f));//Костыль, чтобы рисовало меню нормально
		
		engine = new Engine(cam, new GUIMainMenu(resourceLoader), new DefaultSceneRenderer(resourceLoader), new DefaultSceneController());
		//engine = new Engine(cam, new PrimitiveScene(resourceLoader), new DefaultSceneRenderer(resourceLoader));
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
