package com.xamlo.engine.devices;

import java.util.ArrayList;

import com.xamlo.core.engine.graphics.api.devices.IWindow;
import com.xamlo.engine.api.ITickeable;
import com.xamlo.engine.api.devices.EnumKeyboardButtons;
import com.xamlo.engine.api.devices.IKeyboard;
import com.xamlo.engine.api.devices.IMouse;

/**
 * Смысл этого класса в том, чтобы потоком логики движка заходить в классы,
 * повязанные на графике и справшивать у них актуальные данные для обработки
 * 
 * Положение мыши, нажатые клавиши, положение экрана и хочет ли оно закрыться
 * @author Satomi
 *
 */
public class DeviceController implements ITickeable {

	protected IKeyboard keyboard;
	protected IMouse mouse;
	protected IWindow window;
	
	private boolean isStopped;
	
	public DeviceController(IKeyboard keyboard, IMouse mouse, IWindow window) {
		this.isStopped = false;
	}
	
	@Override
	public void onTick() {
		
		//Получить клавиши, нажатые в текущем цикле
		ArrayList<EnumKeyboardButtons> newKeys = this.keyboard.getPushedKeys();
		
		//FIXME: Так как фпс может отставать, то мы можем получить два события на одно нажатие,
		//FIXME: если поток DeviceControllThread успеет сделать 2 тика, а RenderThread отрисует 
		//FIXME: только 1 кадр, что более чем реально
		
		//Получить клавиши, находящиеся в опущеном положении
		ArrayList<EnumKeyboardButtons> oldKeys = this.keyboard.getKeysHolding();
		
		
		//TODO: Добавить возможность регать слушателей с указанием комбинаций
		
		//TODO: Вызвать всех слушателей по итогу кнопок
		
	}
	
	public boolean isAlive() {
		return !this.isStopped;
	}

	
	
}
