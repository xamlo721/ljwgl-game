package com.xamlo.engine.api.devices;

import java.util.ArrayList;

public interface IKeyboard {

	public boolean isKeyPushed(EnumKeyboardButtons key);
	
	public boolean isKeyReleased(EnumKeyboardButtons key);
	
	public boolean isKeyHold(EnumKeyboardButtons key);

	public ArrayList<EnumKeyboardButtons> getPushedKeys();

	public ArrayList<EnumKeyboardButtons> getKeysHolding();
	
}
