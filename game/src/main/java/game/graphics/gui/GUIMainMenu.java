package game.graphics.gui;

import com.xamlo.core.engine.graphics.api.gui.IDragListener;
import com.xamlo.core.engine.graphics.api.gui.IDraggable;
import com.xamlo.core.engine.graphics.api.gui.IDropTarget;
import com.xamlo.core.engine.graphics.api.gui.IClickListener;
import com.xamlo.core.engine.graphics.api.gui.IClickable;
import com.xamlo.core.engine.graphics.api.gui.IUIElement;
import com.xamlo.core.engine.graphics.api.gui.elements.IWidget;
import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;
import com.xamlo.core.engine.graphics.components.AbstractTexture;
import com.xamlo.core.engine.graphics.components.gui.Color;
import com.xamlo.core.engine.graphics.components.gui.FontResource;
import com.xamlo.core.engine.graphics.font.ApplicationFont;
import com.xamlo.core.engine.graphics.fontsystem.FontSystem;
import com.xamlo.core.engine.graphics.components.gui.PushButton;
import com.xamlo.core.engine.graphics.components.gui.Widget;
import com.xamlo.core.engine.graphics.components.gui.UIElementGeometry;
import com.xamlo.engine.api.resources.ITextureResource;
import com.xamlo.engine.api.resources.IFontResource;
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
	    ITextureResource<String> puttonImage = resourceLoader.loadTexture("texture.gui.mainmenu.column");
	    IFontResource<String> font = resourceLoader.loadFont("font.gui.mainmenu.roboto.bold");
        ApplicationFont menuFontKey = new ApplicationFont("Roboto", 24, true, false);
        FontSystem.getInstance().attach(menuFontKey, ((FontResource) font).getGlyphFont());

		menuBg = new Widget();
		menuBg.setBackgroundColor(new Color(255, 0, 0));
		menuBg.setBackgroundImage((AbstractTexture)backgroundImage);
		menuBg.setBackgroundColor(new Color(0,0,0, 128));
		menuBg.resize(new UIElementGeometry(0, 0, 1920, 1080));
		menuBg.setWidgetName("main_bg.widget");
		
		menuColumn = new Widget(menuBg);
		menuColumn.setBackgroundColor(new Color(255, 255, 255, 96));
		menuColumn.resize(new UIElementGeometry(200, 0, 340, 1080));
		menuColumn.setWidgetName("main_menu_column.widget");
		menuColumn.setBackgroundColor(new Color(0,0,5, 128));

		singleplayer = new PushButton(GUITexts.resolve("text.gui.mainmenu.singleplayer"));
		menuColumn.addChild(singleplayer);
		singleplayer.setBackgroundColor(new Color(255, 255, 255, 96));
		singleplayer.setBackgroundImage((AbstractTexture)puttonImage);
		singleplayer.setHoverColor(new Color(255, 255, 255, 192));
		singleplayer.resize(new UIElementGeometry(20, 180, 300, 40));
		singleplayer.setFont(menuFontKey);
		singleplayer.setWidgetName("singleplayer.button");
		singleplayer.setClickListener(new IClickListener() {
			
			@Override
			public void onClicked(IClickable button) {
			}
		});
		
		multiplayer = new PushButton(GUITexts.resolve("text.gui.mainmenu.multiplayer"));
		menuColumn.addChild(multiplayer);

		multiplayer.setBackgroundColor(new Color(255, 255, 255, 96));
		multiplayer.setHoverColor(new Color(255, 255, 255, 192));
		multiplayer.setBackgroundImage((AbstractTexture)puttonImage);
		multiplayer.resize(new UIElementGeometry(20, 280, 300, 40));
		multiplayer.setFont(menuFontKey);
		multiplayer.setWidgetName("multiplayer.button");
		multiplayer.setClickListener(new IClickListener() {
			
			@Override
			public void onClicked(IClickable button) {
			}
		});
		
		options = new PushButton(GUITexts.resolve("text.gui.mainmenu.options"));
		menuColumn.addChild(options);

		options.setBackgroundColor(new Color(255, 255, 255, 96));
		options.setHoverColor(new Color(255, 255, 255, 192));
		options.setBackgroundImage((AbstractTexture)puttonImage);
		options.resize(new UIElementGeometry(20, 360, 300, 40));
		options.setFont(menuFontKey);
		options.setWidgetName("options.button");
		options.setClickListener(new IClickListener() {
			
			@Override
			public void onClicked(IClickable button) {
			}
		});
		
		exit = new PushButton(GUITexts.resolve("text.gui.mainmenu.exit"));
		menuColumn.addChild(exit);

		exit.setBackgroundColor(new Color(255, 255, 255, 96));
		exit.setHoverColor(new Color(255, 255, 255, 192));
		exit.setBackgroundImage((AbstractTexture)puttonImage);
		exit.resize(new UIElementGeometry(20, 440, 300, 40));
		exit.setFont(menuFontKey);
		exit.setWidgetName("exit.button");
		exit.setClickListener(new IClickListener() {
			
			@Override
			public void onClicked(IClickable button) {
			}
		});

		//Демо drag-and-drop: кнопку singleplayer можно перетаскивать мышью.
		//Любой виджет (включая панель menuBg и колонку) принимает дроп — во время
		//жеста активный таргет подсвечивается автоматически, при отпускании вне
		//таргета кнопка возвращается на исходное место.
		final int[] originalPos = new int[]{singleplayer.getGeometry().getXCoord(), singleplayer.getGeometry().getYCoord()};
		singleplayer.setDragListener(new IDragListener() {

			private float grabOffX;
			private float grabOffY;

			@Override
			public void onDragStart(IDraggable element, float xCoord, float yCoord) {
				this.grabOffX = xCoord - singleplayer.getAbsX();
				this.grabOffY = yCoord - singleplayer.getAbsY();
			}

			@Override
			public void onDrag(IDraggable element, float xCoord, float yCoord, float deltaX, float deltaY) {
				IUIElement dragParent = singleplayer.getParent();
				int parentOffsetX = dragParent != null ? (int) dragParent.getAbsX() : 0;
				int parentOffsetY = dragParent != null ? (int) dragParent.getAbsY() : 0;
				singleplayer.setPosition((int)(xCoord - grabOffX - parentOffsetX), (int)(yCoord - grabOffY - parentOffsetY));
			}

			@Override
			public void onDrop(IDraggable element, IDropTarget target, float xCoord, float yCoord) {
				//Кнопка остаётся в точке сброса — перемещение уже сделано в onDrag
			}

			@Override
			public void onDragEnd(IDraggable element, float xCoord, float yCoord) {
				singleplayer.setPosition(originalPos[0], originalPos[1]);
			}

			@Override
			public void onDragEnterTarget(IDraggable element, IDropTarget target) {
			}

			@Override
			public void onDragLeaveTarget(IDraggable element, IDropTarget target) {
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
