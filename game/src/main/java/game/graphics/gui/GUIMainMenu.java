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
import com.xamlo.core.engine.graphics.components.gui.CheckBox;
import com.xamlo.core.engine.graphics.components.gui.ComboBox;
import com.xamlo.core.engine.graphics.components.gui.GroupBox;
import com.xamlo.core.engine.graphics.components.gui.ImageView;
import com.xamlo.core.engine.graphics.components.gui.Label;
import com.xamlo.core.engine.graphics.components.gui.ProgressBar;
import com.xamlo.core.engine.graphics.components.gui.RadioButton;
import com.xamlo.core.engine.graphics.components.gui.ScrollArea;
import com.xamlo.core.engine.graphics.components.gui.Separator;
import com.xamlo.core.engine.graphics.components.gui.Slider;
import com.xamlo.core.engine.graphics.components.gui.TabControl;
import com.xamlo.core.engine.graphics.components.gui.TextField;
import com.xamlo.core.engine.graphics.api.gui.TextLineSpec;
import com.xamlo.core.engine.graphics.components.gui.TextBlock;
import com.xamlo.core.engine.graphics.components.gui.EnumAlignment;
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
	private TextBlock infoPanel;
	
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

		singleplayer.setToolTipText("Play on your own");
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

		multiplayer.setToolTipText("Join a friend's world");
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

		options.setToolTipText("Tweak video, sound and controls");
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

		exit.setToolTipText("Quit the game\n(no save data in this engine)");
		exit.setClickListener(new IClickListener() {
			
			@Override
			public void onClicked(IClickable button) {
			}
		});

		//Демо многострочного блока TextBlock: строки без стиля наследуют цвет блока,
		//отдельные строки переопределяют его; выравнивание левое от padding'а.
		infoPanel = new TextBlock();
		menuColumn.addChild(infoPanel);
		infoPanel.setBackgroundColor(new Color(0, 0, 8, 96));
		infoPanel.resize(new UIElementGeometry(20, 540, 300, 170));
		infoPanel.setTextColor(new Color(190, 190, 190));
		infoPanel.setFont(new ApplicationFont("Default", 14, false, false));
		infoPanel.addLine(new TextLineSpec("LJWGL Engine", new Color(255, 210, 120), new ApplicationFont("Default", 20, true, false)));
		infoPanel.addLine("Generic engine for game and app user interfaces");
		infoPanel.addLine("Scroll areas with clipped rendering");
		infoPanel.addLine("Drag & drop between any targets");
		infoPanel.addLine("Hover tooltips on the buttons above");

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


		// ===== Демо-колонка каталога виджетов (вторая панель главного меню) =====
		final Widget widgetsPanel = new Widget(menuBg);
		widgetsPanel.setBackgroundColor(new Color(0, 0, 5, 128));
		widgetsPanel.resize(new UIElementGeometry(600, 0, 380, 1080));
		widgetsPanel.setWidgetName("widgets_demo.panel");

		Label heading = new Label("Widgets Catalog");
		widgetsPanel.addChild(heading);
		heading.setBackgroundColor(new Color(0, 0, 0, 0));
		heading.setFont(menuFontKey);
		heading.setAlignment(EnumAlignment.LEFT);
		heading.setPadding(4);
		heading.resize(new UIElementGeometry(14, 24, 352, 32));

		Separator sepTop = new Separator(true);
		widgetsPanel.addChild(sepTop);
		sepTop.resize(new UIElementGeometry(14, 70, 352, 2));

		final CheckBox cbSound = new CheckBox("Enable sound");
		widgetsPanel.addChild(cbSound);
		cbSound.resize(new UIElementGeometry(14, 96, 352, 36));
		final Label checkState = new Label("");
		widgetsPanel.addChild(checkState);
		checkState.setBackgroundColor(new Color(0, 0, 0, 0));
		checkState.setAlignment(EnumAlignment.LEFT);
		checkState.setPadding(8);
		checkState.resize(new UIElementGeometry(14, 136, 352, 24));
		cbSound.setClickListener(button -> checkState.setText("state: " + (cbSound.isChecked() ? "on" : "off")));

		RadioButton radioLow = new RadioButton("Quality: low", "quality.demo");
		RadioButton radioMed = new RadioButton("Quality: medium", "quality.demo");
		RadioButton radioHigh = new RadioButton("Quality: high", "quality.demo");
		widgetsPanel.addChild(radioLow);
		widgetsPanel.addChild(radioMed);
		widgetsPanel.addChild(radioHigh);
		radioLow.resize(new UIElementGeometry(14, 176, 352, 32));
		radioMed.resize(new UIElementGeometry(14, 212, 352, 32));
		radioHigh.resize(new UIElementGeometry(14, 248, 352, 32));
		final Label qualityValue = new Label("");
		widgetsPanel.addChild(qualityValue);
		qualityValue.setBackgroundColor(new Color(0, 0, 0, 0));
		qualityValue.setAlignment(EnumAlignment.LEFT);
		qualityValue.setPadding(8);
		qualityValue.resize(new UIElementGeometry(14, 288, 352, 24));
		radioLow.setClickListener(button -> qualityValue.setText("selected: low"));
		radioMed.setClickListener(button -> qualityValue.setText("selected: medium"));
		radioHigh.setClickListener(button -> qualityValue.setText("selected: high"));

		GroupBox perfGroup = new GroupBox();
		widgetsPanel.addChild(perfGroup);
		perfGroup.setTitle("Performance");
		perfGroup.resize(new UIElementGeometry(14, 328, 352, 170));

		final Slider fpsSlider = new Slider();
		perfGroup.addChild(fpsSlider);
		fpsSlider.resize(new UIElementGeometry(16, 44, 320, 28));
		fpsSlider.setMinValue(30);
		fpsSlider.setMaxValue(144);
		fpsSlider.setValue(60);
		final ProgressBar fpsProgress = new ProgressBar();
		perfGroup.addChild(fpsProgress);
		fpsProgress.resize(new UIElementGeometry(16, 84, 320, 24));
		fpsProgress.setMinValue(30);
		fpsProgress.setMaxValue(144);
		fpsProgress.setValue(60);
		final Label fpsValue = new Label("");
		perfGroup.addChild(fpsValue);
		fpsValue.setBackgroundColor(new Color(0, 0, 0, 0));
		fpsValue.setAlignment(EnumAlignment.LEFT);
		fpsValue.setPadding(8);
		fpsValue.setText("value: " + fpsSlider.getValue());
		fpsValue.resize(new UIElementGeometry(16, 116, 320, 24));
		fpsSlider.setChangeListener((slider, value) -> {
			fpsProgress.setValue(value);
			fpsValue.setText("value: " + (int) value);
		});

		Separator sepMid = new Separator(true);
		widgetsPanel.addChild(sepMid);
		sepMid.resize(new UIElementGeometry(14, 520, 352, 2));

		final ComboBox modeCombo = new ComboBox("Select game mode");
		widgetsPanel.addChild(modeCombo);
		modeCombo.resize(new UIElementGeometry(14, 548, 220, 36));
		modeCombo.addItem("Classic");
		modeCombo.addItem("Relaxed");
		modeCombo.addItem("Hardcore");
		// Метка выбора под полем: выпадающий список открывается прямо на ней
		// (y=584..680) и при раскрытии перекрывает её (z-порядок).
		final Label modeState = new Label("mode: —");
		widgetsPanel.addChild(modeState);
		modeState.setBackgroundColor(new Color(0, 0, 0, 0));
		modeState.setAlignment(EnumAlignment.LEFT);
		modeState.setPadding(8);
		modeState.resize(new UIElementGeometry(14, 600, 220, 32));
		modeCombo.setSelectionListener((combo, index) -> modeState.setText("mode: " + combo.getItems().get(index)));

		// Текстовое поле: клик ставит фокус, ввод — через char-callback,
		// caret/выделение/placeholder, слушатель изменения.
		final TextField nameField = new TextField();
		widgetsPanel.addChild(nameField);
		nameField.resize(new UIElementGeometry(14, 640, 352, 34));
		nameField.setPlaceholder("Player name");
		nameField.setMaxLength(40); // достаточно длинно для горизонтальной прокрутки
		final Label nameState = new Label("name: —");
		widgetsPanel.addChild(nameState);
		nameState.setBackgroundColor(new Color(0, 0, 0, 0));
		nameState.setAlignment(EnumAlignment.LEFT);
		nameState.setPadding(8);
		nameState.resize(new UIElementGeometry(14, 676, 352, 24));
		nameField.setChangeListener((field, text) -> nameState.setText("name: " + (text.isEmpty() ? "—" : text)));

		TabControl demoTabs = new TabControl();
		widgetsPanel.addChild(demoTabs);
		demoTabs.resize(new UIElementGeometry(14, 700, 352, 240));
		Label pageOne = new Label("Page one — general info about the catalog.");
		pageOne.setAlignment(EnumAlignment.CENTER);
		pageOne.setPadding(8);
		Label pageTwo = new Label("Page two — stats and numbers live here.");
		pageTwo.setAlignment(EnumAlignment.CENTER);
		pageTwo.setPadding(8);
		Label pageThree = new Label("Page three — about this engine GUI kit.");
		pageThree.setAlignment(EnumAlignment.CENTER);
		pageThree.setPadding(8);
		demoTabs.addTab("Info", pageOne);
		demoTabs.addTab("Stats", pageTwo);
		demoTabs.addTab("About", pageThree);
		ImageView previewImage = new ImageView();
		pageOne.addChild(previewImage);
		previewImage.setImage((AbstractTexture) puttonImage);
		previewImage.resize(new UIElementGeometry(126, 60, 100, 100));

		// ScrollArea: «журнал чата» выше окна по высоте; колесо над областью
		// прокручивает содержимое, вышедшее за край обрезается и недоступно.
		final CheckBox cbPwdMask = new CheckBox("Password mask for name field");
		widgetsPanel.addChild(cbPwdMask);
		cbPwdMask.resize(new UIElementGeometry(14, 946, 352, 30));
		cbPwdMask.setClickListener(button -> nameField.setPasswordMode(cbPwdMask.isChecked()));

		Separator sepScroll = new Separator();
		widgetsPanel.addChild(sepScroll);
		sepScroll.resize(new UIElementGeometry(14, 980, 352, 2));
		final Label scrollTitle = new Label("Chat log");
		widgetsPanel.addChild(scrollTitle);
		scrollTitle.setBackgroundColor(new Color(0, 0, 0, 0));
		scrollTitle.setAlignment(EnumAlignment.LEFT);
		scrollTitle.setPadding(8);
		scrollTitle.setTextColor(new Color(170, 170, 170));
		scrollTitle.resize(new UIElementGeometry(14, 984, 352, 20));
		final ScrollArea chatLog = new ScrollArea();
		widgetsPanel.addChild(chatLog);
		chatLog.setBackgroundColor(new Color(16, 18, 24, 220));
		chatLog.resize(new UIElementGeometry(14, 1008, 352, 66));
		final int lineCount = 30;
		final int lineHeight = 18;
		for (int i = 0; i < lineCount; i++) {
			Label line = new Label(String.format("[%02d] log entry number %d", i + 1, i + 1));
			line.setAlignment(EnumAlignment.LEFT);
			line.setPadding(6);
			line.setBackgroundColor(i % 2 == 0 ? new Color(24, 27, 34) : new Color(30, 34, 42));
			line.setTextColor(i % 3 == 0 ? new Color(120, 200, 120) : new Color(210, 210, 210));
			chatLog.getContent().addChild(line);
			line.resize(new UIElementGeometry(0, i * lineHeight, 352, lineHeight));
		}
		chatLog.setContentSize(352, lineCount * lineHeight);

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
