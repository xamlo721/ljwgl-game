package com.xamlo.core.engine.graphics.components;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.api.components.IScene;
import com.xamlo.core.engine.graphics.components.example.BigCubeExample;
import com.xamlo.core.engine.graphics.components.example.CubeExample;

import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.glPolygonMode;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL15.GL_TEXTURE0;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL15.glActiveTexture;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveScene implements IScene {
	

	// Shaders
	
	private final String vertexShaderSource = ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveVertexShader.glsl");
	
	private final String fragmentShaderSource =  ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveFragmentShader.glsl");
	
	private ShaderProgram shaderProgram;
	    
    private List<AbstractRenderableObject> cubes;

    private Matrix4f projectionMatrix;
    private Texture smile;

	@Override
    public void tranformScene(Matrix4f transformMatrix) {
    	this.projectionMatrix = transformMatrix;
    }

	@Override
	public void load() {		
		
        //Рисовать рамку или заливать цветом - закомментировать, если хотим цвет
		//glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glEnable(GL_DEPTH_TEST);

		shaderProgram = new ShaderProgram();
		shaderProgram.addVertexShader(vertexShaderSource);
		shaderProgram.addFragmentShader(fragmentShaderSource);
		shaderProgram.compileShader();
		shaderProgram.bind();
		try {
			shaderProgram.createUniform("projectionMatrix");
			shaderProgram.createUniform("worldMatrix");
			shaderProgram.createUniform("texture_sampler");

		} catch (Exception e) {
			e.printStackTrace();
		}
		smile = new Texture("C:/workspace/eclipse/gamedev/engine-graphics/res/textures/example/smile.png");
		cubes = new ArrayList<AbstractRenderableObject>();
		for (int i = 0; i < 5; i++) {
			CubeExample cube = new CubeExample(smile);
			cube.init();
			cube.setPosition(new Vector3f(
					((float)Math.random() - 0.5f) * 5, 
					((float)Math.random() - 0.5f) * 5, 
				   -((float)Math.random() % 100.0f + 5))
			);
			//cube.setPosition(new Vec3f( 0.1f,  0.1f, -1.0f));
			cube.setScale(0.5f);
			cubes.add(cube);
		}
		
		for (int i = 0; i < 1; i++) {
			BigCubeExample big = new BigCubeExample();
			big.init();
			big.setPosition(new Vector3f(
					((float)Math.random() - 0.5f) * 25, 
					((float)Math.random() - 0.5f) * 25, 
				   -((float)Math.random() % 100.0f + 25))
			);
			//cube.setPosition(new Vec3f( 0.1f,  0.1f, -1.0f));
			//big.setScale(0.25f);
			cubes.add(big);
		}
		
		shaderProgram.unbind();
        
        // clear the framebuffer
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
		System.out.println("Primitive scene loaded");
	}

	@Override
	public void renderFrame() {
        
		shaderProgram.bind();
		
		shaderProgram.setUniform("projectionMatrix", this.projectionMatrix);
		shaderProgram.setUniform("texture_sampler", 0);

		

		for (AbstractRenderableObject cube : cubes) {
			//Теперь матрица преобразования обновляется каждый раз
			shaderProgram.setUniform("worldMatrix", cube.getWorldMatrix());
			
			glActiveTexture(GL_TEXTURE0);
			
			smile.bind();

			cube.draw();
			cube.rotate(new Vector3f((float)Math.random(), 0.0f, ((float)Math.random())));
			//cube.move(new Vec3f(0.0001f, 0.000f, -0.00025f));
			cube.scale(0.999f);
		}

		
	    shaderProgram.unbind();

	}
	
	@Override
	public void release() {	    
		shaderProgram.cleanup();	
		for (AbstractRenderableObject cube : cubes) {
			cube.release();
		}
	}
	

}