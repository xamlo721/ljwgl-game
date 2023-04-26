package ru.satomi.dc.primitive;

import java.util.ArrayList;
 
/**
 * 
 * ����� ������ � ���� ���������� ��������� ������������ 4-� �������
 * ������ � ���� ������ �����
 * 
 * ��������� ������ � ���� 4 ������� ����� � ������� ���� ����� Mesh
 * 
 * 
 *
 */

public class QuadMesh extends PhysicalMesh {
		
	public ArrayList<Face4i> faces;
		
	public int degree[];
	
	public QuadMesh(int vertexNumber) {
		super(vertexNumber);
		
		faces = new ArrayList<Face4i>();
		degree = new int[verticesCount];
		
	}
	
    public void addFace(Face4i face) {		
		this.faces.add(face);
    }

    
}
