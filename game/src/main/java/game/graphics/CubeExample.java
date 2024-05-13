package game.graphics;

import com.xamlo.core.engine.graphics.api.gui.AbstractSceneElement;
import com.xamlo.core.engine.graphics.components.GraphicalMesh;
import com.xamlo.engine.api.resources.IModelResource;
import com.xamlo.engine.resources.ResourceLoader;

public class CubeExample extends AbstractSceneElement {

	/**
	 * Сетка точек, описывающая объект
	 */
	protected static GraphicalMesh mesh;

	protected static IModelResource<String> model;

	public CubeExample() {
		super();
	}
	
	@Override
	public void loadMesh() {

		model = ResourceLoader.INSTANCE().loadModel("model.example.cube");
        mesh = new GraphicalMesh(model.getVertices(), model.getStructure(), model.getIndices());
        
	}
	
	@Override
	public GraphicalMesh getMesh() {
		return mesh;
	}
	
	@Override
	public void init() {
		if (mesh == null) {
			//super.init();
			this.loadMesh();
		}

	}
	
	@Override
	public void release() {
		mesh.cleanup();
	}

}
