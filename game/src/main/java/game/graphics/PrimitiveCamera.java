package game.graphics;

import org.joml.Vector3f;
import org.joml.Matrix4f;
import org.joml.Quaternionf;

import com.xamlo.core.engine.graphics.api.components.ICamera;

public class PrimitiveCamera implements ICamera {

	private static final float DEFAULT_NEAR_DISTANCE = 0.001f;
	
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
	private Vector3f position;
	
	/*
	 * Поворот камеры в мире
	 * [0] - yaw
	 * [1] - pitch
	 * [2] - roll
	 */
	private Vector3f rotation;
	
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
	@Override
	public Matrix4f getViewMatrix() {
	    Matrix4f viewMatrix = new Matrix4f();
	    viewMatrix.identity();

	    // переводим углы поворота камеры из градусов в радианы
	    float yaw = (float)Math.toRadians(rotation.x);
	    float pitch = (float)Math.toRadians(rotation.y);
	    float roll = (float)Math.toRadians(rotation.z);

	    // создаем кватернион для поворота камеры по осям
	    Quaternionf orientation = new Quaternionf().rotateYXZ(yaw, pitch, roll);

	    // умножаем матрицу вида на матрицу поворота камеры
	    viewMatrix.rotate(orientation);

	    // переносим камеру в пространстве
	    Vector3f invertedPosition = new Vector3f(-position.x, -position.y, -position.z);
	    viewMatrix.translate(invertedPosition);

	    
	    return viewMatrix;
	}
	
	
	public PrimitiveCamera() {
		this.position = new Vector3f(0, 0, 0);
		this.rotation = new Vector3f(0, 0, 0);
	    this.fov = DEFAULT_FIELD_OF_VIEW;
	    this.aspectRatio = DEFAULT_CAMERA_WIDTH / DEFAULT_CAMERA_HEIGHT;
	    this.near = DEFAULT_NEAR_DISTANCE;
	    this.far = DEFAULT_FAR_DISTANCE;
	
	}
	
	public PrimitiveCamera(Vector3f pos, Vector3f rotation, float fov, float aspectRatio) {
		//TODO: Валидация параметров
		this.position = pos;
		this.rotation = rotation;
	    this.fov = fov;
	    this.aspectRatio = aspectRatio;
	    this.near = DEFAULT_NEAR_DISTANCE;
	    this.far = DEFAULT_FAR_DISTANCE;
	}

	@Override
	public void rotate(Vector3f vector) {
		this.rotation.add(vector);
	}

	@Override
	public void move(Vector3f vector) {
		this.position.add(vector.x, vector.y, vector.z);		
	}

	@Override
	public Vector3f getPosition() {
		return position;
	}

	@Override
	public Vector3f getRotation() {
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
