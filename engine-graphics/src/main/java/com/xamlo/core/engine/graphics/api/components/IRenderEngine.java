package com.xamlo.core.engine.graphics.api.components;

public interface IRenderEngine {
	
	public void init();
	
	public void createWindow(int width, int height);
	
	public void start();
	
	public void stop();
	
	public void release();

}
	