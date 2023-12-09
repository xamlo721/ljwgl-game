package com.xamlo.engine.threads;

import com.xamlo.engine.devices.DeviceController;

/**
 * Поток, занимающийся опросом девайс контроллера N раз в секунду,
 * не зависимо от FPS и TPS движка
 * @author Satomi
 *
 */
public class DeviceControlThread extends Thread {

	private static final long NANOSECOND = 1000000000;
	private final DeviceController deviceController;

	public DeviceControlThread(DeviceController deviceController) {
		this.deviceController = deviceController;
	}

	
    @Override
    public void run() {

        
		//****************Пресет***************//
		//Время последнего тика
		long lastTime = System.nanoTime();
		//Прошедшее время с начала цикла
		double idleTime = 0;
		//Лимит тиков
		float tickrate = 20;
		//Счётчик времени по которому мы будем мерить 1 секунду
		int secondsForTickCounter = 0;
		//Лимит времени на обработку 1 тика
		float tickTime = 1.0f/tickrate;
		//************************************//


		while (deviceController.isAlive()) {
			
			boolean isProcessing = false;
						
			//Количество циклов сейчас
			long currentTime = System.nanoTime();

			//прибавим к времени ожидания с прошлого кадра время которое проспал цикл
			idleTime += (currentTime - lastTime) / (double) NANOSECOND;
			
			//Разница в секундах между прошлым замером fps и текущим временем
			int currentDelta = (int) ((currentTime / (double) NANOSECOND) - secondsForTickCounter);
			
			//Если прошла секунда, отмерить ФПС и сбросиь счётчики
			if (currentDelta > 0) {
				secondsForTickCounter +=currentDelta;
			}
			
			//Если с времени последнего кадра прошло времени больше чем время кадра, то пора рисовать следующий
			if (idleTime > tickTime) {
				isProcessing = true;
				idleTime -= tickTime;
				lastTime = currentTime;
			}
						
			if(isProcessing) {
				deviceController.onTick();
			} else {
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}

    }
    
}