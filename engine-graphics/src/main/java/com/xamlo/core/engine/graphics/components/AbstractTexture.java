package com.xamlo.core.engine.graphics.components;

import com.xamlo.engine.api.resources.EnumResourceType;
import com.xamlo.engine.api.resources.ITextureResource;

public class AbstractTexture implements ITextureResource {

	//Какое-то уникальное имя текстуры
	protected String textureName;
	//Расположение текстуры на диске
	//TODO: Я думаю это можно убрать отсюда и перенести 
	//TODO: в ResourceLoader
	protected String textureLocation;
	// Идентификатор текстуры OpenGL
	protected int textureID; 
	// Ширина изображения
	protected int width;
	// Высота изображения
	protected int height;
    
	@Override
	public String getName() {
		return textureName;
	}

	@Override
	public void setName(String name) {
		this.textureName = name;
	}
	
	@Override
	public EnumResourceType getType() {
		return EnumResourceType.Texture;
	}

	@Override
	public String getLocation() {
		return this.textureLocation;
	}

	@Override
	public void setLocation(String location) {
		this.textureLocation = location;
	}
	
	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setAvailable(boolean available) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getTextureSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setTextureSize(int size) {
		// TODO Auto-generated method stub
		
	}



}
