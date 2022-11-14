import java.io.FileWriter;
import java.io.IOException;

import ru.satomi.dc.DualContouring;
import ru.satomi.dc.Model;
import ru.satomi.dc.primitive.Point3d;
import ru.satomi.dc.primitive.QuadMesh;
import ru.satomi.dc.primitive.TriandgleMesh;

public class Main {
	
	
	public static void main(String[] args) {
		
		// create the test object
		Model model = new Model();
		
		DualContouring dc = new DualContouring(128, 128, 128, new Point3d(-2, -2, -2), new Point3d(12, 12, 12));
		
		double value[][][] = dc.sample(model);
		
		QuadMesh physicMesh = dc.generateQuadMesh(model, value);
		
		TriandgleMesh triangles = dc.generateTriangleMesh(physicMesh);
	
	
		// prints result out:
		try{
		    FileWriter fw = new FileWriter("output.off");
		    fw.write("OFF\n");
		    fw.write(triangles.vertex_N + " " + triangles.faces.length + " 0\n");
		    
		    for(int i=0; i<triangles.vertex_N; i++){
				fw.write(triangles.vertexs.get(i).x + " ");
				fw.write(triangles.vertexs.get(i).y + " ");
				fw.write(triangles.vertexs.get(i).z + "\n");
		    }
		    
		    for(int i=0; i<triangles.faces.length; i++) {
					fw.write("3 ");
					fw.write(triangles.faces[i].vertexIndex1 + " ");
	                fw.write(triangles.faces[i].vertexIndex2 + " ");
	                fw.write(triangles.faces[i].vertexIndex3 + "\n");
		    }
		    fw.close();
		}
		catch (IOException e){
		    System.out.println(e);
		}
	}

}
