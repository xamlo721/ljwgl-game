package org.lwjglb.engine.graph;

import com.xamlo.core.engine.graphics.components.ShaderProgram;

import java.util.*;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL30.*;

public class SceneRender {

    private ShaderProgram shaderProgram;
    
	private final String vertexShaderSource = ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveVertexShader.glsl");
	
	private final String fragmentShaderSource =  ShaderProgram.loadShader("C:/workspace/eclipse/gamedev/engine-graphics/res/shaders/PrimitiveFragmentShader.glsl");
	private Mesh mesh;

    public SceneRender() {
		shaderProgram = new ShaderProgram();
		shaderProgram.addVertexShader(vertexShaderSource);
		shaderProgram.addFragmentShader(fragmentShaderSource);
		shaderProgram.compileShader();
		
//	       float[] positions = new float[]{
//	                0.0f, 0.5f, 0.0f,
//	                -0.5f, -0.5f, 0.0f,
//	                0.5f, -0.5f, 0.0f
//	        };
//	        mesh = new Mesh(positions, 3);
    }

    public void cleanup() {
        shaderProgram.cleanup();
    }

    public void render() {
        shaderProgram.bind();

        //glBindVertexArray(mesh.getVaoId());
        glDrawArrays(GL_TRIANGLES, 0, mesh.getNumVertices());

        glBindVertexArray(0);

        shaderProgram.unbind();
    }
}