package com.xamlo.core.engine.graphics.devices;

import java.util.ArrayList;

import com.xamlo.core.engine.graphics.api.devices.IKeyboard;
import com.xamlo.core.engine.graphics.api.devices.IUpdatableDevice;

public abstract class AbstractKeyboard implements IKeyboard, IUpdatableDevice {

	protected ArrayList<Integer> pushedKeys = new ArrayList<Integer>();
	protected ArrayList<Integer> keysHolding = new ArrayList<Integer>();
	protected ArrayList<Integer> releasedKeys = new ArrayList<Integer>();

	@Override
	public boolean isKeyPushed(int key) {
		return pushedKeys.contains(key);
	}

	@Override
	public boolean isKeyReleased(int key) {
		return releasedKeys.contains(key);
	}

	@Override
	public boolean isKeyHold(int key) {
		return keysHolding.contains(key);
	}

	@Override
	public ArrayList<Integer> getPushedKeys() {
		return pushedKeys;
	}


	@Override
	public ArrayList<Integer> getKeysHolding() {
		return keysHolding;
	}



}
