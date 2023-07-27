package game.graphics.gui;

import java.net.URI;
import java.util.ArrayList;

import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;
import com.xamlo.core.engine.graphics.components.Texture;
import com.xamlo.core.engine.graphics.components.gui.Color;
import com.xamlo.core.engine.graphics.components.gui.Widget;
import com.xamlo.core.engine.graphics.components.gui.WidgetGeometry;
import com.xamlo.engine.resource.SimpleResourceLoader;

public class GUIMainMenu extends AbstractGuiScene {
	
	private Widget menuBg;
    private Texture backgroundImage;

	public GUIMainMenu() {
		this.widgets = new ArrayList<AbstractRenderableObject>();

		menuBg = new Widget();

	}	

	@Override
	public void load() {
		backgroundImage = SimpleResourceLoader.getInstance().loadTexture(URI.create("resource:///textures/gui/mainmenu/background.png"));
		backgroundImage.bind();
		
		menuBg.setBackgroundColor(new Color(255, 0, 0));
		menuBg.setBorder(0);
		menuBg.setBackgroundImage(backgroundImage);
		menuBg.resize(new WidgetGeometry(0, 0, 1920, 1080));
		menuBg.loadMesh();
		
		this.addWidget(menuBg);
	}



	@Override
	void addWidget(Widget widget) {
		this.widgets.add(widget);
	}



	@Override
	void removeWidget(Widget widget) {
		this.widgets.remove(widget);
	}
	

	@Override
	public void release() {
		for (AbstractRenderableObject obj : this.widgets) {
			obj.release();
		}		
	}

}
