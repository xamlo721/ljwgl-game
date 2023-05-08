package com.xamlo.core.engine.graphics.primitives;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.system.MemoryUtil;

import com.xamlo.core.engine.graphics.api.primitives.IVertexAttribute;

/**
 * Класс Vertex Описывает продвинутый случай 
 * расположения в VRAM информации о точке.
 * 
 * Вершина может содержать сколько угодно 
 * атррибутов любого типа.
 * @author Satomi
 *
 */
public class Vertex {
	
	private List<IVertexAttribute> attributes;
	
	public Vertex() {
		this.attributes = new ArrayList<IVertexAttribute>();
	}

    public Vertex(List<IVertexAttribute> attributes) {
        this.attributes = attributes;
    }

    public Vertex addAttribute(IVertexAttribute attrib) {
    	this.attributes.add(attrib);
		return this;
    }
    
    public int getVertexStride() {
    	int stride = 0;
    	for (IVertexAttribute iVertexAttribute : attributes) {
			stride += iVertexAttribute.getOffset();
		}
		return stride;
    }
    
    public int getVertexSize() {
    	int stride = 0;
    	for (IVertexAttribute iVertexAttribute : attributes) {
			stride += iVertexAttribute.getDimensionSize();
		}
		return stride;
    }
    
    public List<IVertexAttribute> getAttributes() {
        return attributes;
    }
    
    public FloatBuffer getVertexBuffer() {
	    FloatBuffer attribBuffer = MemoryUtil.memAllocFloat(getVertexStride());
    	for (IVertexAttribute iVertexAttribute : attributes) {
    	    attribBuffer.put(iVertexAttribute.getVertexData());
    	    String logmsg = "VertexAttribute { ";
    	    
    	    for (float f : iVertexAttribute.getVertexData()) {
    	    	logmsg += f + ", ";
    	    }
    	    
    	    logmsg += " };";
    	    System.out.println(logmsg);
		}
		return attribBuffer.flip();
    	
    }
	
}
