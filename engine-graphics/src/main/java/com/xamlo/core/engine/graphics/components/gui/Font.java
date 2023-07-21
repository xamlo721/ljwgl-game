package com.xamlo.core.engine.graphics.components.gui;

public class Font {
	
    private String fontFamily;
    private int fontSize;
    private boolean bold;
    private boolean italic;

    public Font(String fontFamily, int fontSize, boolean bold, boolean italic) {
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
        this.bold = bold;
        this.italic = italic;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public boolean isBold() {
        return bold;
    }

    public boolean isItalic() {
        return italic;
    }
    
}