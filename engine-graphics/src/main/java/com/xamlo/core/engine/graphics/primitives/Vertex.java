package com.xamlo.core.engine.graphics.primitives;

import org.joml.Vector2f;
import org.joml.Vector3f;

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
	private Vector3f pos;
	private Vector3f normal;
	private Vector3f color;
	private Vector2f textureCoord;

	public Vertex(Vector3f pos, Vector3f color, Vector3f normal, Vector2f textures) {
		this.pos = pos;
		this.normal = normal;
		this.color = color;
		this.textureCoord = textures;
		
	}
	
	public Vertex(Vector3f pos, Vector3f color, Vector3f normal) {
		this(pos, color, normal, new Vector2f(0,0));
	}

	public Vertex(Vector3f pos, Vector3f color) {
		this(pos, color , new Vector3f(0,0,0));
	}
	
	public Vertex(Vector3f pos) {
		this(pos, new Vector3f(0,0,0));

	}
	
	public Vertex() {	
		this(new Vector3f(0,0,0));
	}
	
	public Vector2f getTextureCoord() {
		return textureCoord;
	}

	public void setTextureCoord(Vector2f textureCoord) {
		this.textureCoord = textureCoord;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public Vector3f getNormal() {
		return normal;
	}

	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}
	
	public float[] toFloatArray() {
		float[] out = new float[Vertex.FLOATS];
		int index = 0;
		out[index++] = pos.x;
		out[index++] = pos.y;
		out[index++] = pos.z;
		out[index++] = normal.x;
		out[index++] = normal.y;
		out[index++] = normal.z;
		out[index++] = color.x;
		out[index++] = color.y;
		out[index++] = color.z;
		out[index++] = textureCoord.x;
		out[index++] = textureCoord.y;
		return out;
	}
}
