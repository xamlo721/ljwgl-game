package com.xamlo.core.engine.graphics.components;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.lwjglb.engine.graph.Mesh;

import ru.satomi.dc.primitive.Vec3f;

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
	
	
	public AbstractRenderableObject() {
		this.position = new Vec3f(0.0f, 0.0f, 0.0f);
		this.rotation = new Vec3f(0.0f, 0.0f, 0.0f);
		this.scale = 1.0f;
	}

	@Override
	public void move(Vec3f vector) {
		this.position = this.position.add(vector);		
	}
	
	@Override
	public void rotate(Vec3f vector) {
		this.rotation = this.rotation.add(vector);

	}

	@Override
	public void scale(float scaleIndex) {
		this.scale = scale*scaleIndex;
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
        worldMatrix.identity().translate(new Vector3f(offset.X, offset.Y, offset.Z)).
                rotateX((float)Math.toRadians(rotation.X)).
                rotateY((float)Math.toRadians(rotation.Y)).
                rotateZ((float)Math.toRadians(rotation.Z)).
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
        worldMatrix.identity().translate(new Vector3f(this.position.X, this.position.Y, this.position.Z)).
                rotateX((float)Math.toRadians(rotation.X)).
                rotateY((float)Math.toRadians(rotation.Y)).
                rotateZ((float)Math.toRadians(rotation.Z)).
                scale(scale);
        
//		System.out.println("updating world matrix for rot" + this.rotation.X + ", " + this.rotation.Y + ", " + this.rotation.Z);
//		System.out.println("{" + worldMatrix.m00() + ", " + worldMatrix.m01() + ", " + worldMatrix.m02() + ", " + worldMatrix.m03() + "}");
//		System.out.println("{" + worldMatrix.m10() + ", " + worldMatrix.m11() + ", " + worldMatrix.m12() + ", " + worldMatrix.m13() + "}");
//		System.out.println("{" + worldMatrix.m20() + ", " + worldMatrix.m21() + ", " + worldMatrix.m22() + ", " + worldMatrix.m23() + "}");
//		System.out.println("{" + worldMatrix.m30() + ", " + worldMatrix.m31() + ", " + worldMatrix.m32() + ", " + worldMatrix.m33() + "}");

        return worldMatrix;
    }
    
}
