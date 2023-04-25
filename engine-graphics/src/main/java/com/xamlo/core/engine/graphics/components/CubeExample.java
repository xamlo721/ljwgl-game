package com.xamlo.core.engine.graphics.components;

import org.lwjglb.engine.graph.Mesh;

import com.xamlo.core.engine.graphics.primitives.Vertex;

import ru.satomi.dc.primitive.Vec3f;

public class CubeExample extends AbstractRenderableObject {

	public CubeExample() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void loadMesh() {

    	Vertex[] vertices = new Vertex[8];
    	int i = 0;
    	vertices[i++] = new Vertex(new Vec3f(-0.5f,  0.5f, -1.0f), new Vec3f(-0.1f,  0.1f, 1.0f)); //V1
    	vertices[i++] = new Vertex(new Vec3f(-0.5f, -0.5f, -1.0f), new Vec3f(-0.5f,  0.0f, 0.0f)); //V2
    	vertices[i++] = new Vertex(new Vec3f( 0.5f, -0.5f, -1.0f), new Vec3f(-1.0f,  0.0f, 1.0f)); //V3
    	vertices[i++] = new Vertex(new Vec3f( 0.5f,  0.5f, -1.0f), new Vec3f(-1.0f,  1.0f, 1.0f)); //V4
    	vertices[i++] = new Vertex(new Vec3f(-0.5f,  0.5f, -2.0f), new Vec3f(-0.1f,  0.1f, 1.0f)); //V1-1
    	vertices[i++] = new Vertex(new Vec3f(-0.5f, -0.5f, -2.0f), new Vec3f(-0.5f,  0.0f, 0.0f)); //V2-1
    	vertices[i++] = new Vertex(new Vec3f( 0.5f, -0.5f, -2.0f), new Vec3f(-1.0f,  0.0f, 1.0f)); //V3-1
    	vertices[i++] = new Vertex(new Vec3f( 0.5f,  0.5f, -2.0f), new Vec3f(-1.0f,  1.0f, 1.0f)); //V4-1

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
        mesh = new Mesh(vertices, indices);
        
	}

	@Override
	public void init() {
		this.loadMesh();
	}

	@Override
	public void release() {
		this.mesh.cleanup();
	}

}
