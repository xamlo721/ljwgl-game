package com.xamlo.core.engine.graphics.primitives;

public interface IBufferObject {

	public boolean allocMemory();
	
	public void bind();
	
	public void unbind();
	
	public boolean releaseMemory();
}
