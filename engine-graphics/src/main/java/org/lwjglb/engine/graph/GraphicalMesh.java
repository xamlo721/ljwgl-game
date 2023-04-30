package org.lwjglb.engine.graph;

import com.xamlo.core.engine.graphics.components.Texture;//FIXME: Я уверен, что это не должно здесь находится

import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.primitives.VertexArrayObject;

import ru.satomi.dc.primitive.PhysicalMesh;

public class GraphicalMesh extends PhysicalMesh {
	

    //Объект, хранящий в себе копию памяти VRAM
    //И правильные способы работы с ней
    private VertexArrayObject vao;
    
    public GraphicalMesh(Vertex[] vertices) {
    	super(vertices.length);
    	vao = new VertexArrayObject(vertices);
    	vao.allocMemory(0);
    }
    
    public GraphicalMesh(Vertex[] vertices, int[] indices) {
    	super(vertices.length);
    	vao = new VertexArrayObject(vertices, indices);
    	vao.allocMemory(0);
        this.verticesCount = indices.length;
    }
    
    public GraphicalMesh(Vertex[] vertices, int[] indices, Texture texture) {
    	super(vertices.length);
    	vao = new VertexArrayObject(vertices, indices, texture);
    	vao.allocMemory(0);
        this.verticesCount = indices.length;
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
        return this.verticesCount;
    }

}
