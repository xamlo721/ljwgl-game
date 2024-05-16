package game.graphics.gui;

import java.util.ArrayList;
import java.util.List;

import org.joml.Matrix4f;

import com.xamlo.core.engine.graphics.api.components.IScene;
import com.xamlo.core.engine.graphics.api.gui.AbstractSceneElement;
import com.xamlo.core.engine.graphics.api.gui.IActivatable;
import com.xamlo.core.engine.graphics.api.gui.IUIElement;
import com.xamlo.core.engine.graphics.api.gui.IVisible;
import com.xamlo.core.engine.graphics.api.gui.elements.IWidget;
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

        // Идем в обратном порядке, так как последние добавленные виджеты отрисовываются поверх остальных.
        for (IUIElement element : guiElements) {
        	IUIElement found = findElementAt(element, xCoord, yCoord);
            if (found != null) {
                return found;
            }
            
        }

        return null;
    }
    
    private IUIElement findElementAt(IUIElement element, float xCoord, float yCoord) {
    	
        // Если виджет невидим или неактивен, то он не может быть целью
    	if (element instanceof IVisible && !((IVisible)element).isVisible()) {
            return null;
        }
        
    	if (element instanceof IActivatable && !((IActivatable)element).isEnabled()) {
            return null;
    	}

        // Проверяем, есть ли у виджета дочерние элементы? 
    	// Тогда сначала проверяем их (они могут быть поверх родителя)
        List<IUIElement> children = element.getChildElements();

        for (int i = children.size() - 1; i >= 0; i--) {
        	
        	IUIElement child = children.get(i);
        	
        	if (!(child instanceof IWidget)) {
        		continue;
        	}
        	
        	IUIElement found = findElementAt((IUIElement)child, xCoord, yCoord);

            if (found != null) {
                return found;
            }
            
        }
        
        // Если ни один дочерний не подошел, проверяем сам виджет
        if (element.containsPoint(xCoord, yCoord)) {
            return element;
        }
        
        return null;
    }

	
}
