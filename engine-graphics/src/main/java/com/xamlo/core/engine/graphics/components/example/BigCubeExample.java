package com.xamlo.core.engine.graphics.components.example;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;

import java.awt.Point;

import org.joml.Vector3d;
import org.joml.Vector3f;
import org.lwjglb.engine.graph.GraphicalMesh;

import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;
import com.xamlo.core.engine.graphics.components.example.DualContouring;

import ru.satomi.dc.primitive.Face3i;
import ru.satomi.dc.primitive.QuadMesh;
import ru.satomi.dc.primitive.TriandgleMesh;

public class BigCubeExample extends AbstractRenderableObject {
	
	/**
	 * Сетка точек, описывающая объект
	 */
	protected static GraphicalMesh mesh;
	
	public BigCubeExample() {
		super();
	}
	
	@Override
	public void init() {
		if (mesh == null) {
			this.loadMesh();
		}		
	}
	
	@Override
	public void loadMesh() {
		
		// create the test object
		Model model = new Model();
		
		DualContouring dc = new DualContouring(16, 16, 16, new Vector3d(-2, -2, -2), new Vector3d(12, 12, 12));
		
		double value[][][] = dc.sample(model);
		
		QuadMesh physicMesh = dc.generateQuadMesh(model, value);
		
		TriandgleMesh triangles = dc.generateTriangleMesh(physicMesh);
	
		
    	int i = 0;
    	Vertex[] vertices = new Vertex[triangles.vertexs.size()];
    	int[] indices = new int[triangles.faces.length * 3]; 

    	for (Vector3d point : triangles.vertexs) {
    		vertices[i++] = new Vertex(
    				new Vector3f((float)point.x, (float)point.y, (float)point.z), 
    				new Vector3f((float)Math.random(),  (float)Math.random(), (float)Math.random()));
    	}
    	i = 0;
    	for (Face3i triangle : triangles.faces) {
    		indices[i++] = triangle.vertexIndex1;
    		indices[i++] = triangle.vertexIndex2;
    		indices[i++] = triangle.vertexIndex3;
    	}
		
    	System.out.println("Loading big Cube... " + triangles.faces.length + " poligons loaded.");
        mesh = new GraphicalMesh(vertices, indices);

	}

	@Override
	public void draw() {
		mesh.bind();
		
	    // Draw the vertices
	    //glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());
		

		/**
		 * mode: Задает примитивы для рендеринга, в данном случае треугольники. Здесь никаких изменений.
		 * count: Указывает количество элементов, которые должны быть отрисованы.
		 * type: Указывает тип значения в данных индексов. В данном случае мы используем целые числа.
		 * indices: Задает смещение, которое необходимо применить к данным индексов для начала рендеринга.
		 */
		glDrawElements(GL_TRIANGLES, mesh.getNumVertices(), GL_UNSIGNED_INT, 0);
		
	}

	@Override
	public GraphicalMesh getMesh() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void release() {
		mesh.cleanup();
		
	}
}
