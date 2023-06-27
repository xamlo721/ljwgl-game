package com.xamlo.core.engine.graphics.components;

import java.util.ArrayList;

import com.xamlo.core.engine.graphics.api.components.IKeyboard;

public abstract class AbstractKeyboard implements IKeyboard {

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
