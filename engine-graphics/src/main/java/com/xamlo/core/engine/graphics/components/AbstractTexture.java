package com.xamlo.core.engine.graphics.components;

import com.xamlo.engine.api.resources.ITextureResource;

import java.net.URI;

public class AbstractTexture implements ITextureResource {

	protected final URI location;
	// Идентификатор текстуры OpenGL
	protected int glTextureID = -1;
	// Ширина изображения
	protected final int width;
	// Высота изображения
	protected final int height;

	public AbstractTexture(URI location, int width, int height) {
		this.location = location;
		this.width = width;
		this.height = height;
	}

	@Override
	public URI getLocation() {
		return location;
	}

	@Override
	public int getTextureSize() {
		return width * height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
