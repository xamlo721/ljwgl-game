package com.xamlo.core.engine.graphics.components;

import ru.satomi.dc.primitive.Vec3f;

/*
 * Интерфейс для описания камер
 * Позозреваю, что камеры будут разные в проекте и надо будет эксперементировать
 * с их вариантами реализации
 */
public interface ICamera {

	void rotate(Vec3f vector);
	
	void move(Vec3f vector);
	
	Vec3f getPosition();
	
	Vec3f getRotation();
	
}
