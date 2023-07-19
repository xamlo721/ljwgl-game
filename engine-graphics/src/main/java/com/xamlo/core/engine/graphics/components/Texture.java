package com.xamlo.core.engine.graphics.components;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Класс Texture представляет текстуру OpenGL.
 * Он загружает изображение из файла и создает соответствующую текстуру OpenGL.
 */
public class Texture extends AbstractTexture {
//
//	public Texture() {
//		PNGDecoder decoder = new PNGDecoder(
//			     Texture.class.getResourceAsStream(fileName));
//	}
//

    /**
     * Создает новую текстуру OpenGL из изображения, загруженного из файла.
     *
     * @param filename имя файла с изображением
     */
    public Texture(String filename) {
    	//TODO: Исправить - не имя файла, а уникальное название текстуры
    	//TODO: Путь до текстуры потом подставит ResourceLoader
        this.textureLocation = filename;

    	//Проверка на наличие свободной памяти в куче
        try (MemoryStack stack = MemoryStack.stackPush()) {
        	//Так как нам нужна память не стека, то "int" ридётся заменить на эту конструкцию
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            ByteBuffer buf;
            try(InputStream is = Texture.class.getResourceAsStream(filename)) {
                if (is == null) {
                    throw new IOException("Texture resource not found");
                }
                byte[] bytes = is.readAllBytes();

                buf = stbi_load_from_memory(
                        ByteBuffer.allocateDirect(bytes.length)
                                .put(bytes)
                                .flip(),
                        w,
                        h,
                        channels,
                        4
                );
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (buf == null) {
                throw new RuntimeException("Image file [" + filename + "] not loaded: " + stbi_failure_reason());
            }

            this.width = w.get();
            this.height = h.get();

            generateTexture(width, height, buf);

            stbi_image_free(buf);
        }
    }

    private void generateTexture(int width, int height, ByteBuffer buf) {
    	///Получаем ID области памяти, для новой текстуры
    	textureID = glGenTextures();
    	
    	this.bind();
    	//Указываем сколько байт использовалось для одного пикселя
    	//R(8)+G(8)+B(8)+A(8) => 32bit => 1 int
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        /**
         * Этот параметр в основном говорит о том, что когда пиксель рисуется без 
         * прямой однозначной привязки к текстурной координате, он выбирает ближайшую 
         * точку текстурной координаты.
         */
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        /**
         * target - Тип текстуры, обычно GL_TEXTURE_2D
         * 
         * level - Указывает номер уровня детализации. 
         * 		Уровень 0 - это базовый уровень изображения. 
         * 		Уровень n - это n-е уменьшенное изображение mipmap.
         * 
         * internal format - Определяет количество и структуру цветовых компонентов в текстуре
         * 
         * width - Ширина текстуры
         * 
         * height - Высота текстуры
         * 
         * border - Не знаю, но раз нет бордюра, то 0
         * 
         * format - Структура расположения цветовых компонентов в пикселе.
         * 
         * type - какой тип данных использовать для хранения пикселя
         * 		Обычно для RGBA хватит классического сишного GL_UNSIGNED_BYTE
         * 
         * data - буфер, где лежит текстура
         */
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
        /**
         * Разрешить видеокарте сгенерировать mipmap для текстуры
         */
        glGenerateMipmap(GL_TEXTURE_2D);
    }
    
    /**
     * Привязывает текстуру OpenGL.
     */
    public void bind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
    }

    /**
     * Отвязывает текстуру OpenGL.
     */
    public void unbind() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    /**
     * Возвращает ширину изображения.
     *
     * @return ширина изображения
     */
    public int getWidth() {
        return width;
    }

    /**
     * Возвращает высоту изображения.
     *
     * @return высота изображения
     */
    public int getHeight() {
        return height;
    }

    /**
     * Возвращает идентификатор текстуры OpenGL.
     *
     * @return идентификатор текстуры OpenGL
     */
    public int getTextureID() {
        return textureID;
    }
}