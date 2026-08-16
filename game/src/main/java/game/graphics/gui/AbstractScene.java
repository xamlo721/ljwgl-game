package game.graphics.gui;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;

import com.xamlo.core.engine.graphics.api.components.IScene;
import com.xamlo.core.engine.graphics.api.gui.AbstractSceneElement;
import com.xamlo.core.engine.graphics.api.gui.IActivatable;
import com.xamlo.core.engine.graphics.api.gui.IUIElement;
import com.xamlo.core.engine.graphics.api.gui.IVisible;
import com.xamlo.core.engine.graphics.api.gui.ZOrder;
import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;

public abstract class AbstractScene extends AbstractGUI implements IScene {

	protected List<AbstractSceneElement> objects;
	protected Matrix4f projectionMatrix;
    

    
    public AbstractScene() {
		this.objects = new ArrayList<AbstractSceneElement>();	
		this.guiElements = new ArrayList<IUIElement>();
	}
    
	@Override
    public void addElement(IUIElement widget) {
		this.guiElements.add(widget);
		
		for (IUIElement childWidget : widget.getChildElements()) {
			this.addElement(childWidget);
		}
		
	}
	
	@Override
    public void removeElement(IUIElement widget) {
		this.guiElements.remove(widget);
	}
    
	@Override
	public List<AbstractSceneElement> getRenderableObject() {
		return objects;
	}	
	
	@Override
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	@Override
	public void setProjectionMatrix(Matrix4f transformMatrix) {
		this.projectionMatrix = transformMatrix;
	}

	@Override
	public void unload() {
		for (AbstractRenderableObject object : objects) {
			object.release();
		}
		this.objects.clear();		
	}
	
	@Override
    public IUIElement findElementAt(float xCoord, float yCoord) {
    	return findElementAt(xCoord, yCoord, null);
    }

	@Override
	public IUIElement findElementAt(float xCoord, float yCoord, Object excluded) {

        // Идём в обратном порядке отрисовки (z-порядок): верхний элемент,
        // содержащий точку, имеет приоритет.
        List<IUIElement> ordered = ZOrder.sorted(guiElements);
        for (int i = ordered.size() - 1; i >= 0; i--) {
            IUIElement element = ordered.get(i);

            // Исключаемый элемент (перетаскиваемый в DnD) заслонять нижние элементы не должен.
            if (element == excluded) {
                continue;
            }

            // Если виджет невидим или неактивен, то он не может быть целью
            if (element instanceof IVisible && !((IVisible) element).isVisible()) {
                continue;
            }

            if (element instanceof IActivatable && !((IActivatable) element).isEnabled()) {
                continue;
            }

            if (element.containsPoint(xCoord, yCoord)) {
                return element;
            }
        }

        return null;
    }

	
}
