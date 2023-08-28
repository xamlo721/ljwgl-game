package com.xamlo.core.engine.graphics.api.components.scene;

import org.joml.Vector3f;

public interface IRenderable {

	/**
	 * Метод для инициализации объекта
	 */
	public void init();
	
	/**
	 * Метод для освобождения объекта
	 */
	public void release();

	
}
