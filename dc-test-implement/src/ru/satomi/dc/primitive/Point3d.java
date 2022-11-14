package ru.satomi.dc.primitive;

public class Point3d {
	
	public double x;
	public double y;
	public double z;
	
	public Point3d() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Point3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void append(Point3d p) {
		this.x += p.x;
		this.y += p.y;
		this.z += p.z;
	}
	
	public double distance(Point3d point) {
		return Math.sqrt((this.x - point.x)*(this.x - point.x) + (this.y - point.y)*(this.y - point.y) + (this.z - point.z)*(this.z - point.z));
	}
	
	public void update(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}


}
