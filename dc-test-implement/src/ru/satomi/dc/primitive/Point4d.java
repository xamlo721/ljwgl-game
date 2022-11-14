package ru.satomi.dc.primitive;

public class Point4d {
	
	public double x;
	public double y;
	public double z;
	public double v;

	
	public Point4d() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.v = 0;
	}
	
	public Point4d(double x, double y, double z, double v) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.v = v;
	}
	
	public void append(Point4d p) {
		this.x += p.x;
		this.y += p.y;
		this.z += p.z;
		this.v += p.v;
	}
	
	public double distance(Point4d point) {
		return Math.sqrt((this.x - point.x)*(this.x - point.x) + (this.y - point.y)*(this.y - point.y) + (this.z - point.z)*(this.z - point.z));
	}
	
	public void update(double x, double y, double z, double v) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.v = v;
	}
}
