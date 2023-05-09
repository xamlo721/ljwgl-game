package org.lwjglb.engine.graph;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryUtil;

import com.xamlo.core.engine.graphics.api.primitives.IVertex;
import com.xamlo.core.engine.graphics.api.primitives.IVertexStructure;
import com.xamlo.core.engine.graphics.components.Texture;//FIXME: Я уверен, что это не должно здесь находится
import com.xamlo.core.engine.graphics.primitives.EnumMemoryType;
import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.primitives.VertexArrayObject;
import com.xamlo.core.engine.graphics.primitives.VertexBufferObject;
import com.xamlo.core.engine.graphics.primitives.VertexStructure;

import ru.satomi.dc.primitive.PhysicalMesh;

public class GraphicalMesh extends PhysicalMesh {
	

    //Объект, хранящий в себе копию памяти VRAM
    //И правильные способы работы с ней
    private VertexArrayObject vao;
    
    public GraphicalMesh(Vertex[] vertices) {
    	super(vertices.length);
//    	vao = new VertexArrayObject(vertices);
//    	vao.allocMemory(0);
    }
    
    public GraphicalMesh(IVertex[] vertices, IVertexStructure structure, int[] indices) {
    	super(vertices.length);

		System.out.println("Register Mesh VertexCount: " + structure.getVertexCount() + ". VertexSize " + structure.getVertexSize());
		
	    FloatBuffer vertexData = MemoryUtil.memAllocFloat(structure.getVertexCount() * structure.getVertexSize());
	    for (IVertex v : vertices) {
		    vertexData.put(v.getVertexData());
		    v.release();
	    }
		vertexData.flip();

    	vao = new VertexArrayObject();
    	vao.addVertexData(new VertexBufferObject(vertexData, structure, EnumMemoryType.STATIC));
    	vao.setVertexOrder(indices);
    	vao.allocMemory(0);
		/**
		 * Чистим за собой память. Она НЕ почистится gc, так как расположена не на стеке
		 */
	    MemoryUtil.memFree(vertexData);
    	this.verticesCount = indices.length;
    }
    
    public GraphicalMesh(Vertex[] vertices, VertexStructure structure,  int[] indices, Texture texture) {
    	this(vertices, structure, indices);
//    	vao = new VertexArrayObject(vertices, indices, texture);
//    	vao.allocMemory(0);
//        this.verticesCount = indices.length;
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
