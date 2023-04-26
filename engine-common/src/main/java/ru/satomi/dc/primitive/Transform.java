package ru.satomi.dc.primitive;

import org.joml.Matrix4f;
import org.joml.Vector3f;

//import core.kernel.Camera;

public class Transform {
	
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scaling;
	
	public Transform() {
		setTranslation(new Vector3f(0,0,0));
		setRotation(new Vector3f(0,0,0));
		setScaling(new Vector3f(1,1,1));
	}
	
	public Matrix4f getWorldMatrix() {
		Matrix4f translationMatrix = new Matrix4f().translation(translation);
		Matrix4f rotationMatrix = new Matrix4f().rotationXYZ(rotation.x, rotation.y, rotation.z);
		Matrix4f scalingMatrix = new Matrix4f().scaling(scaling);
		
		return translationMatrix.mul(scalingMatrix.mul(rotationMatrix));
	}
	
	public Matrix4f getModelMatrix() {
		Matrix4f rotationMatrix = new Matrix4f().rotationXYZ(rotation.x, rotation.y, rotation.z);
		
		return rotationMatrix;
	}	
	
//	public Matrix4f getModelViewProjectionMatrix()
//	{
//		return Camera.getInstance().getViewProjectionMatrix().mul(getWorldMatrix());
//	}
//
//	public Matrix4f getViewProjectionMatrix()
//	{
//		return Camera.getInstance().getViewProjectionMatrix();
//	}
//
//	public Matrix4f getProjectionMatrix()
//	{
//		return Camera.getInstance().getProjectionMatrix();
//	}

	public Vector3f getTranslation() {
		return translation;
	}

	public void setTranslation(Vector3f translation) {
		this.translation = translation;
	}
	
	public void setTranslation(float x, float y, float z) {
		this.translation = new Vector3f(x, y, z);
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}
	
	public void setRotation(float x, float y, float z) {
		this.rotation = new Vector3f(x,y,z);
	}

	public Vector3f getScaling() {
		return scaling;
	}

	public void setScaling(Vector3f scaling) {
		this.scaling = scaling;
	}
	
	public void setScaling(float x, float y, float z) {
		this.scaling = new Vector3f(x, y, z);
	}
}
