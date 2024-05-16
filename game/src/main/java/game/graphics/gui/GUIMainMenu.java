package game.graphics.gui;

import com.xamlo.core.engine.graphics.api.gui.IClickListener;
import com.xamlo.core.engine.graphics.api.gui.IClickable;
import com.xamlo.core.engine.graphics.api.gui.IUIElement;
import com.xamlo.core.engine.graphics.api.gui.elements.IWidget;
import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;
import com.xamlo.core.engine.graphics.components.AbstractTexture;
import com.xamlo.core.engine.graphics.components.gui.Color;
import com.xamlo.core.engine.graphics.components.gui.PushButton;
import com.xamlo.core.engine.graphics.components.gui.Widget;
import com.xamlo.core.engine.graphics.components.gui.UIElementGeometry;
import com.xamlo.engine.api.resources.ITextureResource;

import com.xamlo.engine.api.resources.IResourceLoader;

public class GUIMainMenu extends AbstractScene {

	private IWidget menuBg;
		private IWidget menuColumn;
			private PushButton singleplayer;
			private PushButton multiplayer;
			private PushButton options;
			private PushButton exit;
	
	private final IResourceLoader<String> resourceLoader;

	public GUIMainMenu(IResourceLoader<String> resourceLoader) {
		this.resourceLoader = resourceLoader;		
	}

	@Override
	public void load() {
		
	    ITextureResource<String> backgroundImage = resourceLoader.loadTexture("texture.gui.mainmenu.background");
	    ITextureResource<String> puttonsFrameImage = resourceLoader.loadTexture("texture.gui.mainmenu.column");
	    ITextureResource<String> puttonImage = resourceLoader.loadTexture("texture.gui.mainmenu.column");

		menuBg = new Widget();
		menuBg.setBackgroundColor(new Color(255, 0, 0));
		menuBg.setBackgroundImage((AbstractTexture)backgroundImage);
		menuBg.resize(new UIElementGeometry(0, 0, 1920, 1080));
		menuBg.setWidgetName("main_bg.widget");
		
		menuColumn = new Widget(menuBg);
		menuColumn.setBackgroundColor(new Color(255, 0, 0));
		menuColumn.setBackgroundImage((AbstractTexture)puttonsFrameImage);
		menuColumn.resize(new UIElementGeometry(200, 0, 340, 1080));
		menuColumn.setWidgetName("main_menu_column.widget");
		
		singleplayer = new PushButton("text.gui.mainmenu.singleplayer");
		menuColumn.addChild(singleplayer);
		singleplayer.setBackgroundColor(new Color(255, 0, 0));
		singleplayer.setHoverColor(new Color(205, 10, 0));
		singleplayer.setBackgroundImage((AbstractTexture)puttonImage);
		singleplayer.resize(new UIElementGeometry(10, 180, 300, 40));
		singleplayer.setWidgetName("singleplayer.button");
		singleplayer.setClickListener(new IClickListener() {
			
			@Override
			public void onClicked(IClickable button) {
				System.out.println("Clicked on singleplayer");
			}
		});
		
		multiplayer = new PushButton("text.gui.mainmenu.multiplayer");
		menuColumn.addChild(multiplayer);

		multiplayer.setBackgroundColor(new Color(255, 0, 0));
		multiplayer.setHoverColor(new Color(205, 10, 0));
		multiplayer.setBackgroundImage((AbstractTexture)puttonImage);
		multiplayer.resize(new UIElementGeometry(10, 280, 300, 40));
		multiplayer.setWidgetName("multiplayer.button");
		multiplayer.setClickListener(new IClickListener() {
			
			@Override
			public void onClicked(IClickable button) {
				System.out.println("Clicked on multiplayer");
			}
		});
		
		options = new PushButton("text.gui.mainmenu.options");
		menuColumn.addChild(options);

		options.setBackgroundColor(new Color(255, 0, 0));
		options.setHoverColor(new Color(205, 10, 0));
		options.setBackgroundImage((AbstractTexture)puttonImage);
		options.resize(new UIElementGeometry(10, 360, 300, 40));
		options.setWidgetName("options.button");
		options.setClickListener(new IClickListener() {
			
			@Override
			public void onClicked(IClickable button) {
				System.out.println("Clicked on options");
			}
		});
		
		exit = new PushButton("text.gui.mainmenu.exit");
		menuColumn.addChild(exit);

		exit.setBackgroundColor(new Color(255, 0, 0));
		exit.setHoverColor(new Color(205, 10, 0));
		exit.setBackgroundImage((AbstractTexture)puttonImage);
		exit.resize(new UIElementGeometry(10, 440, 300, 40));
		exit.setWidgetName("exit.button");
		exit.setClickListener(new IClickListener() {
			
			@Override
			public void onClicked(IClickable button) {
				System.out.println("Clicked on exit");
			}
		});
		
		
		this.addElement(menuBg);

	}

	@Override
	public void unload() {
		for (IUIElement obj : this.guiElements) {
			obj.free();
		}		
		for (AbstractRenderableObject obj : super.objects) {
			obj.release();
		}
	}

}
