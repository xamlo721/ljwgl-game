package com.xamlo.core.engine.graphics;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_CW;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrontFace;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL43;

import com.xamlo.core.engine.graphics.components.Camera;
import com.xamlo.core.engine.graphics.components.Window;

import ru.satomi.dc.primitive.Vec3f;


public class RenderEngine {
	
	@SuppressWarnings("unused")
	private GLFWErrorCallback errorCallback;
	
	private Window window;
	private Camera camera;
	
	
	private static int fps;
	private static float framerate = 30;
	private static float frameTime = 1.0f/framerate;
	private boolean isRendering;
	
	private static final long NANOSECOND = 1000000000;
	
	public RenderEngine() {
		window = Window.getInstance();
        camera = Camera.getInstance();

	}
	
	public void init() {

		glfwInit();

		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

        camera.setPosition(new Vec3f(0.f, 0f, 0f));
        
	}
	
	public void createWindow(int width, int height) {
		
		window.create(width, height);
		window.setWindowTitle("Game window");
		
//		ByteBuffer bufferedImage = ImageLoader.loadImageToByteBuffer("./res/logo/logo_lwjgl_icon32.png");
//		GLFWImage image = GLFWImage.malloc();
//		image.set(32, 32, bufferedImage);
//		window.setWindowIcon(image);
				
		glFrontFace(GL_CW);				
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glEnable(GL_DEPTH_TEST);     	
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_FRAMEBUFFER_SRGB);
		
		getDeviceProperties();
	}
	
	public void startRender() {
		if(isRendering)
			return;
		
		run();
	}
	
	private void run() {
		
		this.isRendering = true;
		
		int frames = 0;
		long frameCounter = 0;
		
		long lastTime = System.nanoTime();
		double unprocessedTime = 0;
		
		// Rendering Loop
		while(isRendering) {
			
			boolean render = false;
			
			long startTime = System.nanoTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double) NANOSECOND;
			frameCounter += passedTime;
		
			
			while(unprocessedTime > frameTime) {

				render = true;
				unprocessedTime -= frameTime;
				
				if(Window.getInstance().isCloseRequested()) {
					stopRendering();
				}
				
				renderFrame();
				
				if(frameCounter >= NANOSECOND) {
					fps = frames;
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if(render) {
				renderFrame();
				frames++;
			} else {
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}		
		}
		
		shutdown();	
	}
	
	private void stopRendering() {
		
		if(!isRendering)
			return;
		
		isRendering = false;
		
	}

	public void renderFrame() {	
		
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
		
	public void shutdown(){
		
		window.close();
		glfwTerminate();

	}
	
	
	private void getDeviceProperties() {
		System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION) + " bytes");
		System.out.println("Max Geometry Uniform Blocks: " + GL31.GL_MAX_GEOMETRY_UNIFORM_BLOCKS+ " bytes");
		System.out.println("Max Geometry Shader Invocations: " + GL40.GL_MAX_GEOMETRY_SHADER_INVOCATIONS + " bytes");
		System.out.println("Max Uniform Buffer Bindings: " + GL31.GL_MAX_UNIFORM_BUFFER_BINDINGS + " bytes");
		System.out.println("Max Uniform Block Size: " + GL31.GL_MAX_UNIFORM_BLOCK_SIZE + " bytes");
		System.out.println("Max SSBO Block Size: " + GL43.GL_MAX_SHADER_STORAGE_BLOCK_SIZE + " bytes");

		int[] work_grp_cnt = new int[3];

		GL31.glGetIntegeri_v(GL43.GL_MAX_COMPUTE_WORK_GROUP_COUNT, 0, work_grp_cnt);
		GL31.glGetIntegeri_v(GL43.GL_MAX_COMPUTE_WORK_GROUP_COUNT, 1, work_grp_cnt);
		GL31.glGetIntegeri_v(GL43.GL_MAX_COMPUTE_WORK_GROUP_COUNT, 2, work_grp_cnt);

		System.out.println("GL_MAX_COMPUTE_WORK_GROUP_COUNT 0 " + work_grp_cnt[0]);
		System.out.println("GL_MAX_COMPUTE_WORK_GROUP_COUNT 1 " + work_grp_cnt[1]);
		System.out.println("GL_MAX_COMPUTE_WORK_GROUP_COUNT 2 " + work_grp_cnt[2]);

		GL31.glGetIntegeri_v(GL43.GL_MAX_COMPUTE_WORK_GROUP_SIZE, 0, work_grp_cnt);
		GL31.glGetIntegeri_v(GL43.GL_MAX_COMPUTE_WORK_GROUP_SIZE, 1, work_grp_cnt);
		GL31.glGetIntegeri_v(GL43.GL_MAX_COMPUTE_WORK_GROUP_SIZE, 2, work_grp_cnt);
		System.out.println("GL_MAX_COMPUTE_WORK_GROUP_SIZE 0 " + work_grp_cnt[0]);
		System.out.println("GL_MAX_COMPUTE_WORK_GROUP_SIZE 1 " + work_grp_cnt[1]);
		System.out.println("GL_MAX_COMPUTE_WORK_GROUP_SIZE 2 " + work_grp_cnt[2]);


		System.out.println("GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS " + GL11.glGetInteger(GL43.GL_MAX_COMPUTE_WORK_GROUP_INVOCATIONS));
	}


}