package com.xamlo.core.engine.graphics.components;

import com.xamlo.engine.api.resources.ITextureResource;

import java.net.URI;

public class AbstractTexture implements ITextureResource {

	protected final URI location;
	// Идентификатор текстуры OpenGL
	protected final int glTextureID;
	// Ширина изображения
	protected final int width;
	// Высота изображения
	protected final int height;

	public AbstractTexture(URI location, int glTextureID, int width, int height) {
		this.location = location;
		this.glTextureID = glTextureID;
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
