package ru.satomi.dc.primitive;

public class TriandgleMesh extends PhysicalMesh {
	
	public Face3i faces[];
	
	int face_N;
	
	
	public TriandgleMesh(int vertexNumber) {
		super(vertexNumber);
	}
	
    public void setFaceN(int N){
		this.face_N = N;
		this.faces = new Face3i[N];
    }
}
