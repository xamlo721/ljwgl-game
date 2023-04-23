package com.xamlo.core.engine.graphics.components;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import ru.satomi.dc.primitive.Vec3f;

public class PrimitiveCamera implements ICamera {

	private static final float DEFAULT_NEAR_DISTANCE = 1.00f;
	
	private static final float DEFAULT_FAR_DISTANCE = 1000.0f;
	
	private static final float DEFAULT_FIELD_OF_VIEW = 60.0f;
	 // пример соотношения сторон 16:9
	private static final int   DEFAULT_CAMERA_WIDTH = 1920;

	private static final int   DEFAULT_CAMERA_HEIGHT = 1080;

	/*
	 * Позиция камеры в мире
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	private Vec3f position;
	
	/*
	 * Поворот камеры в мире
	 * [0] - yaw
	 * [1] - pitch
	 * [2] - roll
	 */
	private Vec3f rotation;
	
	/*
	 * Угол обзора (FOV = Field of view) в градусах
	 */
	private float fov;

	/*
	 * Соотношение сторон изображения (AspectRatio)
	 */
	private float aspectRatio;

	/*
	 * Максимальное расстояние от камеры, 
	 * до которого будут отображаться объекты
	 */
	private float far;
	
	/*
	 * Минимальное расстояние от камеры, 
	 * от которого будут отображаться объекты
	 */
	private float near;
	  
	/*
	 * Матрица вида для камеры
	 */
	public Matrix4f getViewMatrix() {
	    Matrix4f viewMatrix = new Matrix4f();
	    viewMatrix.setIdentity();
	    viewMatrix.rotate((float) Math.toRadians(rotation.X), new Vector3f(1, 0, 0));
	    viewMatrix.rotate((float) Math.toRadians(rotation.Y), new Vector3f(0, 1, 0));
	    viewMatrix.rotate((float) Math.toRadians(rotation.Z), new Vector3f(0, 0, 1));
	    viewMatrix.translate(new Vector3f(-position.X, -position.Y, -position.Z));
	    return viewMatrix;
	}
	
	
	public PrimitiveCamera() {
		this.position = new Vec3f(0, 0, 0);
		this.rotation = new Vec3f(0, 0, 0);
	    this.fov = DEFAULT_FIELD_OF_VIEW;
	    this.aspectRatio = DEFAULT_CAMERA_WIDTH / DEFAULT_CAMERA_HEIGHT;
	    this.near = DEFAULT_NEAR_DISTANCE;
	    this.far = DEFAULT_FAR_DISTANCE;
	
	}
	
	public PrimitiveCamera(Vec3f pos, Vec3f rotation, float fov, float aspectRatio) {
		//TODO: Валидация параметров
		this.position = pos;
		this.rotation = rotation;
	    this.fov = fov;
	    this.aspectRatio = aspectRatio;
	    this.near = DEFAULT_NEAR_DISTANCE;
	    this.far = DEFAULT_FAR_DISTANCE;
	}

	@Override
	public void rotate(Vec3f vector) {
		this.rotation.add(vector);
	}

	@Override
	public void move(Vec3f vector) {
		this.position.add(vector);		
	}

	@Override
	public Vec3f getPosition() {
		return position;
	}

	@Override
	public Vec3f getRotation() {
		return rotation;
	}
	
	@Override
	public float getFov() {
		return fov;
	}

	@Override
	public void setFov(float fov) {
		this.fov = fov;
	}

	@Override
	public float getAspectRatio() {
		return aspectRatio;
	}

	@Override
	public void setAspectRatio(int width, int height) {
		this.aspectRatio = (float)width / (float)height;
	}

	@Override
	public float getFarDistance() {
	    return far;
	}

	@Override
	public void setFarDistance(float far) {
	    this.far = far;
	}

	@Override
	public float getNearDistance() {
		return near;
	}

	@Override
	public void setNearDistance(float near) {
		this.near = near;
	}
	
	
}
