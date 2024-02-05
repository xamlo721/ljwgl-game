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
import org.joml.Vector3d;
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
			glDrawElements(GL_TRIANGLES, obj.getMesh().getVertexCount(), GL_UNSIGNED_INT, 0);
			
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
			    	
			    	Vertex v1 = (Vertex) new Vertex(5).append(new Vector3f(-1.0f,  1.0f, 0.0f)).append(new Vector2f(0.0f, 0.0f)); //V1
			    	Vertex v2 = (Vertex) new Vertex(5).append(new Vector3f(-1.0f, -1.0f, 0.0f)).append(new Vector2f(0.0f, 1.0f)); //V2
			    	Vertex v3 = (Vertex) new Vertex(5).append(new Vector3f( 1.0f, -1.0f, 0.0f)).append(new Vector2f(1.0f, 1.0f)); //V3
			    	Vertex v4 = (Vertex) new Vertex(5).append(new Vector3f( 1.0f,  1.0f, 0.0f)).append(new Vector2f(1.0f, 0.0f)); //V4
			    	
			    	vertices[i++] = v1;
			    	vertices[i++] = v2;
			    	vertices[i++] = v3;
			    	vertices[i++] = v4;


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
			System.out.println("																				");
			System.out.println("rendering UIElement :" + obj);
			System.out.println("UIElement wight" + this + ". coords:  width [" + geometry.getXCoord() + ", " + (geometry.getWidth() + geometry.getXCoord()) + "]" );
			System.out.println("UIElement height" + this + ". coords:  height [" + geometry.getYCoord() + ", " + (geometry.getHeight() + geometry.getYCoord()) + "]" );

			
			

			
			final float screenWidght = 1920.0f *2;
			final float screenheight = 1080.0f *2;
			
			//Координты начала отрисовки объекта
			float localScreenXCoord = ((float) geometry.getXCoord()) / screenWidght;
			float localScreenYCoord = ((float) geometry.getYCoord()) / screenheight;
			
			
			System.out.println("UIElement draw X position:  [" + localScreenXCoord + "]" );
			System.out.println("UIElement draw Y position:  [" + localScreenYCoord + "]" );

			uiGrapphicElement.setPosition(localScreenXCoord, localScreenYCoord, 0.0f);

			float displayedWidth = (geometry.getWidth()) / screenWidght;
			float displayedHeight= (geometry.getHeight()) / screenheight;
			
			System.out.println("UIElement draw width:  [" + displayedWidth + "]" );
			System.out.println("UIElement draw height: [" + displayedHeight + "]" );
			
			uiGrapphicElement.setExpandGeometry(
					(float)  displayedWidth,
					//0.5f,
					(float)  displayedHeight, 
					//0.5f,
					1.0f );
			
			
			
//			uiGrapphicElement.scale(0.999f);
//			uiGrapphicElement.rotate(new Vector3f((float)Math.random(), 0.0f, ((float)Math.random())));
//			uiGrapphicElement.move(new Vector3f(0.0001f, 0.000f, -0.00025f));

			
			shaderProgram.setUniform("worldMatrix", uiGrapphicElement.getWorldMatrix());
			
			glActiveTexture(GL_TEXTURE0);
			
			//ERROR тут пустой почему-то
			uiGrapphicElement.getMesh().bind();
			
			GraphicalMesh elementMesh = uiGrapphicElement.getMesh();
			
			for (Vector3d v : elementMesh.getVertices()) {
				System.out.println("vertex [" + v.x + ". " + v.y + ". " + v.z +  " ]" );

			}
			
			if (obj.hasBackgroundImage()) {
				obj.getBackgroundImage().bind();
			}
			
			
			
			System.out.println("uiGrapphicElement wight" + this + ". coords:  width [" + uiGrapphicElement.position.x  + "]" );
			System.out.println("uiGrapphicElement height" + this + ". coords:  height [" + uiGrapphicElement.position.y  + "]" );


			glDrawElements(GL_TRIANGLES, uiGrapphicElement.getMesh().getVertexCount(), GL_UNSIGNED_INT, 0);
			
		}
		
	    shaderProgram.unbind();


		
	}
	

	@Override
	public void cleanup() {
		shaderProgram.cleanup();			
	}



}
