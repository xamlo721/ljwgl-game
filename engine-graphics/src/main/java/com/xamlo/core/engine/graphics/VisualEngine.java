package com.xamlo.core.engine.graphics;

import com.xamlo.core.engine.graphics.components.Camera;
import com.xamlo.core.engine.graphics.components.Window;

import ru.satomi.dc.primitive.Vec3f;


public class VisualEngine {
	private Window window;
	//private Skydome skydome;
	//private DcWrapper dcWrapper;
//	private ChunkOctreeWrapper chunkOctreeWrapper;
	
	public VisualEngine() {
		window = Window.getInstance();
	}
	
	public void init() {
		window.init();
		//skydome = new Skydome();
		//dcWrapper = new DcWrapper();
		//Почему ChunkOctreeWrapper это часть RenderEngine?
//		chunkOctreeWrapper = new ChunkOctreeWrapper(planet.getWorld().getChunkMap(), planet.getWorld().getMesh());
		
        Camera camera = Camera.getInstance();
        camera.setPosition(new Vec3f(0.f,0f,0f));
	}

	public void render() {	
		
//		Camera.getInstance().update();
//		
//		Default.clearScreen();
//		
//		//skydome.render();
//
//		//dcWrapper.update();
//		//dcWrapper.render();
//
//		chunkOctreeWrapper.update(Camera.getInstance().getPosition());
//		//-->chunkOctree.processCSGOperations();
//		//-->chunkOctree.update(pos);
//		//--> renderMesh();
//		chunkOctreeWrapper.updateKeyBoard();
//		
//		
//		
//		chunkOctreeWrapper.render();
//		
//		// draw into OpenGL window
//		window.render();
	}
	
	public void update(){}
	
	public void shutdown(){
//		chunkOctreeWrapper.cleanUp();
	}


}