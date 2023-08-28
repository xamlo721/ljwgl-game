package com.xamlo.core.engine.graphics.devices;


import java.util.ArrayList;

import org.joml.Vector2f;

import com.xamlo.core.engine.graphics.api.devices.IMouse;

public abstract class AbstractMouse implements IMouse {

	protected ArrayList<Integer> pushedButtons = new ArrayList<Integer>();
	protected ArrayList<Integer> buttonsHolding = new ArrayList<Integer>();
	protected ArrayList<Integer> releasedButtons = new ArrayList<Integer>();

	protected Vector2f cursorPosition;
	protected Vector2f lockedCursorPosition;
	protected float scrollOffset;
	protected boolean showCursor;
	
	@Override
	public boolean isShowCursor() {
		return showCursor;
	}
	
	@Override
	public boolean isButtonPushed(int key) {
		return pushedButtons.contains(key);
	}

	@Override
	public boolean isButtonReleased(int key) {
		return releasedButtons.contains(key);
	}

	@Override
	public boolean isButtonHolding(int key) {
		return buttonsHolding.contains(key);
	}
	
	@Override
	public ArrayList<Integer> getButtonsHolding() {
		return buttonsHolding;
	}

	@Override
	public ArrayList<Integer> getPushedButtons() {
		return pushedButtons;
	}

	@Override
	public Vector2f getCursorPosition() {
		return cursorPosition;
	}



	@Override
	public Vector2f getLockedCursorPosition() {
		return lockedCursorPosition;
	}

	@Override
	public void setLockedCursorPosition(Vector2f lockedCursorPosition) {
		this.lockedCursorPosition = lockedCursorPosition;
	}

	@Override
	public float getScrollOffset() {
		return scrollOffset;
	}

	@Override
	public void setScrollOffset(float scrollOffset) {
		this.scrollOffset = scrollOffset;
	}

}
