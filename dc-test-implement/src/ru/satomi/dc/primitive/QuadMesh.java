package ru.satomi.dc.primitive;

import java.util.ArrayList;
 
/**
 * 
 *  ласс хранит в себе квадратные плоскости ограниченные 4-€ точками
 * ’ранит в себе массив точек
 * 
 * ѕлоскость хранит в себе 4 индекса точек в массиве всех точек Mesh
 * 
 * 
 *
 */

public class QuadMesh extends Mesh {
		
	public ArrayList<Face4i> faces;
		
	public int degree[];
	
	public QuadMesh(int vertexNumber) {
		super(vertexNumber);
		
		faces = new ArrayList<Face4i>();
		degree = new int[vertex_N];
		
	}
	
    public void addFace(Face4i face) {		
		this.faces.add(face);
    }

    
}
