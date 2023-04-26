package com.xamlo.core.engine.graphics.components;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;

import org.joml.Vector3f;
import org.lwjglb.engine.graph.GraphicalMesh;

import com.xamlo.core.engine.graphics.primitives.Vertex;

public class CubeExample extends AbstractRenderableObject {

	/**
	 * Сетка точек, описывающая объект
	 */
	protected static GraphicalMesh mesh;
	
	public CubeExample() {
		super();
	}
	
	@Override
	public void loadMesh() {

    	Vertex[] vertices = new Vertex[8];
    	int i = 0;
    	vertices[i++] = new Vertex(new Vector3f(-0.5f,  0.5f, 1.0f), new Vector3f(0.1f,  0.1f, 1.0f)); //V1
    	vertices[i++] = new Vertex(new Vector3f(-0.5f, -0.5f, 1.0f), new Vector3f(0.5f,  0.0f, 1.0f)); //V2
    	vertices[i++] = new Vertex(new Vector3f( 0.5f, -0.5f, 1.0f), new Vector3f(1.0f,  0.0f, 1.0f)); //V3
    	vertices[i++] = new Vertex(new Vector3f( 0.5f,  0.5f, 1.0f), new Vector3f(1.0f,  1.0f, 1.0f)); //V4
    	vertices[i++] = new Vertex(new Vector3f(-0.5f,  0.5f, -1.0f), new Vector3f(0.1f,  0.1f, 1.0f)); //V1-1
    	vertices[i++] = new Vertex(new Vector3f(-0.5f, -0.5f, -1.0f), new Vector3f(0.5f,  1.0f, 1.0f)); //V2-1
    	vertices[i++] = new Vertex(new Vector3f( 0.5f, -0.5f, -1.0f), new Vector3f(1.0f,  0.0f, 1.0f)); //V3-1
    	vertices[i++] = new Vertex(new Vector3f( 0.5f,  0.5f, -1.0f), new Vector3f(1.0f,  1.0f, 1.0f)); //V4-1

    	i = 0;
    	int[] indices = new int[36]; 
    	//FACE
    	indices[i++] = 0;
    	indices[i++] = 1;
    	indices[i++] = 3;
    	
    	indices[i++] = 3;
    	indices[i++] = 1;
    	indices[i++] = 2;
    	//LEFT
    	indices[i++] = 0;
    	indices[i++] = 1;
    	indices[i++] = 5;
    	
    	indices[i++] = 0;
    	indices[i++] = 4;
    	indices[i++] = 5;
    	//RIGHT
    	indices[i++] = 3;
    	indices[i++] = 2;
    	indices[i++] = 7;
    	
    	indices[i++] = 2;
    	indices[i++] = 6;
    	indices[i++] = 7;
    	//BEHIND
    	indices[i++] = 4;
    	indices[i++] = 5;
    	indices[i++] = 7;
    	
    	indices[i++] = 7;
    	indices[i++] = 5;
    	indices[i++] = 6;
    	///TOP
    	indices[i++] = 0;
    	indices[i++] = 3;
    	indices[i++] = 4;
    	
    	indices[i++] = 3;
    	indices[i++] = 7;
    	indices[i++] = 4;
    	///BUTTOM
    	indices[i++] = 1;
    	indices[i++] = 5;
    	indices[i++] = 6;
    	
    	indices[i++] = 1;
    	indices[i++] = 2;
    	indices[i++] = 6;
        mesh = new GraphicalMesh(vertices, indices);
        
	}
	
	@Override
	public GraphicalMesh getMesh() {
		return this.mesh;
	}
	
	@Override
	public void init() {
		if (mesh == null) {
			this.loadMesh();
		}

	}
	
	@Override
	public void draw() {
		this.mesh.bind();
		
	    // Draw the vertices
	    //glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());
		

		/**
		 * mode: Задает примитивы для рендеринга, в данном случае треугольники. Здесь никаких изменений.
		 * count: Указывает количество элементов, которые должны быть отрисованы.
		 * type: Указывает тип значения в данных индексов. В данном случае мы используем целые числа.
		 * indices: Задает смещение, которое необходимо применить к данным индексов для начала рендеринга.
		 */
		glDrawElements(GL_TRIANGLES, this.mesh.getNumVertices(), GL_UNSIGNED_INT, 0);

		
	}
	@Override
	public void release() {
		this.mesh.cleanup();
	}

}
