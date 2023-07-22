package com.xamlo.core.engine.graphics.components;

import org.joml.Matrix4f;
import org.joml.Vector3f;

import com.xamlo.core.engine.graphics.api.components.IRenderable;


public abstract class AbstractRenderableObject implements IRenderable {
	
	/**
	 * Матрица преобразования координат объекта в мировые
	 * У каждого объекта своя матрица и своё положение в мире
	 * 
	 * Положение объекта в пространстве, заданное матрицей 4х4
	 * Определяет положение XYZ, повороты Yaw, roll, pich и маштаб scale
	 * //TODO: исрпвить пример
	 * 
	 * {1.0, 0.0, 0.0, 0.0}
	 * {0.0, 1.0, 0.0, 0.0}
	 * {0.0, 0.0, 1.0, 0.0}
	 * {0.0, 0.0, 0.0, 1.0}
	 */
	protected final Matrix4f worldMatrix = new Matrix4f();

	/*
	 * Позиция камеры в мире
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
    protected Vector3f position;
	
	/*
	 * Поворот камеры в мире
	 * [0] - yaw
	 * [1] - pitch
	 * [2] - roll
	 */
	protected Vector3f rotation;
	
	/**
	 * Масштаб объекта
	 */
	protected float scale;
	

	
	
	public AbstractRenderableObject() {
		this.position = new Vector3f(0.0f, 0.0f, 0.0f);
		this.rotation = new Vector3f(0.0f, 0.0f, 0.0f);
		this.scale = 1.0f;
	}

	@Override
	public void move(Vector3f vector) {
		this.position = this.position.add(vector);		
	}
	
	@Override
	public void rotate(Vector3f vector) {
		this.rotation = this.rotation.add(vector);

	}

	@Override
	public void scale(float scaleIndex) {
		this.scale = scale*scaleIndex;
	}
	
	@Override
	public void setPosition(Vector3f pos) {
		this.position = pos;
	}

	@Override
	public void setRotation(Vector3f rot) {
		this.rotation = rot;
	}

	@Override
	public void setScale(float scaleIndex) {
		this.scale = scaleIndex;
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
	public float getScale() {
		return scale;
	}
	
	/**
	 * Метод получения сетки объекта
	 * @return Mesh объекта
	 */
	public abstract GraphicalMesh getMesh();
	
	public abstract void loadMesh();


	/**
	 * @param offset - смещение по координатной оси
	 * @param rotation - поворот по трём осям вращения
	 * @param scale - масштаб объекта
	 * @return Матрица преобразования объекта в мировые координаты
	 */
    public Matrix4f getWorldMatrix(Vector3f offset, Vector3f rotation, float scale) {
        worldMatrix.identity().translate(offset).
                rotateX((float)Math.toRadians(rotation.x)).
                rotateY((float)Math.toRadians(rotation.y)).
                rotateZ((float)Math.toRadians(rotation.z)).
                scale(scale);
        return worldMatrix;
    }
    
	/**
	 * @param offset - смещение по координатной оси
	 * @param rotation - поворот по трём осям вращения
	 * @param scale - масштаб объекта
	 * @return Матрица преобразования объекта в мировые координаты
	 */
    public Matrix4f getWorldMatrix() {
        worldMatrix.identity().translate(position).
                rotateX((float)Math.toRadians(rotation.x)).
                rotateY((float)Math.toRadians(rotation.y)).
                rotateZ((float)Math.toRadians(rotation.z)).
                scale(scale);
        
//		System.out.println("updating world matrix for rot" + this.rotation.X + ", " + this.rotation.Y + ", " + this.rotation.Z);
//		System.out.println("{" + worldMatrix.m00() + ", " + worldMatrix.m01() + ", " + worldMatrix.m02() + ", " + worldMatrix.m03() + "}");
//		System.out.println("{" + worldMatrix.m10() + ", " + worldMatrix.m11() + ", " + worldMatrix.m12() + ", " + worldMatrix.m13() + "}");
//		System.out.println("{" + worldMatrix.m20() + ", " + worldMatrix.m21() + ", " + worldMatrix.m22() + ", " + worldMatrix.m23() + "}");
//		System.out.println("{" + worldMatrix.m30() + ", " + worldMatrix.m31() + ", " + worldMatrix.m32() + ", " + worldMatrix.m33() + "}");

        return worldMatrix;
    }
    
}
