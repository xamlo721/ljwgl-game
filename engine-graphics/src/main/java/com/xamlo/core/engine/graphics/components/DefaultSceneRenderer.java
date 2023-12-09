package com.xamlo.core.engine.graphics.components;

import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;
import static org.lwjgl.opengl.GL13.GL_BLEND;
import static org.lwjgl.opengl.GL13.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL13.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL13.glBlendFunc;
import static org.lwjgl.opengl.GL13.GL_FRONT_AND_BACK;
import static org.lwjgl.opengl.GL13.GL_LINE;
import static org.lwjgl.opengl.GL13.glPolygonMode;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.api.components.IScene;
import com.xamlo.core.engine.graphics.api.components.ISceneRenderer;
import com.xamlo.core.engine.graphics.api.gui.IWidget;
import com.xamlo.core.engine.graphics.api.primitives.IVertex;
import com.xamlo.core.engine.graphics.components.attribs.PositionAttribute;
import com.xamlo.core.engine.graphics.components.attribs.TexCoordAttribute;
import com.xamlo.core.engine.graphics.components.gui.WidgetGeometry;
import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.primitives.VertexStructure;
import com.xamlo.engine.api.resources.IShaderResource;
import com.xamlo.engine.api.resources.ResourceLoader;

public class DefaultSceneRenderer implements ISceneRenderer {
	
	// Shaders
	
	private final IShaderResource<String> vertexShaderSource;
	
	private final IShaderResource<String> fragmentShaderSource;
	
	private ShaderProgram shaderProgram;

	public DefaultSceneRenderer(ResourceLoader<String> resourceLoader) {
		this.vertexShaderSource = resourceLoader.loadShader("shader.primitive.vertex");
		this.fragmentShaderSource = resourceLoader.loadShader("shader.primitive.fragment");
	}


	@Override
	public void init() {
        //Рисовать рамку или заливать цветом - закомментировать, если хотим цвет
		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glEnable(GL_DEPTH_TEST);		
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		


		shaderProgram = new ShaderProgram();
		shaderProgram.addVertexShader(vertexShaderSource.getShaderProgram());
		shaderProgram.addFragmentShader(fragmentShaderSource.getShaderProgram());
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
		
		System.out.println("i render " + scene.getRenderableObject().size() + " objects");

		for (AbstractRenderableObject obj : scene.getRenderableObject()) {
			//Теперь матрица преобразования обновляется каждый раз
			shaderProgram.setUniform("worldMatrix", obj.getWorldMatrix());
			
			glActiveTexture(GL_TEXTURE0);
			
			//smile.bind();
			obj.getMesh().bind();
//			if (obj.hasBackgroundImage()) {
//				obj.getBackgroundImage().bind();
//			}

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
		System.out.println("i render " + scene.getGuiElements().size() + " ui elements");

		for (IWidget obj : scene.getGuiElements()) {
			
			AbstractRenderableObject uiGrapphicElement = new AbstractRenderableObject() {

			    private static GraphicalMesh defaultWWidgetMesh;

				@Override
				public void init() {
					//NO-OP
				}

				@Override
				public void release() {
					defaultWWidgetMesh.cleanup();				
				}

				@Override
				public GraphicalMesh getMesh() {
					
					if (defaultWWidgetMesh == null) {
						this.loadMesh();
					}

					return defaultWWidgetMesh;
				}

				@Override
				public void loadMesh() {
					
			    	IVertex[] vertices = new Vertex[4];
			    	int i = 0;
			    	
			    	VertexStructure vertexScruct = new VertexStructure();
			    	vertexScruct.addAttribute(new PositionAttribute());
			    	vertexScruct.addAttribute(new TexCoordAttribute());
			    	vertexScruct.setVertexCount(4);
			    	
			    	vertices[i++] = new Vertex(5).append(new Vector3f(-1.0f,  1.0f, 0.0f)).append(new Vector2f(0.0f, 0.0f)); //V1
			    	vertices[i++] = new Vertex(5).append(new Vector3f(-1.0f, -1.0f, 0.0f)).append(new Vector2f(0.0f, 1.0f)); //V2
			    	vertices[i++] = new Vertex(5).append(new Vector3f( 1.0f, -1.0f, 0.0f)).append(new Vector2f(1.0f, 1.0f)); //V3
			    	vertices[i++] = new Vertex(5).append(new Vector3f( 1.0f,  1.0f, 0.0f)).append(new Vector2f(1.0f, 0.0f)); //V4


			    	i = 0;
			    	int[] indices = new int[6]; 
			    	//FACE
			    	indices[i++] = 0;
			    	indices[i++] = 1;
			    	indices[i++] = 3;
			    	
			    	indices[i++] = 3;
			    	indices[i++] = 1;
			    	indices[i++] = 2;

			    	defaultWWidgetMesh = new GraphicalMesh(vertices, vertexScruct, indices);				
				}
				
			};
			
			//TODO: Протестить, те ли вообще поля я трогаю
			//this.setExpandGeometry((float)this.geometry.width / (float)geometry.width, (float)this.geometry.height / (float)geometry.height, 1.0f);
			//this.setExpandGeometry((float)geometry.width/ (float)1920 ,(float)geometry.height/ (float)1080  , 1.0f);

			WidgetGeometry geometry = obj.getWidgetGeometry();
			
			uiGrapphicElement.setExpandGeometry(
					(float) geometry.getWidth() / (float)1920,
					(float) geometry.getHeight() / (float)1080,
					1.0f
			);

			uiGrapphicElement.setPosition(geometry.getXCoord(), geometry.getyCoord(),  0.0f);

			
			
			shaderProgram.setUniform("worldMatrix", uiGrapphicElement.getWorldMatrix());
			
			glActiveTexture(GL_TEXTURE0);
			
			uiGrapphicElement.getMesh().bind();
			if (obj.hasBackgroundImage()) {
				obj.getBackgroundImage().bind();
			}


			glDrawElements(GL_TRIANGLES, uiGrapphicElement.getMesh().getNumVertices(), GL_UNSIGNED_INT, 0);
			
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
