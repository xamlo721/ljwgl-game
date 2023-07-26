package com.xamlo.engine.resource;

import com.xamlo.engine.api.resources.IShaderResource;

import java.net.URI;

public class SimpleShaderResource implements IShaderResource {

    private final URI location;
    private final String shaderProgram;

    public SimpleShaderResource(URI location, String shaderProgram) {
        this.location = location;
        this.shaderProgram = shaderProgram;
    }

    @Override
    public URI getLocation() {
        return location;
    }

    @Override
    public String getShaderProgram() {
        return shaderProgram;
    }
}
