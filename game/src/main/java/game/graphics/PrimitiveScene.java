package game.graphics;

import com.xamlo.engine.api.resources.ITextureResource;
import com.xamlo.engine.resource.SimpleResourceLoader;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.api.components.IScene;
import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;
import com.xamlo.core.engine.graphics.components.Texture;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PrimitiveScene implements IScene {
		    
    private List<AbstractRenderableObject> items;

    private Matrix4f projectionMatrix;
    private ITextureResource smile;

	@Override
    public void tranformScene(Matrix4f transformMatrix) {
    	this.projectionMatrix = transformMatrix;
    }

	@Override
	public void load() {		

		smile = SimpleResourceLoader.getInstance().loadTexture(URI.create("resource:///textures/example/smile.png"));
		items = new ArrayList<AbstractRenderableObject>();
		for (int i = 0; i < 5; i++) {
			CubeExample cube = new CubeExample();
			cube.init();
			cube.setPosition(new Vector3f(
					((float)Math.random() - 0.5f) * 5, 
					((float)Math.random() - 0.5f) * 5, 
				   -((float)Math.random() % 100.0f + 5))
			);
			//cube.setPosition(new Vec3f( 0.1f,  0.1f, -1.0f));
			cube.setScale(0.5f);
			items.add(cube);
		}
		
		for (int i = 0; i < 1; i++) {
			BigCubeExample big = new BigCubeExample();
			big.init();
			big.setPosition(new Vector3f(
					((float)Math.random() - 0.5f) * 25, 
					((float)Math.random() - 0.5f) * 25, 
				   -((float)Math.random() % 100.0f + 25))
			);
			//cube.setPosition(new Vec3f( 0.1f,  0.1f, -1.0f));
			//big.setScale(0.25f);
			items.add(big);
		}

		System.out.println("Primitive scene loaded");
	}

	@Override
	public List<AbstractRenderableObject> getRenderableObject() {
		return this.items;
	}
	
	@Override
	public void release() {	    
		for (AbstractRenderableObject obj : items) {
			obj.release();
		}
	}

	@Override
	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}
	

}