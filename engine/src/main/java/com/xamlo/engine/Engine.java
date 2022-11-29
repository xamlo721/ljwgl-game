package com.xamlo.engine;

import com.xamlo.core.engine.graphics.RenderEngine;
import com.xamlo.engine.world.World;

public class Engine {
	
	World w;
	

	
	private boolean isRunning;
		
	private RenderEngine renderingEngine;


	
	public void init() {

		renderingEngine = new RenderEngine();
		renderingEngine.init();
		renderingEngine.createWindow(1920/2, 1080/2);
		

		
	}

	public void start() {
		if(isRunning)
			return;
		
//		run();
	}
	


}
