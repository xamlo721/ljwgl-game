package com.xamlo.core.engine.graphics.components;

import org.joml.Matrix4f;

import ru.satomi.dc.primitive.Vec3f;

import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL11.GL_LINE;
import static org.lwjgl.opengl.GL11.glPolygonMode;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveScene implements IScene {
	

	// Shaders
	
	private final String vertexShaderSource = ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveVertexShader.glsl");
	
	private final String fragmentShaderSource =  ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveFragmentShader.glsl");
	
	private ShaderProgram shaderProgram;
	    
    private List<CubeExample> cubes;

    private Matrix4f projectionMatrix;

	@Override
    public void tranformScene(Matrix4f transformMatrix) {
    	this.projectionMatrix = transformMatrix;
    }

	@Override
	public void load() {		
		
        //Рисовать рамку или заливать цветом - закомментировать, если хотим цвет
		//glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);

		shaderProgram = new ShaderProgram();
		shaderProgram.addVertexShader(vertexShaderSource);
		shaderProgram.addFragmentShader(fragmentShaderSource);
		shaderProgram.compileShader();
		shaderProgram.bind();
		try {
			shaderProgram.createUniform("projectionMatrix");
			shaderProgram.createUniform("worldMatrix");

		} catch (Exception e) {
			e.printStackTrace();
		}
		cubes = new ArrayList<CubeExample>();
		for (int i = 0; i < 100_000_000; i++) {
			CubeExample cube = new CubeExample();
			cube.init();
			cube.setPosition(new Vec3f(
					((float)Math.random() - 0.5f) * 5, 
					((float)Math.random() - 0.5f) * 5, 
				   -((float)Math.random() % 100.0f + 5))
			);
			//cube.setPosition(new Vec3f( 0.1f,  0.1f, -1.0f));
			cube.setScale(0.05f);
			cubes.add(cube);
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

		

		for (CubeExample cube : cubes) {
			//Теперь матрица преобразования обновляется каждый раз
			shaderProgram.setUniform("worldMatrix", cube.getWorldMatrix());
			
			cube.draw();
			cube.rotate(new Vec3f((float)Math.random(), 0.0f, ((float)Math.random())));
			//cube.move(new Vec3f(0.0001f, 0.000f, -0.00025f));
			//cube.scale(0.999f);
		}

		
	    shaderProgram.unbind();

	}
	
	@Override
	public void release() {	    
		shaderProgram.cleanup();	
		for (CubeExample cube : cubes) {
			cube.release();
		}
	}
	

}