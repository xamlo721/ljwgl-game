package com.xamlo.core.engine.graphics.primitives;

import ru.satomi.dc.primitive.Vec2f;
import ru.satomi.dc.primitive.Vec3f;

/**
 * Класс Vertex Описывает базовый случай расположения в VRAM информации о точке.
 * Точка содержит координаты Х, У, Z
 * Точка содержит векторы нормалей
 * Точка содержит цвет формата RGB
 * Точка содержит координату текстуры (предположительно UV)
 * @author Satomi
 *
 */
public class Vertex {
	
	public static final int FLOATS = 11;
	private Vec3f pos;
	private Vec3f normal;
	private Vec3f color;
	private Vec2f textureCoord;

	public Vertex(Vec3f pos, Vec3f color, Vec3f normal, Vec2f textures) {
		this.pos = pos;
		this.normal = normal;
		this.color = color;
		this.textureCoord = textures;
		
	}
	
	public Vertex(Vec3f pos, Vec3f color, Vec3f normal) {
		this(pos, color, normal, new Vec2f(0,0));
	}

	public Vertex(Vec3f pos, Vec3f color) {
		this(pos, color , new Vec3f(0,0,0));
	}
	
	public Vertex(Vec3f pos) {
		this(pos, new Vec3f(0,0,0));

	}
	
	public Vertex() {	
		this(new Vec3f(0,0,0));
	}
	
	public Vec2f getTextureCoord() {
		return textureCoord;
	}

	public void setTextureCoord(Vec2f textureCoord) {
		this.textureCoord = textureCoord;
	}

	public Vec3f getPos() {
		return pos;
	}

	public void setPos(Vec3f pos) {
		this.pos = pos;
	}

	public Vec3f getNormal() {
		return normal;
	}

	public void setNormal(Vec3f normal) {
		this.normal = normal;
	}

	public Vec3f getColor() {
		return color;
	}

	public void setColor(Vec3f color) {
		this.color = color;
	}
	
	public float[] toFloatArray() {
		float[] out = new float[Vertex.FLOATS];
		int index = 0;
		out[index++] = pos.X;
		out[index++] = pos.Y;
		out[index++] = pos.Z;
		out[index++] = normal.X;
		out[index++] = normal.Y;
		out[index++] = normal.Z;
		out[index++] = color.X;
		out[index++] = color.Y;
		out[index++] = color.Z;
		out[index++] = textureCoord.X;
		out[index++] = textureCoord.Y;
		return out;
	}
}
