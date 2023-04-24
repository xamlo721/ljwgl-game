package com.xamlo.core.engine.graphics.components;

import org.joml.Matrix4f;

/*
 * Интерфейс, описывающий правила в заимодействия с понятием "Сцена"
 * Сцена, это набор графических элементов (возможно сложная структура списка)
 * которую необходимо сначала подгрузить в память, потом выгрузить
 * для рисования пока существует метод renderFrame(), позволяющий
 * отдать на откуп наследникам IScene всю логику по отрисовке объектов
 * Выглядит не безопасно, но пусть пока будет так
 * @author Satomi
 */
public interface IScene {
	
	void load();

	void renderFrame();
	
	void tranformScene(Matrix4f transformMatrix);
	
	void release();
	
	
}
