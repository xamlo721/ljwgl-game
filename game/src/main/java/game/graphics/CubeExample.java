package game.graphics;

import org.joml.Vector2f;
import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.api.primitives.IVertex;
import com.xamlo.core.engine.graphics.components.AbstractModel;
import com.xamlo.core.engine.graphics.components.GraphicalMesh;
import com.xamlo.core.engine.graphics.components.attribs.PositionAttribute;
import com.xamlo.core.engine.graphics.components.attribs.TexCoordAttribute;
import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.primitives.VertexStructure;

public class CubeExample extends AbstractModel {

	/**
	 * Сетка точек, описывающая объект
	 */
	protected static GraphicalMesh mesh;

	
	public CubeExample() {
		super("empty");
	}
	
	@Override
	public void loadMesh() {

    	IVertex[] vertices = new Vertex[8];
    	int i = 0;
    	
    	VertexStructure vertexScruct = new VertexStructure();
    	vertexScruct.addAttribute(new PositionAttribute());
    	vertexScruct.addAttribute(new TexCoordAttribute());
    	vertexScruct.setVertexCount(8);
    	
    	vertices[i++] = new Vertex(5).append(new Vector3f(-0.5f,  0.5f, 1.0f)).append(new Vector2f(0.0f, 0.0f)); //V1
    	vertices[i++] = new Vertex(5).append(new Vector3f(-0.5f, -0.5f, 1.0f)).append(new Vector2f(0.0f, 1.0f)); //V2
    	vertices[i++] = new Vertex(5).append(new Vector3f( 0.5f, -0.5f, 1.0f)).append(new Vector2f(1.0f, 1.0f)); //V3
    	vertices[i++] = new Vertex(5).append(new Vector3f( 0.5f,  0.5f, 1.0f)).append(new Vector2f(1.0f, 0.0f)); //V4
    	vertices[i++] = new Vertex(5).append(new Vector3f(-0.5f,  0.5f, -1.0f)).append(new Vector2f(0.0f, 0.0f)); //V1-1
    	vertices[i++] = new Vertex(5).append(new Vector3f(-0.5f, -0.5f, -1.0f)).append(new Vector2f(0.0f, 1.0f)); //V2-1
    	vertices[i++] = new Vertex(5).append(new Vector3f( 0.5f, -0.5f, -1.0f)).append(new Vector2f(1.0f, 1.0f)); //V3-1
    	vertices[i++] = new Vertex(5).append(new Vector3f( 0.5f,  0.5f, -1.0f)).append(new Vector2f(1.0f, 0.0f)); //V4-1

    	i = 0;
    	int[] indices = new int[36]; 
    	//FACE
    	indices[i++] = 0;
    	indices[i++] = 1;
    	indices[i++] = 3;
    	
    	indices[i++] = 3;
    	indices[i++] = 1;
    	indices[i++] = 2;
    	//LEFT
    	indices[i++] = 0;
    	indices[i++] = 1;
    	indices[i++] = 5;
    	
    	indices[i++] = 0;
    	indices[i++] = 4;
    	indices[i++] = 5;
    	//RIGHT
    	indices[i++] = 3;
    	indices[i++] = 2;
    	indices[i++] = 7;
    	
    	indices[i++] = 2;
    	indices[i++] = 6;
    	indices[i++] = 7;
    	//BEHIND
    	indices[i++] = 4;
    	indices[i++] = 5;
    	indices[i++] = 7;
    	
    	indices[i++] = 7;
    	indices[i++] = 5;
    	indices[i++] = 6;
    	///TOP
    	indices[i++] = 0;
    	indices[i++] = 3;
    	indices[i++] = 4;
    	
    	indices[i++] = 3;
    	indices[i++] = 7;
    	indices[i++] = 4;
    	///BUTTOM
    	indices[i++] = 1;
    	indices[i++] = 5;
    	indices[i++] = 6;
    	
    	indices[i++] = 1;
    	indices[i++] = 2;
    	indices[i++] = 6;
        mesh = new GraphicalMesh(vertices, vertexScruct, indices);
        
	}
	
	@Override
	public GraphicalMesh getMesh() {
		return mesh;
	}
	
	@Override
	public void init() {
		if (mesh == null) {
			super.init();
			this.loadMesh();
		}

	}
	
	@Override
	public void release() {
		mesh.cleanup();
	}

}
