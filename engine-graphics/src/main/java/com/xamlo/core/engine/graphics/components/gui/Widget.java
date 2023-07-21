package com.xamlo.core.engine.graphics.components.gui;

import java.util.ArrayList;
import java.util.List;

import com.xamlo.core.engine.graphics.api.gui.IWidget;
import com.xamlo.engine.api.resources.ITextureResource;

public class Widget implements IWidget {
	
	protected WidgetGeometry geometry;
	protected IWidget parent;
	protected List<IWidget> childWidgets;
	protected boolean visible;
	protected boolean isEnable;
	protected boolean focusable;
	protected boolean hasBackgroundImage; //TODO: Это Variant-ом чтоли делают? почините кто знает
	protected ITextureResource backgroundImage;
	protected Color backgroundColor;
	protected Font font;
	protected String toolTipText;
	protected Border border;
	protected String widgetName;
	
	public Widget() {
		
		this.geometry = new WidgetGeometry(0, 0, 0, 0);
		this.parent = null;
		this.childWidgets = new ArrayList<IWidget>();
		this.visible = true;
		this.isEnable = true;
		this.focusable = false;
		this.hasBackgroundImage = false;
		this.backgroundColor = new Color(255, 255, 255);
		this.font = new Font("Default", 12, false, false);
		this.toolTipText = "";
		this.border = new Border(4, new Color(128, 128, 128));
		
	}
	
	@Override
	public void setParent(IWidget parent) {
		// TODO Auto-generated method stub
		this.parent = parent;
	}

	@Override
	public IWidget getParent() {
		return this.parent;
	}
	
	@Override
	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}

	@Override
	public String getWidgetName() {
		return this.widgetName;
	}
	
	@Override
	public WidgetGeometry getWidgetGeometry() {
		return geometry;
	}

	@Override
	public WidgetSize getWidSize() {
		return geometry;
	}

	@Override
	public void resize(WidgetGeometry geometry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(WidgetSize size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		this.visible = false;
	}

	@Override
	public void show() {
		this.visible = true;
	}

	@Override
	public void setVisible(boolean visible) {
		if(visible) {
			show();
		} else {
			hide();
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IWidget> getChildElements() {
		return this.childWidgets;
	}

	@Override
	public boolean hasBackgroundImage() {
		return this.hasBackgroundImage;
	}
	
	@Override
	public void setBackgroundImage(ITextureResource image) {
		this.backgroundImage = image;
	}

	@Override
	public ITextureResource getBackgroundImage() {
		if (!this.hasBackgroundImage) {
			return null;
		}
		return this.backgroundImage;
	}

	@Override
	public void setPosition(int x, int y) {
		this.geometry = new WidgetGeometry(x, y, this.geometry.width, this.geometry.height);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.isEnable = enabled;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnable;
	}

	@Override
	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
	}

	@Override
	public Color getBackgroundColor() {
		return this.backgroundColor;
	}

	@Override
	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public Font getFont() {
		return this.font;
	}

	@Override
	public void setToolTipText(String tooltip) {
		this.toolTipText = tooltip;
	}

	@Override
	public String getToolTipText() {
		return this.toolTipText;
	}

	@Override
	public void setBorder(Border border) {
		this.border = border;
	}

	@Override
	public Border getBorder() {
		return this.border;
	}

	@Override
	public void setFocusable(boolean focusable) {
		this.focusable = focusable;
	}

	@Override
	public boolean isFocusable() {
		return this.focusable;
	}



}
