package com.xamlo.core.engine.graphics;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
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
	//private Skydome skydome;
	//private DcWrapper dcWrapper;
//	private ChunkOctreeWrapper chunkOctreeWrapper;
	
	public RenderEngine() {
		window = Window.getInstance();
        camera = Camera.getInstance();

	}
	
	public void init() {

		glfwInit();

		glfwSetErrorCallback(errorCallback = GLFWErrorCallback.createPrint(System.err));

		

		
		
		//skydome = new Skydome();
		//dcWrapper = new DcWrapper();
		//Почему ChunkOctreeWrapper это часть RenderEngine?
//		chunkOctreeWrapper = new ChunkOctreeWrapper(planet.getWorld().getChunkMap(), planet.getWorld().getMesh());
		
        camera.setPosition(new Vec3f(0.f, 0f, 0f));
        
	}
	
	public void createWindow(int width, int height) {
		

		
		window.create(720, 480);
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
		
		window.close();
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