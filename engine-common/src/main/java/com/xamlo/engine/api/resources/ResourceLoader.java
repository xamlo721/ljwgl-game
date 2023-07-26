package com.xamlo.engine.api.resources;

import java.net.URI;

public interface ResourceLoader {

    ITextureResource loadTexture(URI uri);
}
