package game.graphics.gui;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;

import com.xamlo.core.engine.graphics.api.components.IScene;
import com.xamlo.core.engine.graphics.api.gui.IWidget;
import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;

public abstract class AbstractScene extends AbstractGUI implements IScene {

	private List<AbstractRenderableObject> objects;
    private Matrix4f projectionMatrix;
    

    
    public AbstractScene() {
		this.objects = new ArrayList<AbstractRenderableObject>();	
		this.guiElements = new ArrayList<IWidget>();
	}
    
	@Override
    public void addWidget(IWidget widget) {
		this.guiElements.add(widget);
		
		for (IWidget childWidget : widget.getChildElements()) {
			this.addWidget(childWidget);
		}
	}
	
	@Override
    public void removeWidget(IWidget widget) {
		this.guiElements.remove(widget);
	}
    
	@Override
	public List<AbstractRenderableObject> getRenderableObject() {
		return objects;
	}	
	
	@Override
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	@Override
	public void tranformScene(Matrix4f transformMatrix) {
		this.projectionMatrix = transformMatrix;
	}

	@Override
	public void unload() {
		for (AbstractRenderableObject object : objects) {
			object.release();
		}
		this.objects.clear();		
	}
	
}
