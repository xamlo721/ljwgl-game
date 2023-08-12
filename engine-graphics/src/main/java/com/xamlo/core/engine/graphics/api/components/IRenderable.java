package com.xamlo.core.engine.graphics.api.components;

import org.joml.Vector3f;

public interface IRenderable {

	/**
	 * Метод для инициализации объекта
	 */
	public void init();
	
	/**
	 * Подвинуть объект на вектор
	 * относительно текущего расположения
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	public void move(Vector3f vector);
	
	/**
	 * Подвинуть объект на вектор
	 * относительно текущего расположения
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	public void move(float xCoord, float yCoord, float zCoord);
	
	/**
	 * Повернуть объект на вектор
	 * относительно текущего положения
	 * [0] - yaw
	 * [1] - pitch
	 * [2] - roll
	 */
	public void rotate(Vector3f vector);

	/**
	 * Повернуть объект на вектор
	 * относительно текущего положения
	 * [0] - yaw
	 * [1] - pitch
	 * [2] - roll
	 */
	public void rotate(float yaw, float pitch, float roll);
	
	/**
	 * Изменить размер объекта в scale раз
	 */
	public void scale(float scaleIndex);
	
	/**
	 * Установить объект на позицию
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	public void setPosition(Vector3f pos);
	
	/**
	 * Установить объект на позицию
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	public void setPosition(float xCoord, float yCoord, float zCoord);
	
	/**
	 * Установить положение объекта согласно вектору 
	 * [0] - yaw
	 * [1] - pitch
	 * [2] - roll
	 */
	public void setRotation(Vector3f rot);

	/**
	 * Установить положение объекта согласно вектору 
	 * [0] - yaw
	 * [1] - pitch
	 * [2] - roll
	 */
	public void setRotation(float yaw, float pitch, float roll);
	
	/**
	 * Установить размер объекта
	 */
	public void setScale(float scaleIndex);
	
	/**
	 * Растянуть геометрию объекта по указанным коэффициентам
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	public void expandGeometry(Vector3f geometry);
	
	/**
	 * Растянуть геометрию объекта по указанным коэффициентам
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	public void expandGeometry(float xCoord, float yCoord, float zCoord);
	
	/**
	 * Установить растяжение геометрии объекта по указанным коэффициентам
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	public void setExpandGeometry(Vector3f geometry);
	
	/**
	 * Установить растяжение геометрии объекта по указанным коэффициентам
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	public void setExpandGeometry(float xCoord, float yCoord, float zCoord);
	
	/**
	 * Восстанавливает геометрию объекта в исходное состояние
	 */
	public void resetGeometry();
	
	/**
	 * Получение текущей позиции камеры
	 * [0] - x Coord
	 * [1] - y Coord
	 * [2] - z Coord
	 */
	public Vector3f getPosition();
	/**
	 * Получение текущего угла поворота камеры
	 * [0] - yaw
	 * [1] - pitch
	 * [2] - roll
	 */
	public Vector3f getRotation();
	
	/**
	 * Получить текущий масштаб
	 */
	public float getScale();
	
	/**
	 * Метод для освобождения объекта
	 */
	public void release();

	
}
