package com.xamlo.core.engine.graphics.api.devices;

import java.util.ArrayList;

import com.xamlo.engine.api.devices.EnumKeyboardButtons;

public interface IKeyboard {

	public boolean isKeyPushed(EnumKeyboardButtons key);
	
	public boolean isKeyReleased(EnumKeyboardButtons key);
	
	public boolean isKeyHold(EnumKeyboardButtons key);

	public ArrayList<EnumKeyboardButtons> getPushedKeys();

	public ArrayList<EnumKeyboardButtons> getKeysHolding();
	
}
