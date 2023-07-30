package com.xamlo.engine.resource;

import com.xamlo.core.engine.graphics.components.Texture;
import com.xamlo.engine.api.resources.IShaderResource;
import com.xamlo.engine.api.resources.ITextureResource;
import com.xamlo.engine.api.resources.ResourceLoader;
import org.lwjgl.BufferUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;

import static org.lwjgl.stb.STBImage.*;

public class SimpleResourceLoader implements ResourceLoader {

    private static final SimpleResourceLoader INSTANCE = new SimpleResourceLoader();

    public static SimpleResourceLoader getInstance() {
        return INSTANCE;
    }

    @Override
    public Texture loadTexture(URI uri) {
        IntBuffer width = BufferUtils.createIntBuffer(Integer.BYTES);
        IntBuffer height = BufferUtils.createIntBuffer(Integer.BYTES);
        IntBuffer channels = BufferUtils.createIntBuffer(Integer.BYTES);

        ByteBuffer buf;
        try (InputStream is = getResourceStream(uri)) {
            if (is == null) {
                throw new IOException("Texture resource not found");
            }
            byte[] bytes = is.readAllBytes();

            buf = stbi_load_from_memory(
                    BufferUtils.createByteBuffer(bytes.length)
                            .put(bytes)
                            .flip(),
                    width,
                    height,
                    channels,
                    4
            );

            if (buf == null) {
                throw new IOException("Texture [" + uri + "] not loaded: " + stbi_failure_reason());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Texture(width.get(), height.get(), buf, uri);
    }

    @Override
    public IShaderResource loadShader(URI uri) {
        try (InputStream is = getResourceStream(uri)) {
            if (is == null) {
                throw new IOException("Specified shader resource not found.");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            StringJoiner stringJoiner = new StringJoiner("\n");
            String line;
            while ((line = reader.readLine()) != null) {
                stringJoiner.add(line);
            }
            return new SimpleShaderResource(uri, stringJoiner.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static InputStream getResourceStream(URI uri) throws IOException {
        return switch (uri.getScheme()) {
            case "file" -> Files.newInputStream(Path.of(uri));
            case "resource" -> SimpleResourceLoader.class.getResourceAsStream(uri.getPath());
            default -> throw new IllegalStateException("Unexpected value: " + uri.getScheme());
        };
    }
}
