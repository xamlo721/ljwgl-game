package com.xamlo.core.engine.graphics.components;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

import com.xamlo.core.engine.graphics.api.components.IScene;
import com.xamlo.core.engine.graphics.api.components.ISceneRenderer;

public class DefaultSceneRenderer implements ISceneRenderer {
	
	// Shaders
	
	private final String vertexShaderSource = ShaderProgram.loadShaderFromResource("/shaders/PrimitiveVertexShader.glsl");
	
	private final String fragmentShaderSource =  ShaderProgram.loadShaderFromResource("/shaders/PrimitiveFragmentShader.glsl");
	
	private ShaderProgram shaderProgram;

	@Override
	public void init() {
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
		
		shaderProgram.unbind();
        
        // clear the framebuffer
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
		
	}

	@Override
	public void loadScene(IScene scene) {
		
		//TODO: Подгрузка в кеш текстур и моделей сцены
		
		scene.load();
		
	}
	
	@Override
	public void render(IScene scene) {
        
		shaderProgram.bind();
		
		shaderProgram.setUniform("projectionMatrix", scene.getProjectionMatrix());
		shaderProgram.setUniform("texture_sampler", 0);
		
		for (AbstractRenderableObject obj : scene.getRenderableObject()) {
			//Теперь матрица преобразования обновляется каждый раз
			shaderProgram.setUniform("worldMatrix", obj.getWorldMatrix());
			
			glActiveTexture(GL_TEXTURE0);
			
			//smile.bind();
			obj.getMesh().bind();

		    // Draw the vertices
		    //glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());
			

			/**
			 * mode: Задает примитивы для рендеринга, в данном случае треугольники. Здесь никаких изменений.
			 * count: Указывает количество элементов, которые должны быть отрисованы.
			 * type: Указывает тип значения в данных индексов. В данном случае мы используем целые числа.
			 * indices: Задает смещение, которое необходимо применить к данным индексов для начала рендеринга.
			 */
			glDrawElements(GL_TRIANGLES, obj.getMesh().getNumVertices(), GL_UNSIGNED_INT, 0);
			
			//obj.rotate(new Vector3f((float)Math.random(), 0.0f, ((float)Math.random())));
			//obj.move(new Vec3f(0.0001f, 0.000f, -0.00025f));
			//obj.scale(0.999f);
		}
		
	    shaderProgram.unbind();

		
	}

	@Override
	public void cleanup() {
		shaderProgram.cleanup();			
	}



}
