package org.lwjglb.engine.graph;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.system.MemoryUtil;

import com.xamlo.core.engine.graphics.api.primitives.IVertexAttribute;
import com.xamlo.core.engine.graphics.components.Texture;//FIXME: Я уверен, что это не должно здесь находится
import com.xamlo.core.engine.graphics.primitives.EnumMemoryType;
import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.primitives.VertexArrayObject;
import com.xamlo.core.engine.graphics.primitives.VertexAttributeDataOrder;
import com.xamlo.core.engine.graphics.primitives.VertexBufferObject;

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
    
    public GraphicalMesh(Vertex[] vertices, int[] indices) {
    	super(vertices.length);
    	
    	if (vertices.length == 0) {
    		//TODO: Охх я вам как дааам!
    		return;
    	}
    	
	    FloatBuffer vertexBuffer = MemoryUtil.memAllocFloat(vertices.length * vertices[0].getVertexStride());

		for (int i = 0; i < vertices.length; i++) {
			Vertex v = vertices[i];
			vertexBuffer.put(v.getVertexBuffer());
		}
		VertexBufferObject vbo = new VertexBufferObject(vertexBuffer, EnumMemoryType.STATIC, 3);
		
		List<VertexAttributeDataOrder> dataOrders = new ArrayList<VertexAttributeDataOrder>(); 
		
		Vertex v = vertices[0];
		
		int i = 1;
		for (IVertexAttribute attr : v.getAttributes()) {
			VertexAttributeDataOrder order = new VertexAttributeDataOrder(i++, attr.getSize(), attr.getType(), attr.isNormalized(), attr.getStride());
			dataOrders.add(order);
		}

		vbo.setDataStricture(dataOrders);
		
    	vao = new VertexArrayObject();
    	vao.addVertexData(vbo);
    	vao.setVertexOrder(indices);
    	vao.allocMemory(0);
    	
	    MemoryUtil.memFree(vertexBuffer);
	    
    	this.verticesCount = indices.length;
    }
    
    public GraphicalMesh(Vertex[] vertices, int[] indices, Texture texture) {
    	this(vertices, indices);
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
