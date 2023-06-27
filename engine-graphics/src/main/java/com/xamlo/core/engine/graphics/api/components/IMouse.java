package com.xamlo.core.engine.graphics.api.components;

import java.util.ArrayList;

import org.joml.Vector2f;

public interface IMouse {
	
	public void update();
	
	boolean isShowCursor();

	public boolean isButtonPushed(int key);
	
	public boolean isButtonReleased(int key);
	
	public boolean isButtonHolding(int key);
	
	public Vector2f getCursorPosition();

	public void setCursorPosition(Vector2f cursorPosition);

	public Vector2f getLockedCursorPosition();

	public void setLockedCursorPosition(Vector2f lockedCursorPosition);

	public float getScrollOffset();

	public void setScrollOffset(float scrollOffset);

	public ArrayList<Integer> getButtonsHolding();

	public ArrayList<Integer> getPushedButtons();


}
