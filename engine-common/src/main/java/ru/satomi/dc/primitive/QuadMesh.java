package ru.satomi.dc.primitive;

import java.util.ArrayList;
 

public class QuadMesh extends PhysicalMesh {
		
	public ArrayList<Face4i> faces;
		
	public int degree[];
	
	public QuadMesh(int vertexNumber) {
		super(vertexNumber);
		
		faces = new ArrayList<Face4i>();
		degree = new int[vertexCount];
		
	}
	
    public void addFace(Face4i face) {		
		this.faces.add(face);
    }

    
}
