package ru.satomi.dc.primitive;

import java.util.ArrayList;

import org.joml.Vector3d;

public class PhysicalMesh {
	
	public ArrayList<Vector3d> vertexs;
	
	///Количество вершин в Меше
	public int verticesCount;
	
	public PhysicalMesh(int vertexNumber) {
		verticesCount = vertexNumber;
		vertexs = new ArrayList<Vector3d>();
		
		for (int i = 0; i < vertexNumber; i++) {
			vertexs.add(i, new Vector3d());
		}
	}

}
