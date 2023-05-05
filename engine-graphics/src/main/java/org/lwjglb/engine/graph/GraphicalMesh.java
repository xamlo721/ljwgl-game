package org.lwjglb.engine.graph;

import com.xamlo.core.engine.graphics.components.Texture;//FIXME: Я уверен, что это не должно здесь находится

import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.primitives.VertexArrayObject;
import com.xamlo.core.engine.graphics.primitives.VertexBufferObject.EnumMemoryType;

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
    	
		float[] coordsData = new float[vertices.length * 3]; //FIXME: Magic number
		float[] normalesData= new float[vertices.length * 3]; //FIXME: Magic number
		float[] colorsData = new float[vertices.length * 3]; //FIXME: Magic number
		float[] textureData = new float[vertices.length * 2]; //FIXME: Magic number

		for (int i = 0; i < vertices.length; i++) {
			Vertex v  = vertices[i];
			coordsData[i*3 + 0] = v.getPos().x; //FIXME: Magic number
			coordsData[i*3 + 1] = v.getPos().y; //FIXME: Magic number
			coordsData[i*3 + 2] = v.getPos().z; //FIXME: Magic number
			
			normalesData[i*3 + 0] = v.getNormal().x; //FIXME: Magic number
			normalesData[i*3 + 1] = v.getNormal().y; //FIXME: Magic number
			normalesData[i*3 + 2] = v.getNormal().z; //FIXME: Magic number
			
			colorsData[i*3 + 0] = v.getColor().x; //FIXME: Magic number
			colorsData[i*3 + 1] = v.getColor().y; //FIXME: Magic number
			colorsData[i*3 + 2] = v.getColor().z; //FIXME: Magic number
			
			textureData[i*2 + 0] = v.getTextureCoord().x; //FIXME: Magic number
			textureData[i*2 + 1] = v.getTextureCoord().y; //FIXME: Magic number
		}
		
    	vao = new VertexArrayObject();
    	vao.addVertexData(0, coordsData, EnumMemoryType.STATIC, 3);
    	//vao.addVertexData("colors", colorsData, EnumMemoryType.STATIC, 3);
    	//vao.addVertexData("normales", normalesData, EnumMemoryType.STATIC, 3);
    	vao.addVertexData(1, textureData, EnumMemoryType.STATIC, 2);
    			
    	vao.setVertexOrder(indices);
    	vao.allocMemory(0);
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
