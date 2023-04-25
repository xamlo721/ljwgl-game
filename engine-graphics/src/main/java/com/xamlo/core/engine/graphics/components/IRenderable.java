package com.xamlo.core.engine.graphics.components;

import ru.satomi.dc.primitive.Vec3f;

public interface IRenderable {

	/**
	 * Метод для инициализации объекта
	 */
	public void init();
	
	/**
	 * Подвинуть объект на вектор
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	public void move(Vec3f vector);
	
	/**
	 * Повернуть объект на вектор 
	 * [0] - yaw
	 * [1] - pitch
	 * [2] - roll
	 */
	public void rotate(Vec3f vector);

	/**
	 * Изменить размер объекта в scale раз
	 */
	public void scale(float scaleIndex);
	
	/**
	 * Получение текущей позиции камеры
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	public Vec3f getPosition();
	/**
	 * Получение текущего угла поворота камеры
	 * [0] - yaw
	 * [1] - pitch
	 * [2] - roll
	 */
	public Vec3f getRotation();
	
	/**
	 * Получить текущий масштаб
	 */
	public float getScale();
	
	/**
	 * Метод для освобождения объекта
	 */
	public void release();
	
	/**
	 * Самый главный метод - рисования на экране
	 */
	public void draw();
	
}
