package ru.satomi.dc.primitive;

import java.util.ArrayList;

public class Mesh {
	
	public ArrayList<Point3d> vertexs;
	public int vertex_N;
	
	public Mesh(int vertexNumber) {
		vertex_N = vertexNumber;
		vertexs = new ArrayList<Point3d>();
		
		for (int i = 0; i < vertexNumber; i++) {
			vertexs.add(i, new Point3d());
		}
	}

}
