package ru.satomi.dc.primitive;

import java.util.ArrayList;

public class PhysicalMesh {
	
	public ArrayList<Point3d> vertexs;
	
	///Количество вершин в Меше
	public int verticesCount;
	
	public PhysicalMesh(int vertexNumber) {
		verticesCount = vertexNumber;
		vertexs = new ArrayList<Point3d>();
		
		for (int i = 0; i < vertexNumber; i++) {
			vertexs.add(i, new Point3d());
		}
	}

}
