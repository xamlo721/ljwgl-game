package com.xamlo.core.engine.graphics.components.gui;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjglb.engine.graph.GraphicalMesh;

import com.xamlo.core.engine.graphics.api.gui.IWidget;
import com.xamlo.core.engine.graphics.api.primitives.IVertex;
import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;
import com.xamlo.core.engine.graphics.components.attribs.PositionAttribute;
import com.xamlo.core.engine.graphics.components.attribs.TexCoordAttribute;
import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.primitives.VertexStructure;
import com.xamlo.engine.api.resources.ITextureResource;

public class Widget extends AbstractRenderableObject implements IWidget {
	
	protected WidgetGeometry geometry;
	protected IWidget parent;
	protected boolean hasParent;
	protected List<IWidget> childWidgets;
	protected boolean visible;
	protected boolean isEnable;
	protected boolean focusable;
	protected boolean hasBackgroundImage; //TODO: Это Variant-ом чтоли делают? почините кто знает
	protected ITextureResource backgroundImage;
	protected Color backgroundColor;
	protected Font font;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(WidgetSize size) {
		// TODO Auto-generated method stub
		
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
	public List<IWidget> getChildElements() {
		return this.childWidgets;
	}

	@Override
	public boolean hasBackgroundImage() {
		return this.hasBackgroundImage;
	}
	
	@Override
	public void setBackgroundImage(ITextureResource image) {
		this.backgroundImage = image;
	}

	@Override
	public ITextureResource getBackgroundImage() {
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
	public void setBackgroundColor(Color color) {
		this.backgroundColor = color;
	}

	@Override
	public Color getBackgroundColor() {
		return this.backgroundColor;
	}

	@Override
	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public Font getFont() {
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
