package org.lwjglb.engine.graph;

import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.primitives.VertexArrayObject;

import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.*;

public class Mesh {
	

	///Количество вершин в Меше
    private int numVertices;
    
    //Объект, хранящий в себе копию памяти VRAM
    //И правильные способы работы с ней
    VertexArrayObject vao;
    
    public Mesh(Vertex[] vertices) {
    	
    	vao = new VertexArrayObject(vertices);
    	vao.allocMemory();
        this.numVertices = vertices.length;
    }
    
    public void use() {
	    glBindVertexArray(vao.vaoId);
	    glEnableVertexAttribArray(0);

    }

    public void cleanup() {
        vao.releaseMemory();
    }

    public int getNumVertices() {
        return numVertices;
    }

}
