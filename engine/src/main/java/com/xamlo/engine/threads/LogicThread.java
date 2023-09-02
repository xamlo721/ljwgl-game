package com.xamlo.engine.threads;


public class LogicThread extends Thread {

	private static final long NANOSECOND = 1000000000;

	public LogicThread() {
		// TODO Auto-generated constructor stub
	}

	
    @Override
    public void run() {

//    	
//		renderingEngine.init();
//		//FIXME: Настройки здесь не должны находиться 100%
//		renderingEngine.createWindow(1920, 1080);
//		renderingEngine.loadInputDevices();
//    	renderingEngine.loadScene();	
//	    renderingEngine.start();
//
//        
//		//****************Пресет***************//
//		//Количество просчитанных тиков
//		int tickCounter = 0;
//		//Время последнего тика
//		long lastTime = System.nanoTime();
//		//Прошедшее время с начала цикла
//		double idleTime = 0;
//		//Лимит тиков
//		float tickrate = 20;
//		//Количество тиков за прошлую секунду
//		int ticksPerLastSecond = 0;
//		//Счётчик времени по которому мы будем мерить 1 секунду
//		int secondsForTickCounter = 0;
//		//Лимит времени на обработку 1 тика
//		float tickTime = 1.0f/tickrate;
//		//************************************//
//
//
//		while (renderingEngine.isRendering()) {
//			
//			boolean isProcessing = false;
//						
//			//Количество циклов сейчас
//			long currentTime = System.nanoTime();
//
//			//прибавим к времени ожидания с прошлого кадра время которое проспал цикл
//			idleTime += (currentTime - lastTime) / (double) NANOSECOND;
//			
//			//Разница в секундах между прошлым замером fps и текущим временем
//			int currentDelta = (int) ((currentTime / (double) NANOSECOND) - secondsForTickCounter);
//			
//			//Если прошла секунда, отмерить ФПС и сбросиь счётчики
//			if (currentDelta > 0) {
//				ticksPerLastSecond = tickCounter;
//				tickCounter = 0;
//				secondsForTickCounter +=currentDelta;
//				System.out.println(Thread.currentThread().getName() + " " + idleTime );
//				System.out.println(Thread.currentThread().getName() + " " + ticksPerLastSecond );
//
//			}
//			
//			//Если с времени последнего кадра прошло времени больше чем время кадра, то пора рисовать следующий
//			if (idleTime > tickTime) {
//				isProcessing = true;
//				idleTime -= tickTime;
//				lastTime = currentTime;
//			}
//						
//			if(isProcessing) {
//				renderingEngine.transformScene();
//				renderingEngine.renderFrame();
//				renderingEngine.updateInputDevices();
//				tickCounter++;
//			} else {
//				
//				try {
//					Thread.sleep(10);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}	
//			
//			
//		}
//		
//		renderingEngine.stop();
//		renderingEngine.release();	

    	
    }
}
