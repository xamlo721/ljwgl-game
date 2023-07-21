package game.graphics.gui;

import java.util.List;

import org.joml.Matrix4f;

import com.xamlo.core.engine.graphics.api.components.IScene;
import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;

public abstract class AbstractGuiScene implements IScene {

    protected List<AbstractRenderableObject> widgets;
    protected int guiID;
    protected Matrix4f projectionMatrix;

	@Override
	public List<AbstractRenderableObject> getRenderableObject() {
		return widgets;
	}
	
	public int getId() {
		return this.guiID;
	}
	
	abstract void addWidget();
	
	abstract void removeWidget();
	
	@Override
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	@Override
	public void tranformScene(Matrix4f transformMatrix) {
		this.projectionMatrix = transformMatrix;
	}
	
}
