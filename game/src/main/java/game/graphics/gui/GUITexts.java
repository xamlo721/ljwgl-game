package game.graphics.gui;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public final class GUITexts {

    private static final Map<String, String> TEXTS = new HashMap<>();

    static {
        loadTexts("/default_texts.properties");
    }

    public static String resolve(String text) {
        if (text == null) {
            return null;
        }
        return TEXTS.getOrDefault(text, text);
    }

    private static void loadTexts(String resourcePath) {
        try (InputStream stream = GUITexts.class.getResourceAsStream(resourcePath)) {
            if (stream == null) {
                System.err.println("GUITexts: " + resourcePath + " not found, raw keys will be used as-is");
                return;
            }
            Properties properties = new Properties();
            properties.load(new InputStreamReader(stream, StandardCharsets.UTF_8));
            for (String key : properties.stringPropertyNames()) {
                TEXTS.put(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            System.err.println("GUITexts: failed to load " + resourcePath + ": " + e.getMessage());
        }
    }

    private GUITexts() {
    }

}
