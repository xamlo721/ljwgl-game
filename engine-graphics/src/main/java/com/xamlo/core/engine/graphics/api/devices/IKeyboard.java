package com.xamlo.core.engine.graphics.api.devices;

import java.util.ArrayList;

public interface IKeyboard {

	public void update();

	public boolean isKeyPushed(int key);
	
	public boolean isKeyReleased(int key);
	
	public boolean isKeyHold(int key);

	public ArrayList<Integer> getPushedKeys();

	
	public ArrayList<Integer> getKeysHolding();
	
	
}
