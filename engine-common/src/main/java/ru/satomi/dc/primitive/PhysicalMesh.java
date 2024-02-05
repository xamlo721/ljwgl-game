package ru.satomi.dc.primitive;

import java.util.ArrayList;

import org.joml.Vector3d;

/**
 * Serverside class describing surface geometry
 *
 * Consists of an array of vertices and a counter for the number of vertices
 *
 * The number of vertices field is located separately, since
 * currently in the code the places where first
 * a mesh is created, the number of vertices is set and only
 * then the specific ones are indicated.
 * 
 * @author Satomi
 *
 */
public class PhysicalMesh {
	
	protected ArrayList<Vector3d> vertexs;
	
	///Количество вершин в Меше
	protected int vertexCount;
	
	public PhysicalMesh(int vertexCount) {
		this.vertexCount = vertexCount;
		this.vertexs = new ArrayList<Vector3d>(vertexCount);
		
		for (int i = 0; i < vertexCount; i++) {
			vertexs.add(i, new Vector3d());
		}
	}
	
	public PhysicalMesh(int vertexCount, ArrayList<Vector3d> vertexs) {
		//TODO: Assert 
		this.vertexCount = vertexCount;
		this.vertexs = vertexs;
	}
	
	public ArrayList<Vector3d> getVertices() {
		return this.vertexs;
	}
	
	public int getVertexCount() {
		return this.vertexCount;
	}
	
	public void addVertex(Vector3d v) {
		this.vertexs.add(v);
		this.vertexCount++;
	}

	public void addVertex(int i, Vector3d v) {
		this.vertexs.set(i, v);
	}
}
