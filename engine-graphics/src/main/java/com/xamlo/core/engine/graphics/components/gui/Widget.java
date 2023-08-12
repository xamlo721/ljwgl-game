package com.xamlo.core.engine.graphics.components.gui;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.api.gui.IColor;
import com.xamlo.core.engine.graphics.api.gui.IFont;
import com.xamlo.core.engine.graphics.api.gui.IWidget;
import com.xamlo.core.engine.graphics.api.primitives.IVertex;
import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;
import com.xamlo.core.engine.graphics.components.AbstractTexture;
import com.xamlo.core.engine.graphics.components.GraphicalMesh;
import com.xamlo.core.engine.graphics.components.attribs.PositionAttribute;
import com.xamlo.core.engine.graphics.components.attribs.TexCoordAttribute;
import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.primitives.VertexStructure;

public class Widget extends AbstractRenderableObject implements IWidget {
	
	protected WidgetGeometry geometry;
	protected IWidget parent;
	protected boolean hasParent;
	protected List<IWidget> childWidgets;
	protected boolean visible;
	protected boolean isEnable;
	protected boolean focusable;

	protected IFont font;
	protected String toolTipText;
	protected Border border;
	protected String widgetName;
	protected GraphicalMesh mesh;

	public Widget() {
		
		this.geometry = new WidgetGeometry(0, 0, 0, 0);
		this.parent = null;
		this.hasParent = false;
		this.childWidgets = new ArrayList<IWidget>();
		this.visible = true;
		this.isEnable = true;
		this.focusable = false;
		this.hasBackgroundImage = false;
		this.backgroundColor = new Color(255, 255, 255);
		this.font = new Font("Default", 12, false, false);
		this.toolTipText = "";
		this.border = new Border(4, new Color(128, 128, 128));
		
	}
	
	public Widget(Widget parent) {
		this.parent = parent;
		this.parent.addChild(this);
		this.geometry = new WidgetGeometry(0, 0, 0, 0);
		this.parent = null;
		this.hasParent = false;
		this.childWidgets = new ArrayList<IWidget>();
		this.visible = true;
		this.isEnable = true;
		this.focusable = false;
		this.hasBackgroundImage = false;
		this.backgroundColor = new Color(255, 255, 255);
		this.font = new Font("Default", 12, false, false);
		this.toolTipText = "";
		this.border = new Border(4, new Color(128, 128, 128));
		
	}
	
	@Override
	public void setParent(IWidget parent) {
		this.hasParent = true;
		this.parent = parent;
	}

	@Override
	public boolean hasParent() {
		return this.hasParent;
	}


	@Override
	public IWidget getParent() {
		return this.parent;
	}
	
	@Override
	public void setWidgetName(String widgetName) {
		this.widgetName = widgetName;
	}

	@Override
	public String getWidgetName() {
		return this.widgetName;
	}
	
	@Override
	public WidgetGeometry getWidgetGeometry() {
		return geometry;
	}

	@Override
	public WidgetSize getWidSize() {
		return geometry;
	}

	@Override
	public void resize(WidgetGeometry geometry) {
		//TODO: Протестить, те ли вообще поля я трогаю
		//this.setExpandGeometry((float)this.geometry.width / (float)geometry.width, (float)this.geometry.height / (float)geometry.height, 1.0f);
		this.setExpandGeometry((float)geometry.width/(float)1920 ,(float)geometry.height/ (float)1080  , 1.0f);

		float xPos;
		float yPos;
		
		//Не получается сделать относительные координаты, какой-то говнокод
		if(hasParent) {
			xPos = ((float)this.parent.getWidgetGeometry().xCoord + (float)geometry.xCoord) / (float)1920;
			yPos = ((float)this.parent.getWidgetGeometry().yCoord + (float)geometry.yCoord) / (float)1080;
		} else {
			xPos = (float)geometry.xCoord / (float)1920;
			yPos = (float)geometry.yCoord / (float)1080;
		}
		
		this.setPosition(
				xPos, 
				yPos,
				this.position.z);

		this.geometry = geometry;
		
	}

	@Override
	public void resize(WidgetSize size) {
		//TODO: Протестить, те ли вообще поля я трогаю
		//this.setExpandGeometry((float)this.geometry.width / (float)geometry.width, (float)this.geometry.height / (float)geometry.height, 1.0f);
		this.setExpandGeometry((float)geometry.width/ (float)1920 ,(float)geometry.height/ (float)1080  , 1.0f);

		this.geometry.width = geometry.width;		
		this.geometry.height = geometry.height;		
	}

	@Override
	public void hide() {
		this.visible = false;
	}

	@Override
	public void show() {
		this.visible = true;
	}

	@Override
	public void setVisible(boolean visible) {
		if(visible) {
			show();
		} else {
			hide();
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addChild(IWidget child) {
		this.childWidgets.add(child);
		if (!child.hasParent()) {
			child.setParent(this);
		}
	}
	
	@Override
	public List<IWidget> getChildElements() {
		return this.childWidgets;
	}

	@Override
	public boolean hasBackgroundImage() {
		return this.hasBackgroundImage;
	}
	
	@Override
	public void setBackgroundImage(AbstractTexture image) {
		this.backgroundImage = image;
		this.hasBackgroundImage = true;
	}

	@Override
	public AbstractTexture getBackgroundImage() {
		if (!this.hasBackgroundImage) {
			return null;
		}
		return this.backgroundImage;
	}

	@Override
	public void setPosition(int x, int y) {
		this.geometry = new WidgetGeometry(x, y, this.geometry.width, this.geometry.height);
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.isEnable = enabled;
	}

	@Override
	public boolean isEnabled() {
		return this.isEnable;
	}

	@Override
	public void setBackgroundColor(IColor color) {
		this.backgroundColor = color;
	}

	@Override
	public IColor getBackgroundColor() {
		return this.backgroundColor;
	}

	@Override
	public void setFont(IFont font) {
		this.font = font;
	}

	@Override
	public IFont getFont() {
		return this.font;
	}

	@Override
	public void setToolTipText(String tooltip) {
		this.toolTipText = tooltip;
	}

	@Override
	public String getToolTipText() {
		return this.toolTipText;
	}

	@Override
	public void setBorder(Border border) {
		this.border = border;
	}
	
	@Override
	public void setBorder(int borderSize) {
		this.border = new Border(borderSize, this.border.getColor());
	}

	@Override
	public Border getBorder() {
		return this.border;
	}

	@Override
	public void setFocusable(boolean focusable) {
		this.focusable = focusable;
	}

	@Override
	public boolean isFocusable() {
		return this.focusable;
	}

	@Override
	public void init() {
		if (mesh == null) {
			this.loadMesh();
		}		
	}

	@Override
	public void release() {
		mesh.cleanup();
		
	}

	@Override
	public GraphicalMesh getMesh() {
		return mesh;
	}

	@Override
	public void loadMesh() {

    	IVertex[] vertices = new Vertex[4];
    	int i = 0;
    	
    	VertexStructure vertexScruct = new VertexStructure();
    	vertexScruct.addAttribute(new PositionAttribute());
    	vertexScruct.addAttribute(new TexCoordAttribute());
    	vertexScruct.setVertexCount(4);
    	
    	vertices[i++] = new Vertex(5).append(new Vector3f(-1.0f,  1.0f, 0.0f)).append(new Vector2f(0.0f, 0.0f)); //V1
    	vertices[i++] = new Vertex(5).append(new Vector3f(-1.0f, -1.0f, 0.0f)).append(new Vector2f(0.0f, 1.0f)); //V2
    	vertices[i++] = new Vertex(5).append(new Vector3f( 1.0f, -1.0f, 0.0f)).append(new Vector2f(1.0f, 1.0f)); //V3
    	vertices[i++] = new Vertex(5).append(new Vector3f( 1.0f,  1.0f, 0.0f)).append(new Vector2f(1.0f, 0.0f)); //V4


    	i = 0;
    	int[] indices = new int[6]; 
    	//FACE
    	indices[i++] = 0;
    	indices[i++] = 1;
    	indices[i++] = 3;
    	
    	indices[i++] = 3;
    	indices[i++] = 1;
    	indices[i++] = 2;

        mesh = new GraphicalMesh(vertices, vertexScruct, indices);
        		
	}










}
