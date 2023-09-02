package com.xamlo.engine.api.devices;

public enum EnumMouseButtons {
	
	MOUSE_BUTTON_UNCKNOWN(-1, "MOUSE_BUTTON_UNCKNOWN"),
	
    MOUSE_BUTTON_1(0, "MOUSE_BUTTON_1"),
    
    MOUSE_BUTTON_2(1, "MOUSE_BUTTON_2"),
    
    MOUSE_BUTTON_3(2, "MOUSE_BUTTON_3"),
    
    MOUSE_BUTTON_4(3, "MOUSE_BUTTON_4"),
    
    MOUSE_BUTTON_5(4, "MOUSE_BUTTON_5"),
    
    MOUSE_BUTTON_6(5, "MOUSE_BUTTON_6"),
    
    MOUSE_BUTTON_7(6, "MOUSE_BUTTON_7"),
    
    MOUSE_BUTTON_8(7, "MOUSE_BUTTON_8");

	public final int keyID;
	public final String name;
	
	private EnumMouseButtons(int keyID, String name) {
		this.keyID = keyID;
		this.name = name;
	}
	
}
