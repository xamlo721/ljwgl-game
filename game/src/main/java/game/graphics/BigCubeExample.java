package game.graphics;

import org.joml.Vector3d;
import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.primitives.Vertex;
import com.xamlo.core.engine.graphics.primitives.VertexStructure;
import com.xamlo.core.engine.graphics.components.AbstractRenderableObject;
import com.xamlo.core.engine.graphics.components.GraphicalMesh;
import com.xamlo.core.engine.graphics.components.attribs.ColorAttribute;
import com.xamlo.core.engine.graphics.components.attribs.PositionAttribute;
import com.xamlo.core.engine.graphics.components.example.DualContouring;
import com.xamlo.core.engine.graphics.components.example.Model;

import ru.satomi.dc.primitive.Face3i;
import ru.satomi.dc.primitive.QuadMesh;
import ru.satomi.dc.primitive.TriandgleMesh;

public class BigCubeExample extends AbstractRenderableObject {
	
	/**
	 * Сетка точек, описывающая объект
	 */
	protected static GraphicalMesh mesh;
	
	public BigCubeExample() {
		super();
	}
	
	@Override
	public void init() {
		if (mesh == null) {
			this.loadMesh();
		}		
	}
	
	@Override
	public void loadMesh() {
		
		// create the test object
		Model model = new Model();
		
		DualContouring dc = new DualContouring(16, 16, 16, new Vector3d(-2, -2, -2), new Vector3d(12, 12, 12));
		
		double value[][][] = dc.sample(model);
		
		QuadMesh physicMesh = dc.generateQuadMesh(model, value);
		
		TriandgleMesh triangles = dc.generateTriangleMesh(physicMesh);
	
		
    	int i = 0;
    	Vertex[] vertices = new Vertex[triangles.vertexs.size()];
    	int[] indices = new int[triangles.faces.length * 3]; 

    	VertexStructure vertexScruct = new VertexStructure();
    	vertexScruct.addAttribute(new PositionAttribute());
    	vertexScruct.addAttribute(new ColorAttribute());
    	vertexScruct.setVertexCount(triangles.vertexs.size());
    	
    	for (Vector3d point : triangles.vertexs) {
    		vertices[i] = new Vertex(6);
    		vertices[i].append(new Vector3f((float)point.x, (float)point.y, (float)point.z));
    		vertices[i].append(new Vector3f((float)Math.random(),  (float)Math.random(), (float)Math.random()));
    		i++;
    	}
    	i = 0;
    	for (Face3i triangle : triangles.faces) {
    		indices[i++] = triangle.vertexIndex1;
    		indices[i++] = triangle.vertexIndex2;
    		indices[i++] = triangle.vertexIndex3;
    	}
		
    	System.out.println("Loading big Cube... " + triangles.faces.length + " poligons loaded.");
        mesh = new GraphicalMesh(vertices, vertexScruct, indices);

	}

	@Override
	public GraphicalMesh getMesh() {
		return mesh;
	}

	@Override
	public void release() {
		mesh.cleanup();
		
	}
}
