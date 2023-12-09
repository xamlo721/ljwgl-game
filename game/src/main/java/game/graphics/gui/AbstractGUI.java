package game.graphics.gui;

import java.util.List;

import com.xamlo.core.engine.graphics.api.gui.IWidget;

public abstract class AbstractGUI implements IGraphicUserInterface {

    protected int guiID;
    protected List<IWidget> guiElements;

	abstract void addWidget(IWidget widget);
	
	abstract void removeWidget(IWidget widget);
	
	@Override
	public int getGUIID() {
		return this.guiID;
	}
	
	@Override
	public List<IWidget> getGuiElements() {
		return guiElements;
	}

	
}
	