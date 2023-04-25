package com.xamlo.core.engine.graphics.components;

import org.joml.Matrix4f;
import org.joml.Vector3fc;
import org.lwjglb.engine.graph.Mesh;

import ru.satomi.dc.primitive.Vec3f;

public abstract class AbstractRenderableObject implements IRenderable {

	/**
	 * Матрица преобразования координат объекта в мировые
	 * У каждого объекта своя матрица и своё положение в мире
	 */
	protected final Matrix4f worldMatrix = new Matrix4f();

	/*
	 * Позиция камеры в мире
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
    protected Vec3f position;
	
	/*
	 * Поворот камеры в мире
	 * [0] - yaw
	 * [1] - pitch
	 * [2] - roll
	 */
	protected Vec3f rotation;
	
	/**
	 * Масштаб объекта
	 */
	protected float scale;
	
	/**
	 * Сетка точек, описывающая объект
	 */
	protected Mesh mesh;
	
	@Override
	public void move(Vec3f vector) {
		this.position.add(vector);		
	}
	
	@Override
	public void rotate(Vec3f vector) {
		this.rotation.add(vector);
	}

	@Override
	public void scale(float scaleIndex) {
		this.scale = scaleIndex;
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
	public float getScale() {
		return scale;
	}
	
	/**
	 * Метод получения сетки объекта
	 * @return Mesh объекта
	 */
	public Mesh getMesh() {
		return this.mesh;
	}
	
	public abstract void loadMesh();

	/**
	 * @param offset - смещение по координатной оси
	 * @param rotation - поворот по трём осям вращения
	 * @param scale - масштаб объекта
	 * @return Матрица преобразования объекта в мировые координаты
	 */
    public Matrix4f getWorldMatrix(Vec3f offset, Vec3f rotation, float scale) {
        worldMatrix.identity().translate((Vector3fc) offset).
                rotateX((float)Math.toRadians(rotation.X)).
                rotateY((float)Math.toRadians(rotation.Y)).
                rotateZ((float)Math.toRadians(rotation.Z)).
                scale(scale);
        return worldMatrix;
    }
    
}
