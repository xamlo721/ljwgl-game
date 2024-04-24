package game.graphics.gui;

import com.xamlo.core.engine.graphics.api.gui.IWidget;
import com.xamlo.core.engine.graphics.components.AbstractTexture;
import com.xamlo.core.engine.graphics.components.gui.Color;
import com.xamlo.core.engine.graphics.components.gui.PushButton;
import com.xamlo.core.engine.graphics.components.gui.Widget;
import com.xamlo.core.engine.graphics.components.gui.WidgetGeometry;
import com.xamlo.engine.api.resources.ITextureResource;
import com.xamlo.engine.api.resources.ResourceLoader;

public class GUIMainMenu extends AbstractScene {
	
	private Widget menuBg;
		private Widget menuColumn;
			private PushButton singleplayer;
			private PushButton multiplayer;
			private PushButton options;
			private PushButton exit;
	
	private final ResourceLoader<String> resourceLoader;

	public GUIMainMenu(ResourceLoader<String> resourceLoader) {
		this.resourceLoader = resourceLoader;		
	}	

	@Override
	public void load() {
		
	    ITextureResource<String> backgroundImage = resourceLoader.loadTexture("texture.gui.mainmenu.background");
	    ITextureResource<String> puttonsFrameImage = resourceLoader.loadTexture("texture.gui.mainmenu.column");
	    ITextureResource<String> puttonImage = resourceLoader.loadTexture("texture.gui.mainmenu.column");

		menuBg = new Widget();
		menuBg.setBackgroundColor(new Color(255, 0, 0));
		menuBg.setBorder(0);
		menuBg.setBackgroundImage((AbstractTexture)backgroundImage);
		menuBg.resize(new WidgetGeometry(0, 0, 1920, 1080));
			
		menuColumn = new Widget(menuBg);
		menuColumn.setBackgroundColor(new Color(255, 0, 0));
		menuColumn.setBorder(0);
		menuColumn.setBackgroundImage((AbstractTexture)puttonsFrameImage);
		menuColumn.resize(new WidgetGeometry(100, 0, 340, 1080));
		
		singleplayer = new PushButton("text.gui.mainmenu.singleplayer");
		menuColumn.addChild(singleplayer);
		singleplayer.setBackgroundColor(new Color(255, 0, 0));
		singleplayer.setBorder(0);
		singleplayer.setBackgroundImage((AbstractTexture)puttonImage);
		singleplayer.resize(new WidgetGeometry(10, 400, 300, 40));
		
		multiplayer = new PushButton("text.gui.mainmenu.multiplayer");
		menuColumn.addChild(multiplayer);

		multiplayer.setBackgroundColor(new Color(255, 0, 0));
		multiplayer.setBorder(0);
		multiplayer.setBackgroundImage((AbstractTexture)puttonImage);
		multiplayer.resize(new WidgetGeometry(10, 480, 300, 40));
		
		options = new PushButton("text.gui.mainmenu.options");
		menuColumn.addChild(options);

		options.setBackgroundColor(new Color(255, 0, 0));
		options.setBorder(0);
		options.setBackgroundImage((AbstractTexture)puttonImage);
		options.resize(new WidgetGeometry(10, 560, 300, 40));
		
		exit = new PushButton("text.gui.mainmenu.exit");
		menuColumn.addChild(exit);

		exit.setBackgroundColor(new Color(255, 0, 0));
		exit.setBorder(0);
		exit.setBackgroundImage((AbstractTexture)puttonImage);
		exit.resize(new WidgetGeometry(10, 640, 300, 40));
		
		
		this.addWidget(menuBg);
		
	}

	@Override
	public void unload() {
		for (IWidget obj : this.guiElements) {
			obj.close();
		}		
	}

}
