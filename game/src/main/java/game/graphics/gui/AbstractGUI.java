package game.graphics.gui;

import java.util.List;

import com.xamlo.core.engine.graphics.api.gui.IGraphicUserInterface;
import com.xamlo.core.engine.graphics.api.gui.IUIElement;

public abstract class AbstractGUI implements IGraphicUserInterface {

    protected int guiID;
    protected List<IUIElement> guiElements;

	abstract void addElement(IUIElement widget);
	
	abstract void removeElement(IUIElement widget);
	
	@Override
	public int getGUIID() {
		return this.guiID;
	}
	
	@Override
	public List<IUIElement> getGuiElements() {
		return guiElements;
	}
	
}
