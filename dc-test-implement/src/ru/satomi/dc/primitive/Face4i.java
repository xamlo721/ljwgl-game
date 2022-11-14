package ru.satomi.dc.primitive;

/*
 * 
 * Класс хранит в себе индексы образующих Face вершин
 * Для квадратного фейса это 4 версшины, для треугольного 3
 */
public class Face4i {
	
	public int vertexIndex1;
	public int vertexIndex2;
	public int vertexIndex3;
	public int vertexIndex4;
	
	public Point3d faceNormale;//normale_f

	
	public Face4i(int vertexIndex1, int vertexIndex2, int vertexIndex3, int vertexIndex4, Point3d faceNormale) {
		this.vertexIndex1 = vertexIndex1;
		this.vertexIndex2 = vertexIndex2;
		this.vertexIndex3 = vertexIndex3;
		this.vertexIndex4 = vertexIndex4;
		this.faceNormale = faceNormale;
	}

}
