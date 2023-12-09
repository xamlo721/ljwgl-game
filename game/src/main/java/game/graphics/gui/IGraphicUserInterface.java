package game.graphics.gui;

import java.util.List;

import com.xamlo.core.engine.graphics.api.gui.IWidget;

public interface IGraphicUserInterface {

	public int getGUIID();

	public List<IWidget> getGuiElements();

}
