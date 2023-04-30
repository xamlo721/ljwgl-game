package com.xamlo.core.engine.graphics.components;

import com.xamlo.engine.api.resources.EnumResourceType;
import com.xamlo.engine.api.resources.IModelResource;

public abstract class AbstractModel extends AbstractRenderableObject implements IModelResource {

	protected String modelName;
	protected String modelLocation;

	@Override
	public String getName() {
		return this.modelName;
	}

	@Override
	public void setName(String name) {
		this.modelName = name;
	}
	
	@Override
	public EnumResourceType getType() {
		return EnumResourceType.Model;
	}

	@Override
	public String getLocation() {
		return this.modelLocation;
	}

	@Override
	public void setLocation(String location) {
		this.modelLocation = location;
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
	public int getPolygonCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPolygonCount(int count) {
		// TODO Auto-generated method stub
		
	}

}
