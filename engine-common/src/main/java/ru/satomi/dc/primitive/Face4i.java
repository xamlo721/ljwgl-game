package ru.satomi.dc.primitive;

import org.joml.Vector3d;

/*
 * 
 * ����� ������ � ���� ������� ���������� Face ������
 * ��� ����������� ����� ��� 4 ��������, ��� ������������ 3
 */
public class Face4i {
	
	public int vertexIndex1;
	public int vertexIndex2;
	public int vertexIndex3;
	public int vertexIndex4;
	
	public Vector3d faceNormale;//normale_f

	
	public Face4i(int vertexIndex1, int vertexIndex2, int vertexIndex3, int vertexIndex4, Vector3d faceNormale) {
		this.vertexIndex1 = vertexIndex1;
		this.vertexIndex2 = vertexIndex2;
		this.vertexIndex3 = vertexIndex3;
		this.vertexIndex4 = vertexIndex4;
		this.faceNormale = faceNormale;
	}

}
