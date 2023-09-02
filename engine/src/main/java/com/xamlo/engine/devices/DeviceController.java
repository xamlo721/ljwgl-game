package com.xamlo.engine.devices;

import com.xamlo.core.engine.graphics.api.devices.IKeyboard;
import com.xamlo.core.engine.graphics.api.devices.IMouse;
import com.xamlo.core.engine.graphics.api.devices.IWindow;
import com.xamlo.engine.api.ITickeable;

/**
 * Смысл этого класса в том, чтобы потоком логики движка заходить в классы,
 * повязанные на графике и справшивать у них актуальные данные для обработки
 * 
 * Положение мыши, нажатые клавиши, положение экрана и хочет ли оно закрыться
 * @author Satomi
 *
 */
public class DeviceController implements ITickeable {

	private IKeyboard keyboard;
	private IMouse mouse;
	private IWindow window;
	
	public DeviceController(IKeyboard keyboard, IMouse mouse, IWindow window) {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onTick() {
		// TODO Auto-generated method stub
		
	}

	
	
}
