package org.lwjglb.engine.graph;

import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.primitives.VertexArrayObject;

public class Mesh {
	
	///Количество вершин в Меше
    private int numVertices;
    
    //Объект, хранящий в себе копию памяти VRAM
    //И правильные способы работы с ней
    private VertexArrayObject vao;
    
    public Mesh(Vertex[] vertices) {
    	vao = new VertexArrayObject(vertices);
    	vao.allocMemory();
        this.numVertices = vertices.length;
    }
    
    public Mesh(Vertex[] vertices, int[] indices) {
    	vao = new VertexArrayObject(vertices, indices);
    	vao.allocMemory();
        this.numVertices = indices.length;
    }
    
    public void bind() {
    	vao.bind();
    }

    
    public void unbind() {
    	vao.unbind();
    }

    public void cleanup() {
        vao.releaseMemory();
    }

    public int getNumVertices() {
        return numVertices;
    }

}
