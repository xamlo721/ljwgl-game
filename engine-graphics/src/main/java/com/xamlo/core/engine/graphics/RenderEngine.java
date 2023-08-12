package com.xamlo.core.engine.graphics;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.GL_BACK;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_CW;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glCullFace;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glFrontFace;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL40;
import org.lwjgl.opengl.GL43;

import com.xamlo.core.engine.graphics.api.components.ICamera;
import com.xamlo.core.engine.graphics.api.components.IRenderEngine;
import com.xamlo.core.engine.graphics.api.components.IScene;
import com.xamlo.core.engine.graphics.api.components.ISceneRenderer;
import com.xamlo.core.engine.graphics.components.Window;
import com.xamlo.core.engine.graphics.components.DefaultSceneRenderer;
import com.xamlo.core.engine.graphics.components.LJWGLKeyboard;
import com.xamlo.core.engine.graphics.components.LJWGLMouse;

public class RenderEngine implements IRenderEngine {
	
	@SuppressWarnings("unused")
	private GLFWErrorCallback errorCallback;
	
	private Window window;
	public IScene scene;
	private final ISceneRenderer renderer;
	
	private boolean isRendering;
	public boolean isCloseRequest;

	private static final float movAmt = 0.11f;

	private ICamera camera;
    private Matrix4f projectionMatrix;
    private LJWGLKeyboard keyboard;
    private LJWGLMouse mouse;

	
	public RenderEngine(ISceneRenderer renderer) {
		this.isCloseRequest = false;
		this.renderer = renderer;
	}
	
	public void setCamera(ICamera cam) {
		this.camera = cam;
	}
	
	public void setScene(IScene scene) {
		this.scene = scene;
	}
	
	public boolean isRendering() {
		return this.isRendering && !this.window.isCloseRequested();
	}
	

	@Override
	public void init() {
		
		window = Window.getInstance();
        //camera = Camera.getInstance();

		if(glfwInit() == false) {
			//Исключение, если мы не можем инициализироваться
		}

		//Может вызываться перед инициализацией
		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));


        //camera.setAspectRatio(480, 480);
        camera.setFov((float) Math.toRadians(60.0f));
        //camera.setPosition(new Vec3f(0.f, 0f, 0f));

        projectionMatrix = new Matrix4f().perspective(
       		    camera.getFov(), 
           		camera.getAspectRatio(),
           	    camera.getNearDistance(), 
           	    camera.getFarDistance()
        );
        projectionMatrix = projectionMatrix.mul(camera.getViewMatrix());
	}
	
	@Override
	public void createWindow(int width, int height) {

		window.create(width, height);
		window.setWindowTitle("Game window");
		
//		ByteBuffer bufferedImage = ImageLoader.loadImageToByteBuffer("./res/logo/logo_lwjgl_icon32.png");
//		GLFWImage image = GLFWImage.malloc();
//		image.set(32, 32, bufferedImage);
//		window.setWindowIcon(image);
				
		glFrontFace(GL_CW);				
//		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		//Настройка OpenGL для того,
		//чтобы она не рисовала обратную сторону 
		//модели, которую мы не видим
		glEnable(GL_DEPTH_TEST);
		//Настройка OpenGL для того,
		//чтобы она могла работать с текстурами
		//Но, если верить туториалам
		//эта настройка не обязательна, если мы рендерим
		//с помощью шейдеров glsl
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_FRAMEBUFFER_SRGB);
		
		getDeviceProperties();


	}
	
	public void loadInputDevice() {
		keyboard = new LJWGLKeyboard();
		mouse = new LJWGLMouse();

	}

	@Override
	public void stop() {
		
		System.out.println("stop rendering");
		
		if(!isRendering) {
			return;
		}
		
		isRendering = false;

		
	}

	public void renderFrame() {	
        // Set the clear color
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 

        glViewport(0, 0, window.getWidth(), window.getHeight());

        scene.tranformScene(projectionMatrix);

        
		// Вся логическая сцена
        renderer.render(scene);
		
        projectionMatrix = new Matrix4f().perspective(
       		    camera.getFov(), 
           		camera.getAspectRatio(),
           	    camera.getNearDistance(), 
           	    camera.getFarDistance()
        );
        projectionMatrix = projectionMatrix.mul(camera.getViewMatrix());
        
        
		if(keyboard.isKeyHold(GLFW_KEY_W)) {
			camera.move( new Vector3f(0.0f, 0.0f, -movAmt));

		}
		if(keyboard.isKeyHold(GLFW_KEY_S)) {
			camera.move( new Vector3f(0.0f, 0.0f, movAmt));
		}
		if(keyboard.isKeyHold(GLFW_KEY_A)) {
			camera.move( new Vector3f(-movAmt, 0.0f, 0.0f));

		}
		if(keyboard.isKeyHold(GLFW_KEY_D)) {
			camera.move( new Vector3f(movAmt, 0.0f, 0.0f));
		}
		
		if(keyboard.isKeyHold(GLFW_KEY_SPACE)) {
			camera.move( new Vector3f(0.0f, movAmt, 0.0f));

		}
		if(keyboard.isKeyHold(GLFW_KEY_LEFT_SHIFT)) {
			camera.move( new Vector3f(0.0f, -movAmt, 0.0f));
		}
		
		if(keyboard.isKeyHold(GLFW_KEY_Q)) {
			camera.rotate(new Vector3f(0.0f, 0.0f, movAmt));

		}
		if(keyboard.isKeyHold(GLFW_KEY_E)) {
			camera.rotate(new Vector3f(0.0f, 0.0f, -movAmt));
		}
		
		// free mouse rotation
		if(mouse.isShowCursor() && mouse.getLockedCursorPosition()!=null) {
			float dy = mouse.getLockedCursorPosition().y() - mouse.getCursorPosition().y();
			float dx = mouse.getLockedCursorPosition().x() - mouse.getCursorPosition().x();

			camera.rotate(new Vector3f(dy * movAmt, dx * movAmt, 0.0f));

			glfwSetCursorPos(Window.getInstance().getWindow(),
					mouse.getLockedCursorPosition().x(),
					mouse.getLockedCursorPosition().y());
			
		}
		//camera.setFov(camera.getFov() + 0.01f);
		//camera.move(new Vector3f(0.0f, 0.01f, 0.01f));
		//camera.rotate(new Vector3f(0.0f, 0.1f, 0.3f));

		// draw into OpenGL window
		this.window.swapBuffers();
		
		keyboard.update();
		

	}

	@Override
	public void start() {
		if(isRendering)
			return;
	    
	    this.isRendering = true;

	    this.renderer.init();
	    this.scene.load();	
	}


	@Override
	public void release() {
		
		scene.release();
		window.close();
		glfwTerminate();
		this.isCloseRequest = true;		
		
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