package com.xamlo.engine.api.devices;

import java.util.ArrayList;

import org.joml.Vector2f;

public interface IMouse {
	
	public void update();
	
	boolean isShowCursor();

	public boolean isButtonPushed(EnumMouseButtons key);
	
	public boolean isButtonReleased(EnumMouseButtons key);
	
	public boolean isButtonHolding(EnumMouseButtons key);
	
	public Vector2f getCursorPosition();

	public void setCursorPosition(Vector2f cursorPosition);

	public Vector2f getLockedCursorPosition();

	public void setLockedCursorPosition(Vector2f lockedCursorPosition);

	public float getScrollOffset();

	public void setScrollOffset(float scrollOffset);

	public ArrayList<EnumMouseButtons> getButtonsHolding();

	public ArrayList<EnumMouseButtons> getPushedButtons();


}
