package com.xamlo.core.engine.graphics.threads;

import com.xamlo.core.engine.graphics.RenderEngine;

/**
 * Я думаю, что я немного ошибся, позволив потоку управлять RenderEngine
 * На самом деле он должен быть частью RenderEngine, а управление
 * должно происходить как и положено прямыми вызовами RednerEngine.init etc
 * Попробую переписать, когда будет понятно какие обязанности на себя берёт engine
 * @author Satomi
 */
public class RenderThread extends Thread {
	
    private boolean isInit = false;
    private boolean isWindowed = false;

    
    private boolean isRenderStarted = false;
	private RenderEngine renderingEngine;

    public RenderThread(RenderEngine renderingEngine) {
        this.renderingEngine = renderingEngine;
    }

    public void run() {

    	while (true) {
    	
	    	if (!this.isInit) {
	    		renderingEngine.init();
	    		this.isInit = true;
	    	}
	    	
	    	if (!this.isWindowed) {
	    		//FIXME: Настройки здесь не должны находиться 100%
	    		renderingEngine.createWindow(1920/2, 1080/2);
	    		this.isWindowed = true;
	    	}
    	
	    	if (isRenderStarted) {
	    		renderingEngine.startRender();
	    	}
	    	
    	}
    	
    }
    
    public void startRender() {
    	this.isRenderStarted = true;
    }
    
    public void stopRender() {
    	this.isRenderStarted = false;
    }
}