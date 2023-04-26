package ru.satomi.dc.primitive;

import org.joml.Vector3f;

public class Quaternion {

	private float x;
	private float y;
	private float z;
	private float w;
	
	public Quaternion(float x, float y, float z, float w){
		this.setX(x);
		this.setY(y);
		this.setZ(z);
		this.setW(w);
	}
	
	public Quaternion(Vector3f v, float w){
		this.setX(v.x);
		this.setY(v.y);
		this.setZ(v.z);
		this.setW(w);
	}
	
	public float length()
	{
		return (float) Math.sqrt(x*x + y*y + z*z + w*w);
	}
	
	public Quaternion normalize()
	{
		float length = length();
		
		x /= length;
		y /= length;
		z /= length;
		w /= length;
		
		return this;
	}
	
	public Quaternion conjugate()
	{
		return new Quaternion (-x, -y, -z, w);
	}
	
	public Quaternion mul(Quaternion r)
	{
		float w_ = w * r.getW() - x * r.x - y * r.y - z * r.z;
		float x_ = x * r.getW() + w * r.x + y * r.z - z * r.y;
		float y_ = y * r.getW() + w * r.y + z * r.x - x * r.z;
		float z_ = z * r.getW() + w * r.z + x * r.y - y * r.x;

		return new Quaternion(x_, y_, z_, w_);
	}

	public Quaternion mul(Vector3f r)
	{
		float w_ = -x * r.x - y * r.y - z * r.z;
		float x_ =  w * r.x + y * r.z - z * r.y;
		float y_ =  w * r.y + z * r.x - x * r.z;
		float z_ =  w * r.z + x * r.y - y * r.x;

		return new Quaternion(x_, y_, z_, w_);
	}
	
	public Quaternion div(float r)
	{
		float w_ = w/r;
		float x_ = x/r;
		float y_ = y/r;
		float z_ = z/r;
		return new Quaternion(x_, y_, z_, w_);
	}
	
	public Quaternion mul(float r)
	{
		float w_ = w*r;
		float x_ = x*r;
		float y_ = y*r;
		float z_ = z*r;
		return new Quaternion(x_, y_, z_, w_);
	}
	
	public Quaternion sub(Quaternion r)
	{
		float w_ = w - r.getW();
		float x_ = x - r.x;
		float y_ = y - r.y;
		float z_ = z - r.z;
		return new Quaternion(x_, y_, z_, w_);
	}
	
	public Quaternion add(Quaternion r)
	{
		float w_ = w + r.getW();
		float x_ = x + r.x;
		float y_ = y + r.y;
		float z_ = z + r.z;
		return new Quaternion(x_, y_, z_, w_);
	}
	
	public Vector3f xyz(){
		return new Vector3f(x,y,z);
	}
	
	public String toString()
	{
		return "[" + this.x + "," + this.y + "," + this.z + "," + this.w + "]";
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}
}
