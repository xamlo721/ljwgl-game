package com.xamlo.core.engine.graphics.components;

import com.xamlo.engine.api.resources.IModelResource;

import java.net.URI;

public abstract class AbstractModel extends AbstractRenderableObject implements IModelResource {

	protected final URI modelLocation;

	protected AbstractModel(URI modelLocation) {
		this.modelLocation = modelLocation;
	}

	@Override
	public URI getLocation() {
		return this.modelLocation;
	}

	@Override
	public int getPolygonCount() {
		throw new UnsupportedOperationException("No implemented yet");
	}

	@Override
	public void setPolygonCount(int count) {
		throw new UnsupportedOperationException("No implemented yet");
	}

}
