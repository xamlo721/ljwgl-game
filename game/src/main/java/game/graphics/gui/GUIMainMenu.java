package game.graphics.gui;

import java.net.URI;
import java.util.ArrayList;

import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;
import com.xamlo.core.engine.graphics.components.AbstractTexture;
import com.xamlo.core.engine.graphics.components.gui.Color;
import com.xamlo.core.engine.graphics.components.gui.Widget;
import com.xamlo.core.engine.graphics.components.gui.WidgetGeometry;
import com.xamlo.engine.api.resources.ITextureResource;
import com.xamlo.engine.api.resources.ResourceLoader;

public class GUIMainMenu extends AbstractGuiScene {
	
	private Widget menuBg;
    private ITextureResource<String> backgroundImage;

	private final ResourceLoader<String> resourceLoader;

	public GUIMainMenu(ResourceLoader<String> resourceLoader) {
		this.widgets = new ArrayList<AbstractRenderableObject>();
		this.resourceLoader = resourceLoader;

		menuBg = new Widget();

	}	

	@Override
	public void load() {
		backgroundImage = resourceLoader.loadTexture("texture.gui.mainmenu.background");
		//backgroundImage.bind();
		
		menuBg.setBackgroundColor(new Color(255, 0, 0));
		menuBg.setBorder(0);
		menuBg.setBackgroundImage((AbstractTexture)backgroundImage);
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
