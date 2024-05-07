package game.graphics.gui.controller;

import com.xamlo.core.engine.graphics.api.gui.IUIController;
import com.xamlo.core.engine.graphics.api.gui.elements.IWidget;

public abstract class AbstractUIController implements IUIController {

	IWidget widget;
	
	@Override
	public void setupUI(IWidget widget) {
		this.widget = widget;
	}

}
