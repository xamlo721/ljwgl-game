package game.graphics.gui;

import java.util.ArrayList;

import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.api.gui.IWidget;
import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;
import com.xamlo.core.engine.graphics.components.AbstractTexture;
import com.xamlo.core.engine.graphics.components.gui.Color;
import com.xamlo.core.engine.graphics.components.gui.PushButton;
import com.xamlo.core.engine.graphics.components.gui.Widget;
import com.xamlo.core.engine.graphics.components.gui.WidgetGeometry;
import com.xamlo.engine.api.resources.ITextureResource;
import com.xamlo.engine.api.resources.ResourceLoader;

public class GUIMainMenu extends AbstractGuiScene {
	
	private Widget menuBg;
		private Widget menuColumn;
			private PushButton singleplayer;
			private PushButton multiplayer;
			private PushButton options;
			private PushButton exit;
	
	private final ResourceLoader<String> resourceLoader;

	public GUIMainMenu(ResourceLoader<String> resourceLoader) {
		this.widgets = new ArrayList<AbstractRenderableObject>();
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
		menuBg.loadMesh();
		
		menuColumn = new Widget(menuBg);
		menuColumn.setBackgroundColor(new Color(255, 0, 0));
		menuColumn.setBorder(0);
		menuColumn.setBackgroundImage((AbstractTexture)puttonsFrameImage);
		menuColumn.resize(new WidgetGeometry(40, 0, 340, 1080));
		menuColumn.loadMesh();
		menuColumn.move(new Vector3f(0.0f, 0.0f, 0.1f));
		
		singleplayer = new PushButton("text.gui.mainmenu.singleplayer");
		menuColumn.addChild(singleplayer);
		singleplayer.setBackgroundColor(new Color(255, 0, 0));
		singleplayer.setBorder(0);
		singleplayer.setBackgroundImage((AbstractTexture)puttonImage);
		singleplayer.resize(new WidgetGeometry(10, 400, 300, 40));
		singleplayer.loadMesh();
		
		multiplayer = new PushButton("text.gui.mainmenu.multiplayer");
		menuColumn.addChild(multiplayer);

		multiplayer.setBackgroundColor(new Color(255, 0, 0));
		multiplayer.setBorder(0);
		multiplayer.setBackgroundImage((AbstractTexture)puttonImage);
		multiplayer.resize(new WidgetGeometry(10, 480, 300, 40));
		multiplayer.loadMesh();
		
		options = new PushButton("text.gui.mainmenu.options");
		menuColumn.addChild(options);

		options.setBackgroundColor(new Color(255, 0, 0));
		options.setBorder(0);
		options.setBackgroundImage((AbstractTexture)puttonImage);
		options.resize(new WidgetGeometry(10, 560, 300, 40));
		options.loadMesh();
		
		exit = new PushButton("text.gui.mainmenu.exit");
		menuColumn.addChild(exit);

		exit.setBackgroundColor(new Color(255, 0, 0));
		exit.setBorder(0);
		exit.setBackgroundImage((AbstractTexture)puttonImage);
		exit.resize(new WidgetGeometry(10, 640, 300, 40));
		exit.loadMesh();
		
		
		this.addWidget(menuBg);
		
	}



	@Override
	void addWidget(Widget widget) {
		System.out.println("123");
		this.widgets.add(widget);
		// Рекурсивно добавляем в лист все дочерние виджеты в этом виджете 
		for (IWidget w : widget.getChildElements()) {
			this.addWidget((Widget)w); //FIXME: не уверен, что так можно писать
		}
	}



	@Override
	void removeWidget(Widget widget) {
		this.widgets.remove(widget);
	}
	

	@Override
	public void unload() {
		for (AbstractRenderableObject obj : this.widgets) {
			obj.release();
		}		
	}

}
